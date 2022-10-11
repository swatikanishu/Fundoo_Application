package com.example.fundoo_notes.dto;

import com.example.fundoo_notes.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
