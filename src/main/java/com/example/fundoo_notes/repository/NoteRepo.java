package com.example.fundoo_notes.repository;

import com.example.fundoo_notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note ,Long>  {

}
