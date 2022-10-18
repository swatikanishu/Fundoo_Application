package com.example.fundoo_notes.repository;

import com.example.fundoo_notes.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface CollaboratorRepo extends JpaRepository <Collaborator,Long> {
}
