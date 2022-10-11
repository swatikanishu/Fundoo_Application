package com.example.fundoo_notes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserDto {
    @Column(name = "firstName")
    @NotNull
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "emailid")
    private String emailid;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneno")
    private String phoneno;
    @Column(name = "dob")
    private String dob;
    @Column(name = "registerDate")
    private LocalDateTime registerDate = LocalDateTime.now();
    @Column(name = "updateddate")
    private LocalDateTime updatedDate;
    @Column(name = "verification")
    private boolean verify;
    @Column(name="profile")
    private String profilepic;

}
