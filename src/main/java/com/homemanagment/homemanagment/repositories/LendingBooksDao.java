package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingBooksDao extends JpaRepository<Book,Integer> {
}
