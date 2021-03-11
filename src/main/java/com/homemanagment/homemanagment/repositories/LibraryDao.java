package com.homemanagment.homemanagment.repositories;

import com.homemanagment.homemanagment.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryDao extends JpaRepository<Library,Integer> {
}
