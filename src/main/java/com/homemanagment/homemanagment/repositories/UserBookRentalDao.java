package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.UserBookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRentalDao extends JpaRepository<UserBookRental,Long> {
}
