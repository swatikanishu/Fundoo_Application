package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.CollaboratorDto;
import com.example.fundoo_notes.dto.ResponseDto;
import com.example.fundoo_notes.model.Collaborator;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.repository.CollaboratorRepo;
import com.example.fundoo_notes.repository.UserRepo;
import com.example.fundoo_notes.utility.EmailSenderService;
import com.example.fundoo_notes.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollaboratorService implements ICollaboratorService {

    @Autowired
    CollaboratorRepo collaboratorRepo;
    @Autowired
    IUserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public ResponseDto addCollaborator(CollaboratorDto collaboratorDTO) {
        Collaborator colab = new Collaborator(collaboratorDTO);
        collaboratorRepo.save(colab);
        String token = tokenUtil.createToken(colab.getCollabId());
        emailSenderService.sendEmail(colab.getUserEmail(), "collabarator added", token);

        return new ResponseDto("added new collabarator ", token);
    }


    @Override
    public ResponseDto removeCollaborator(String email, Long colabId) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            collaboratorRepo.deleteById(colabId);
            return new ResponseDto("deleted collaborator by id", user);
        }
        return new ResponseDto("delete collaborator by id failed", user);
    }
}


