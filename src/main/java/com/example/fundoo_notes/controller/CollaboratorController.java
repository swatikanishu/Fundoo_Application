package com.example.fundoo_notes.controller;

import com.example.fundoo_notes.dto.CollaboratorDto;
import com.example.fundoo_notes.dto.ResponseDto;
import com.example.fundoo_notes.service.ICollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collabarator")
public class CollaboratorController{
        @Autowired
        ICollaboratorService colabService;

    @PostMapping("/add")
    public ResponseDto addCollaborator(@RequestBody CollaboratorDto collaboratorDTO) {
        return colabService.addCollaborator(collaboratorDTO);
    }



    @DeleteMapping("/delete")
    public ResponseDto removeCollaborator(@RequestParam String email, @RequestParam Long colabId) {
        return colabService.removeCollaborator(email, colabId);
    }




}
