package com.example.fundoo_notes.model;

import com.example.fundoo_notes.dto.LabelDto;
import com.example.fundoo_notes.dto.NoteDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="labelName")
    private String labelName;
    private Long userId;
    @Column(name="noteId")
    private Long noteId;
    @Column(name = "registeredDate")
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
     @ManyToMany
    private List<Note> notes;


    public Label(LabelDto labelDto) {
    this.labelName = labelDto.getLabelName();
    this.noteId = labelDto.getNoteID();
    this.userId = labelDto.getUserId();
    }
}
