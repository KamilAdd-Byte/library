package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRentalDao extends JpaRepository<BookRental,Long> {
}
