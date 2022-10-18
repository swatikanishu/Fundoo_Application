package com.example.fundoo_notes.service;
import com.example.fundoo_notes.dto.ForgotPasswordDto;
import com.example.fundoo_notes.dto.NoteDto;
import com.example.fundoo_notes.dto.OtpLoginDto;
import com.example.fundoo_notes.dto.UserDto;
import com.example.fundoo_notes.exception.OtpException;
import com.example.fundoo_notes.exception.UserException;
import com.example.fundoo_notes.model.Note;
import com.example.fundoo_notes.model.Otp;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.repository.OtpRepo;
import com.example.fundoo_notes.repository.UserRepo;
import com.example.fundoo_notes.utility.EmailSenderService;
import com.example.fundoo_notes.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    OtpRepo otpRepo;



    @Override
    public String addRecord(UserDto userDto) {
        User user = new User(userDto);
        userRepo.save(user);
        String token = tokenUtil.createToken(user.getId());
        emailSenderService.sendEmail(user.getEmailid(), "Added Your Details", "Your Account is registered! Please Click on the below link for the details." + "\n" + "http://localhost:8081/User/retrieve/" + token);
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
    public String loginUser(OtpLoginDto otpLoginDto) {
        User userDetails = userRepo.findByEmail(otpLoginDto.getEmailid());

        Otp otpdetails = otpRepo.getbyemailid(otpLoginDto.getEmailid());
        System.out.println(otpdetails);
        LocalDateTime checkOtpExpire = LocalDateTime.now();
        long noOfSeconds = Duration.between(otpdetails.getOtpExpiredTime(), checkOtpExpire).toSeconds();
        if( userDetails != null && otpdetails != null && otpdetails.getOtp().equals(otpLoginDto.getOtp())&& userDetails.isVerify()) {
            if (noOfSeconds <= 300) {
                emailSenderService.sendEmail(otpLoginDto.getEmailid(), "OTP Login", "Login Successful");
                //Deleting the OTP after successful Login
                otpRepo.delete(otpdetails);
                return "Login Successful";
            } else
                //Deleting the expired OTP
                otpRepo.delete(otpdetails);
            throw new OtpException("OTP expired");

        }else
            throw  new OtpException("inavalid otp");

        }



    @Override
    public String forgotPassword(String token) {
        Long Id = tokenUtil.decodeToken(token);
        Optional<User> userDetails = userRepo.findById(Id);
        if (userDetails.isEmpty()&& userDetails.get().isVerify()) {
            throw new UserException("Invalid Email Address");
        } else
            emailSenderService.sendEmail(userDetails.get().getEmailid(), "Forgot Password", "Please click on the below link to reset password" + "\nhttp://localhost:8081/User/resetPassword/" + token);
        return "Password link shared to your email address";
    }

    @Override
    public String resetPassword(ForgotPasswordDto forgotPasswordDTO, String token) {
        Long Id = tokenUtil.decodeToken(token);
        Optional<User> userDetails = userRepo.findById(Id);
        if (userDetails.get().getEmailid().equals(forgotPasswordDTO.getEmailid()) && userDetails.isPresent()&& userDetails.get().isVerify()) {
            userDetails.get().setPassword(forgotPasswordDTO.getNewPassword());
            userRepo.save(userDetails.get());
            return "Password Reset successful!";
        } else
            throw new UserException("Invalid Email Address");
    }

    @Override
    public String getotp(String emailid) {
        Otp otp = new Otp();
        Random random = new Random();
         int upperBound =999999;
        int lowerBound = 100000;
       int otpGenerated = random.nextInt(upperBound - lowerBound) + lowerBound;
        otp.setOtp(Long.valueOf(otpGenerated));
        otp.setEmailid(emailid);
        otp.setOtpTime(LocalDateTime.now());
        otp.setOtpExpiredTime(LocalDateTime.now().plusMinutes(5));
        otpRepo.save(otp);


        emailSenderService.sendEmail(emailid, "Login", "Your login otp is " + otpGenerated+"\n otp will expire in five mintues");
        return "otp sent to email";
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userDetails = userRepo.findById(userId);
        if (userDetails.isPresent()) {
            return (userDetails.get());
        } else {
            throw new UserException(" invalid user id");

        }


    }

    @Override
    public String verifyUser(String token) {
        Long userid = tokenUtil.decodeToken(token);
        Optional<User> user = userRepo.findById(userid);
        if (user.isPresent()) {
            user.get().setVerify(true);
            userRepo.save(user.get());
            return "user is verified";
        } else
            throw new UserException("token is not valid");
    }

    @Override
    public User editById(UserDto userDto, Long id) {
        Optional<User> user= userRepo.findById(id);
        if(user.isPresent() && user.get().isVerify()){
            user.get().setFirstName(userDto.getFirstName());
            user.get().setLastName(userDto.getLastName());
            user.get().setEmailid(userDto.getEmailid());
            user.get().setPassword(userDto.getPassword());
            user.get().setPhoneno(userDto.getPhoneno());
            user.get().setDob(userDto.getDob());
            user.get().setRegisterDate(userDto.getRegisterDate());
            user.get().setUpdatedDate(userDto.getUpdatedDate());
            user.get().setProfilepic(userDto.getProfilepic());
            userRepo.save(user.get());
        }
        return user.get();
    }
    }












