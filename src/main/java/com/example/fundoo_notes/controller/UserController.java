package com.example.fundoo_notes.controller;

import com.example.fundoo_notes.dto.ForgotPasswordDto;
import com.example.fundoo_notes.dto.LoginDto;
import com.example.fundoo_notes.dto.ResponseDto;
import com.example.fundoo_notes.dto.UserDto;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    IUserService userService;
    // Add new user data
    @PostMapping("/register")
    public ResponseEntity<String> AddAddressDetails(@Valid @RequestBody UserDto userDto) {
        String token = userService.addRecord(userDto);
        ResponseDto respDTO = new ResponseDto("Data Added Successfully", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    @GetMapping("/retrieve/{token}")
    public ResponseEntity<ResponseDto> getUserDetails(@Valid @PathVariable String token) {
        User response = userService.getDataByToken(token);
        ResponseDto respDTO = new ResponseDto("Data retrieved successfully", response);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    //Login check
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDTO) {
        String response = userService.loginUser(loginDTO);
        ResponseDto responseDTO = new ResponseDto("Login Status:", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }    //Forgot Password
    @PostMapping("/forgotPassword/{token}")
    public ResponseEntity<ResponseDto> forgotPassword(@PathVariable String token) {
        String response = userService.forgotPassword(token);
        ResponseDto responseDTO = new ResponseDto("Password Link Shared to email", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<ResponseDto> resetPassword(@RequestBody ForgotPasswordDto forgotPasswordDTO, @PathVariable String token) {
        String response = userService.resetPassword(forgotPasswordDTO, token);
        ResponseDto responseDTO = new ResponseDto("Password Reset", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
