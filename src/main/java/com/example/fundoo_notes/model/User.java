package com.example.fundoo_notes.model;

import com.example.fundoo_notes.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    @NotNull
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
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


    public User(UserDto userDto) {
     this.firstName= userDto.getFirstName();
     this.lastName= userDto.getLastName();
     this.emailid= userDto.getEmailid();
     this.password= userDto.getPassword();
     this.dob= userDto.getDob();
     this.profilepic= userDto.getProfilepic();
     this.phoneno= userDto.getPhoneno();
     this.registerDate=userDto.getRegisterDate();
    this.updatedDate=userDto.getUpdatedDate();


    }
}
