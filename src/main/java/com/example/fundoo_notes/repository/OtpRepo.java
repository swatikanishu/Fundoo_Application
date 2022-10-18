package com.example.fundoo_notes.repository;

import com.example.fundoo_notes.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  OtpRepo extends JpaRepository<Otp,Long> {
    @Query(value = "SELECT * FROM otp WHERE email=:emailid", nativeQuery = true)


    Otp getbyemailid(String emailid);

}
