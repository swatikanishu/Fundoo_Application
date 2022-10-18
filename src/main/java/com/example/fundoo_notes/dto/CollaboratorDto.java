package com.example.fundoo_notes.dto;

import com.example.fundoo_notes.model.Note;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CollaboratorDto {
    public Long userId;
    public String userEmail;
    public String userFirstname;
    public String userLastname;


}
