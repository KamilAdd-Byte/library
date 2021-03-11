package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import com.homemanagment.homemanagment.service.UserServiceImpl;
import com.homemanagment.homemanagment.system.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    @Autowired
    private final LibraryService libraryService;

    @Autowired
    private final BookServiceImpl service;


    @Autowired
    private final UserServiceImpl userService;

    public LibraryController(final LibraryService libraryService, final BookServiceImpl service,
                             final UserServiceImpl userService) {
        this.libraryService = libraryService;
        this.service = service;
        this.userService = userService;
    }
//    @GetMapping("/lending")
//    public String getAllLendingBooks(Model model){
//       model.addAttribute("listLendingBooks",homeLibrary.getLendingBookList());
//       return "/lending";
//    }
//    @GetMapping("/lending")
//    public String showFormLendingBook(@ModelAttribute("user") UserLending userLending,
//                                      @ModelAttribute("book") Book book,
//                                      @ModelAttribute("library") HomeLibrary homeLibrary, Model model){
//        model.addAttribute("book",book);
//        model.addAttribute("user",userLending);
//        model.addAttribute("lending",homeLibrary.lendingBook(userLending, book.getId(),book));
//        return "/lending";
//    }
    @GetMapping("/lending/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,
                                  @ModelAttribute("user")UserLending userLending, Model model){
        Book book = service.findBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("listUser",userService.allUserList());
        return "lending";

    }


}
