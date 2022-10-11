package com.example.fundoo_notes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForgotPasswordDto {
    private String emailid;
    private String newPassword;
}
