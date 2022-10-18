package com.example.fundoo_notes.model;

import com.example.fundoo_notes.dto.CollaboratorDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Entity
@Data


public  class Collaborator {


    @Id
    @Column(name = "collab_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collabId;

    private LocalDateTime createdDate =LocalDateTime.now();

    private Long userId;


    private String userEmail;


    private String userFirstname;

    private String userLastname;



    public Collaborator(CollaboratorDto coollaboratorDTO) {
        this.userId = coollaboratorDTO.userId;
        this.userEmail = coollaboratorDTO.userEmail;
        this.userFirstname = coollaboratorDTO.userFirstname;
        this.userLastname = coollaboratorDTO.userLastname;

    }


}

