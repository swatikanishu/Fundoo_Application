package com.example.fundoo_notes.controller;

import com.example.fundoo_notes.dto.*;
import com.example.fundoo_notes.model.Label;
import com.example.fundoo_notes.model.Note;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.repository.UserRepo;
import com.example.fundoo_notes.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Note")
public class NoteController {
    @Autowired
    INoteService iNoteService;
    @Autowired
    UserRepo userRepo;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> AddNoteDetails(@Valid @RequestBody NoteDto noteDto) {
        ResponseDto response = iNoteService.addNoteRecord(noteDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAllDetail() {
        List<Note> note = iNoteService.getAll();
        ResponseDto responseDTO = new ResponseDto("** All note  ** ", note);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    // edit by noteid
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable Long id, @Valid @RequestBody NoteDto noteDto) {
        Note note = iNoteService.editById(noteDto, id);
        ResponseDto respDTO = new ResponseDto(" **** Note details is updated *****", note);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @PostMapping("/archive")
    public ResponseDto addToArchive(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.addToArchive(userIdToken, noteId);
    }
    @PostMapping("/restoreArchive")
    public ResponseDto restoredArchive(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.restoreArchive(userIdToken, noteId);
    }

    @DeleteMapping("/delete")
    public String deleteNote(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.deleteNote(userIdToken, noteId);
    }
    @PostMapping("/pin")
    public ResponseDto pinTheNote(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.pinTheNote(userIdToken, noteId);
    }
    @PostMapping("/addnote")
    public String addLabel(@RequestParam String userIdToken, @RequestBody LabelDto labelDto) {
        return iNoteService.addLabel(userIdToken, labelDto);
    }

    @PutMapping("/edit/{labelId}")
    public ResponseEntity<ResponseDto> updateLabel (@RequestParam Long labelId,@RequestBody LabelDto labelDto){
        String label = iNoteService.updateLabel(labelId,labelDto);
        ResponseDto responseDTO=new ResponseDto(label);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/DisplayLabel/{LabelId}")
    public ResponseEntity<ResponseDto> getLabel(@PathVariable Long LabelId ){
        Label label  = iNoteService.getLabel(LabelId);
        ResponseDto responseDTO= new ResponseDto("Labels Fetched",label);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/RemoveLabel/{LabelId}")
    public ResponseEntity<ResponseDto> removeLabel(@PathVariable Long LabelId ){
        String label  = iNoteService.removeLabel(LabelId);
        ResponseDto responseDTO= new ResponseDto("Label Removed",label);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
//    @DeleteMapping("/MoveToTrash/{noteID}")
//    public ResponseEntity<ResponseDto> deleteNote(@PathVariable Long noteID) {
//        String message=iNoteService.MoveToTrash(noteID);
//        ResponseDto responseDTO=new ResponseDto("moved to trash", message);
//        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
//    }

    @PostMapping("/trash")
    public String addnoteToTrash(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.moveToTrash(userIdToken, noteId);
    }
    @PostMapping("/RestoreTrash")
    public String RestoreNoteFromTrash(@RequestParam String userIdToken, @RequestParam Long noteId) {
        return iNoteService.RestoreNoteFromTrash(userIdToken,noteId);
    }

    @PostMapping("/reminder")
    public ResponseEntity<ResponseDto> addReminder(@RequestBody ReminderDto reminderDTO, @PathVariable Long userid){
        Optional<User> user = userRepo.findById(userid) ;
        String message=iNoteService.createReminder(reminderDTO,userid);
        ResponseDto responseDTO=new ResponseDto(message,user);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping("/RemoveReminder/{noteid}/{userid}")
    public ResponseEntity<ResponseDto> removeReminder(@PathVariable Long noteid, @PathVariable Long userid){
        Optional<User> user = userRepo.findById(userid) ;
        String message=iNoteService.RemoveReminder(noteid,userid);
        ResponseDto responseDTO=new ResponseDto(message,user);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }



}

