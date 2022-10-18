package com.example.fundoo_notes.model;

import com.example.fundoo_notes.dto.NoteDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Note  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="Title")
    private String title;
    @Column(name="NoteDescription")
    private String description;
    @Column(name="UserId")
    private long userId;
    @Column(name = "registeredDate")
    private LocalDateTime registerDate;
    @Column(name = "UpdatedDate")
    private LocalDateTime updateDate;
    @Column(name="trash")
    private boolean trash;
    @Column(name="archieve")
    private boolean isArchieve;
    @Column(name="pin")
    private boolean pin;
    @Column(name="labelid")
    private Long labelId;
    @Column(name="emailid")
    private String emailid;
    @Column(name="color")
    private String color;
    @Column(name="reminder")
    private LocalDateTime remindertime;
    public Note (NoteDto noteDto){
        this.title=noteDto.getTitle();
        this.remindertime=noteDto.getRemindertime();
        this.color=noteDto.getColor();
        this.registerDate=noteDto.getRegisterDate();
        this.isArchieve= noteDto.isArchieve();
        this.pin=noteDto.isPin();
        this.description= noteDto.getDescription();
        this.emailid=noteDto.getEmailid();
        this.updateDate=noteDto.getUpdateDate();

    }


}
