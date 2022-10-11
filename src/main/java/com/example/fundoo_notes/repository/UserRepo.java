package com.example.fundoo_notes.repository;


import com.example.fundoo_notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user WHERE email=:email_address", nativeQuery = true)
    User findByEmail(String email_address);

}
