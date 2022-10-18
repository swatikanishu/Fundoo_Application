package com.example.fundoo_notes.dto;

import com.example.fundoo_notes.model.Collaborator;
import com.example.fundoo_notes.model.Label;
import com.example.fundoo_notes.model.Note;
import com.example.fundoo_notes.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String message, String response) {
        this.message = message;
        this.object = response;

    }

    public ResponseDto(String message, User response) {
        this.message = message;
        this.object = response;

    }

    public ResponseDto(String message, Optional<User> response) {
        this.message = message;
        this.object = response;

    }

    public ResponseDto(String token, Note note) {
        this.message=token;

        this.object=note;
    }

    public ResponseDto(String message, List<Note> response) {
        this.message=message;
        this.object=response;
    }


    public ResponseDto(String label) {
        this.message=label;   }

    public ResponseDto(String message, Label label) {
        this.message=message;
        this.object=label;

    }

    public ResponseDto(String message, Collaborator colab) {
        this.message=message;
        this.object=colab;
    }


    }

