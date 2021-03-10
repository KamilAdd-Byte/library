package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLendingRepository extends JpaRepository<UserLending,Integer> {

}
