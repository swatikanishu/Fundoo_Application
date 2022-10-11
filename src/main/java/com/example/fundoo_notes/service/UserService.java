package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.ForgotPasswordDto;
import com.example.fundoo_notes.dto.LoginDto;
import com.example.fundoo_notes.dto.UserDto;
import com.example.fundoo_notes.exception.UserException;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.repository.UserRepo;
import com.example.fundoo_notes.utility.EmailSenderService;
import com.example.fundoo_notes.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class UserService implements IUserService{

@Autowired
    UserRepo userRepo;
@Autowired
    TokenUtil tokenUtil;
@Autowired
    EmailSenderService emailSenderService;
    @Override
    public String addRecord(UserDto userDto) {
        User user = new User(userDto);
        userRepo.save(user);
        String token = tokenUtil.createToken(user.getId());
        emailSenderService.sendEmail(user.getEmailid(), "Added Your Details", "Your Account is registered! Please Click on the below link for the details."+"\n"+"http://localhost:8081/User/retrieve/"+token);
        return token;
    }

    @Override
    public User getDataByToken(String token) {
        Long Userid = tokenUtil.decodeToken(token);
        Optional<User> existingData = userRepo.findById(Userid);
        if (existingData.isPresent()) {
            return existingData.get();
        } else
            throw new UserException("Invalid Token");
    }

    @Override
    public String loginUser(LoginDto loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepo.findByEmail(loginDTO.getEmailid()));
        if (userDetails.isPresent()) {
            if(userDetails.get().getPassword().equals(loginDTO.getPassword())){
                emailSenderService.sendEmail(userDetails.get().getEmailid(), "Login", "Login Successful!");
                return "login successfull";
            } else
                emailSenderService.sendEmail(userDetails.get().getEmailid(), "Login", "You have entered Invalid password!");
            throw new UserException("Wrong Password!!!");
        } else
            throw new UserException("Login Failed, Entered wrong email or password!!!");
    }

    @Override
    public String forgotPassword(String token) {
        Long sellerId = tokenUtil.decodeToken(token);
        Optional<User> sellerDetails= userRepo.findById(sellerId);
        if(sellerDetails.isEmpty()){
            throw new UserException("Invalid Email Address");
        }else
            emailSenderService.sendEmail(sellerDetails.get().getEmailid(), "Forgot Password", "Please click on the below link to reset password"+"\nhttp://localhost:8081/User/resetPassword/"+token);
        return "Password link shared to your email address";
    }

    @Override
    public String resetPassword(ForgotPasswordDto forgotPasswordDTO, String token) {
        Long sellerId = tokenUtil.decodeToken(token);
        Optional<User> sellerDetails = userRepo.findById(sellerId);
        if(sellerDetails.get().getEmailid().equals(forgotPasswordDTO.getEmailid()) && sellerDetails.isPresent()){
            sellerDetails.get().setPassword(forgotPasswordDTO.getNewPassword());
            userRepo.save(sellerDetails.get());
            return "Password Reset successful!";
        }else
            throw new UserException("Invalid Email Address");
    }
    }










