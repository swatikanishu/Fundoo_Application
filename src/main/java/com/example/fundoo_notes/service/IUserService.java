package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.ForgotPasswordDto;
import com.example.fundoo_notes.dto.NoteDto;
import com.example.fundoo_notes.dto.OtpLoginDto;
import com.example.fundoo_notes.dto.UserDto;
import com.example.fundoo_notes.model.Note;
import com.example.fundoo_notes.model.User;

public interface IUserService {
    String addRecord(UserDto userDto);

    User getDataByToken(String token);

    String loginUser(OtpLoginDto loginDTO);


    String forgotPassword(String token);

    String resetPassword(ForgotPasswordDto forgotPasswordDTO, String token);

    String getotp(String emailid);

    User getUserById(Long userId);

    String verifyUser(String token);

    User editById(UserDto userDto, Long id);
}
