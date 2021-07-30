package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

//    List<Book> lendingListBooks(@Param("lending") boolean lending);

//    @Query(value = "SELECT * FROM Book b where b.title=?1",nativeQuery = true)
//    List<Book> searchBookByTitle (@Param("title") String title
}
