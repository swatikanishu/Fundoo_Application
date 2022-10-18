package com.example.fundoo_notes.model;

import com.example.fundoo_notes.dto.OtpLoginDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
        @Column(name = "email", nullable = false)

    private String emailid;
        private LocalDateTime createdotp= LocalDateTime.now();
    private LocalDateTime otpTime;
    private LocalDateTime otpExpiredTime;
    private Long otp;


}
