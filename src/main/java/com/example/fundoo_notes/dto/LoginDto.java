package com.example.fundoo_notes.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
    private String emailid;
    private   String  password;
}
