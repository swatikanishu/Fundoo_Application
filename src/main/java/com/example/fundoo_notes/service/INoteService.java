package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.LabelDto;
import com.example.fundoo_notes.dto.NoteDto;
import com.example.fundoo_notes.dto.ReminderDto;
import com.example.fundoo_notes.dto.ResponseDto;
import com.example.fundoo_notes.model.Label;
import com.example.fundoo_notes.model.Note;

import java.util.List;

public interface INoteService {
    ResponseDto addNoteRecord(NoteDto noteDto);



    List<Note> getAll();

    Note editById(NoteDto noteDto, Long id);

    ResponseDto addToArchive(String userIdToken, Long noteId);

    ResponseDto restoreArchive(String userIdToken, Long noteId);

    String deleteNote(String userIdToken, Long noteId);

    ResponseDto addToTrash(String userIdToken, Long noteId);

    ResponseDto pinTheNote(String userIdToken, Long noteId);

    String addLabel(String userIdToken, LabelDto labelDto);

    String updateLabel(Long labelId, LabelDto labelDto);

    Label getLabel(Long labelId);

    String removeLabel(Long labelId);

//    String MoveToTrash(Long noteID);

    String moveToTrash(String userIdToken, Long noteId);


    String RestoreNoteFromTrash(String userIdToken, Long noteId);

    String createReminder(ReminderDto reminderDTO, Long userid);

    String RemoveReminder(Long noteid, Long userid);


}
