package com.example.fundoo_notes.repository;

import com.example.fundoo_notes.model.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TrashRepo extends JpaRepository<Trash, Long> {
}
