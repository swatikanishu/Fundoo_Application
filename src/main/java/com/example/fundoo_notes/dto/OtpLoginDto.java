package com.example.fundoo_notes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OtpLoginDto {
    private String emailid;
    private Long otp;


}
