package com.example.fundoo_notes.service;

import com.example.fundoo_notes.dto.LabelDto;
import com.example.fundoo_notes.dto.NoteDto;
import com.example.fundoo_notes.dto.ReminderDto;
import com.example.fundoo_notes.dto.ResponseDto;
import com.example.fundoo_notes.exception.NoteException;
import com.example.fundoo_notes.model.Label;
import com.example.fundoo_notes.model.Note;
import com.example.fundoo_notes.model.Trash;
import com.example.fundoo_notes.model.User;
import com.example.fundoo_notes.repository.LabelRepo;
import com.example.fundoo_notes.repository.NoteRepo;
import com.example.fundoo_notes.repository.TrashRepo;
import com.example.fundoo_notes.repository.UserRepo;
import com.example.fundoo_notes.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements  INoteService {
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IUserService iUserService;
    @Autowired
    LabelRepo labelRepo;
    @Autowired
    TrashRepo trashRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseDto addNoteRecord(NoteDto noteDto) {
        Note note = new Note(noteDto);
        noteRepo.save(note);
        String token = tokenUtil.createToken(note.getId());
        ResponseDto respDTO = new ResponseDto(token, note);
        return respDTO;
    }

    @Override
    public List<Note> getAll() {

        return noteRepo.findAll();
    }

    @Override
    public Note editById(NoteDto noteDto, Long id) {

        Optional<Note> note = noteRepo.findById(id);
        if (note.isPresent()) {
            note.get().setArchieve(noteDto.isArchieve());
            note.get().setEmailid(noteDto.getEmailid());
            note.get().setColor(noteDto.getColor());
            note.get().setPin(noteDto.isPin());
            note.get().setTrash(noteDto.isTrash());
            note.get().setLabelId(noteDto.getLabelId());
            note.get().setRemindertime(noteDto.getRemindertime());
            note.get().setRegisterDate(noteDto.getRegisterDate());
            note.get().setUpdateDate(noteDto.getUpdateDate());
            note.get().setTitle(noteDto.getTitle());
            note.get().setDescription(noteDto.getDescription());
            note.get().setUserId(noteDto.getUserId());
            noteRepo.save(note.get());
        }
        return note.get();
    }

    @Override
    public ResponseDto addToArchive(String userIdToken, Long noteId) {
        Long userId = tokenUtil.decodeToken(userIdToken);

        User user = iUserService.getUserById(userId);

        if (user != null) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setArchieve(true);
            return new ResponseDto("added to archive by ", user);

        } else {
            throw new NoteException("ivalid user id");

        }

    }

    @Override
    public ResponseDto restoreArchive(String userIdToken, Long noteId) {
        Long userId = tokenUtil.decodeToken(userIdToken);
        User user = iUserService.getUserById(userId);
        if (user != null) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setArchieve(false);
            return new ResponseDto("added to archive by ", user);

        } else {
            throw new NoteException("invalid user id");

        }

    }

    @Override
    public String deleteNote(String userIdToken, Long noteId) {
        Long userId = tokenUtil.decodeToken(userIdToken);

        User user = (iUserService.getUserById(userId));

        if (user != null) {
            Optional<Note> note = noteRepo.findById(noteId);
            noteRepo.delete(note.get());
            return "note is deleted successfully";


        } else {
            throw new NoteException("invalid user id");

        }


    }

    @Override
    public ResponseDto addToTrash(String userIdToken, Long noteId) {
        Long userId = tokenUtil.decodeToken(userIdToken);

        User user = iUserService.getUserById(userId);

        if (user != null) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setTrash(true);
            return new ResponseDto("Note added to trash by " + user.getFirstName(), noteRepo.save(note.get()));

        } else {
            throw new NoteException("invalid user id");


        }

    }

    @Override
    public ResponseDto pinTheNote(String userIdToken, Long noteId) {
        Long userId = tokenUtil.decodeToken(userIdToken);

        User user = iUserService.getUserById(userId);

        if (user != null) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setPin(true);
            return new ResponseDto("Note is pinned by ", note.get());

        } else
            throw new NoteException("invalid note id userid");

    }

    @Override
    public String addLabel(String userIdToken, LabelDto labelDto) {
        Long userId = tokenUtil.decodeToken(userIdToken);

        User user = iUserService.getUserById(userId);

        if (user != null) {

            Label label = new Label(labelDto);
            label.setUserId(userId);
            labelRepo.save(label);

        }

        return "new Label not added because user not present";
    }

    @Override
    public String updateLabel(Long LabelId, LabelDto labelDto) {
        Optional<Label> labelDetails = labelRepo.findById((LabelId));
        if (labelDetails != null) {
            labelDetails.get().setLabelName(labelDto.getLabelName());
            labelDetails.get().setNotes(labelDto.getNotes());
            labelDetails.get().setNoteId(labelDto.getNoteID());
            labelDetails.get().setUserId(labelDto.getUserId());
            labelDetails.get().setUpdateDate(labelDto.getUpdateDate());
            labelDetails.get().setRegisterDate(labelDto.getRegisterDate());

            labelRepo.save(labelDetails.get());
            return "Label Updated Successful";
        } else
            throw new NoteException("inavild label id");
    }

    @Override
    public Label getLabel(Long labelId) {
        Optional<Label> label = labelRepo.findById(labelId);
        if (label.isPresent()) {
            return label.get();
        } else {
            throw new NoteException("invalid labelid");
        }
    }

    @Override
    public String removeLabel(Long labelId) {
        Optional<Label> label = labelRepo.findById(labelId);
        if (label.isPresent()) {
            labelRepo.deleteById(labelId);
        } else {
            throw new NoteException("invalid labelid");
        }
        return "label is removed";
    }

//    @Override
//    public String MoveToTrash(Long noteID) {
//        Optional<Note> note = noteRepo.findById(noteID);
//        if (note.isPresent()) {
//            Trash trash = new Trash();
//            trash.setRegisterDate(note.get().getRegisterDate());
//            trash.setPin(note.get().isPin());
//            trash.setDescription(note.get().getDescription());
//            trash.setEmailid(note.get().getEmailid());
//            trash.setRemindertime(note.get().getRemindertime());
//            trash.setUpdateDate(note.get().getUpdateDate());
//            trash.setTitle(note.get().getTitle());
//            trash.setUserId(note.get().getUserId());
//            trash.setArchieve(note.get().isArchieve());
//            trash.setLabelId(note.get().getLabelId());
//            trashRepo.save(trash);
//            return  "note have been moved to trash";
//        } else
//            throw new NoteException("note is not saved");

//    }

    @Override
    public String moveToTrash(String userIdToken, Long noteId) {
        Long userId =tokenUtil.decodeToken(userIdToken);
        Optional<User> user =userRepo.findById(userId);
        if(user.isPresent()) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setTrash(true);
            noteRepo.save(note.get());
            return "moved to trash";
        }else
            throw new NoteException(" \"Note is not added to trash because User is not present \"");
    }
    @Override
    public String RestoreNoteFromTrash(String userIdToken, Long noteId) {
        Long userId =tokenUtil.decodeToken(userIdToken);
        Optional<User> user =userRepo.findById(userId);
        if(user.isPresent()) {
            Optional<Note> note = noteRepo.findById(noteId);
            note.get().setTrash(false);
            noteRepo.save(note.get());
            return "retrieve from trash";
        }else
            throw new NoteException(" \"Noteid is not found\"");
    }

    @Override
    public String createReminder(ReminderDto reminderDTO, Long userid) {
        Optional<Note> noteDetails = noteRepo.findById(reminderDTO.getNoteID());
        if(!noteDetails.get().isTrash()){
                Date date =reminderDTO.dateAndTime;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                String format = formatter.format(date);
                noteDetails.get().setRemindertime(LocalDateTime.parse(format));
                noteRepo.save(noteDetails.get());
                return "REMINDER ADDED";
            }else
                throw new NoteException("Can't Unarchive In Trash");
    }


    @Override
    public String RemoveReminder(Long noteid, Long userid) {
        Optional<Note> noteDetails = noteRepo.findById(noteid);
        if(!noteDetails.get().isTrash()){
            noteDetails.get().setRemindertime(null);
            noteRepo.save(noteDetails.get());
            return "REMINDER REMOVED";
        }else
            throw new NoteException("invaild note id");
    }


    }
















