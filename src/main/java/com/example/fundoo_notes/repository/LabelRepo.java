package com.example.fundoo_notes.repository;

import com.example.fundoo_notes.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface LabelRepo extends JpaRepository<Label, Long>{

}
