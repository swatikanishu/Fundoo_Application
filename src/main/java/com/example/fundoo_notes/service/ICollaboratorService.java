package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.CollaboratorDto;
import com.example.fundoo_notes.dto.ResponseDto;

public interface ICollaboratorService {
    ResponseDto addCollaborator(CollaboratorDto collaboratorDTO);


    ResponseDto removeCollaborator(String email, Long colabId);


}
