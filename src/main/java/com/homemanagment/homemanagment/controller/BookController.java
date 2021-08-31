package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository repository;

    private final BookService service;

    @Autowired
    public BookController(final BookRepository repository, final BookService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/management/all")
    public String showFormListAllBooks(Model model) {
        model.addAttribute("listAllBook", repository.findAll());
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());
        return "management";
    }

    @GetMapping("/management")
    public String showAllBooks(Model model) {
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());
        return findPaginated(1, model, "title", "asc", "keyword");
    }

    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "new_book";
    }

    @PostMapping(value = "/save_book")
    public String saveNewBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            objectErrorList.forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            return "new_book";
        } else
            service.saveBook(book);
        return "new_book_info";
    }

    @GetMapping(value = "/new_book_info")
    public String showNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "management";
    }

    @GetMapping("/remove/{id}")
    public String removeBookById(@PathVariable("id") int id, Book book, Model model) {
        service.removeBookById(id, book);
        model.addAttribute("message", "Book successfully deleted!");
        return "management";
    }

    @GetMapping("/update/{id}")
    public String updateBookById(@PathVariable("id") int id, Model model) {
        Book book = service.findBookByID(id);
        model.addAttribute("message", "Book successfully update !");
        model.addAttribute("book", book);
        return "update_book";
    }

    @GetMapping("/search")
    public String searchBookByTitle(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword", service.search(keyword));
        return "search";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber, Model model,
                                @RequestParam("sortField") String sortfield,
                                @RequestParam("sortDir") String sortDir, String keyword) {
        //local variable how book on one page
        int pageSize = 5;
        //create page reference and get bookList to page getContent method
        Page<Book> page = service.findPaginated(pageNumber, pageSize, sortfield, sortDir);
        List<Book> bookList = page.getContent();
        List<Book> search = service.search(keyword);
        //paginated attribute
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listAllBooks", bookList);
        //sorting attribute
        model.addAttribute("sortField", sortfield);
        model.addAttribute("sortDirection", sortDir);
        model.addAttribute("reverseSortDirection", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("keyword", search);
        return "management";
    }
}
