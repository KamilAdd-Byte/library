package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

//    @Query(value = "SELECT * FROM Book b where b.title=?1",nativeQuery = true)
//    List<Book> searchBookByTitle (@Param("title") String title);
}
