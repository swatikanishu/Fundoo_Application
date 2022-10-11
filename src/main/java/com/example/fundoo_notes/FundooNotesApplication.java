package com.example.fundoo_notes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class FundooNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundooNotesApplication.class, args);
        System.out.println("Hello!!");
        log.info("Welcome to program");
    }

}
