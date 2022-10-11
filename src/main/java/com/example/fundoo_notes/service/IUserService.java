package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.ForgotPasswordDto;
import com.example.fundoo_notes.dto.LoginDto;
import com.example.fundoo_notes.dto.UserDto;
import com.example.fundoo_notes.model.User;

public interface IUserService {
    String addRecord(UserDto userDto);

    User getDataByToken(String token);

    String loginUser(LoginDto loginDTO);


    String forgotPassword(String token);

    String resetPassword(ForgotPasswordDto forgotPasswordDTO, String token);
}
