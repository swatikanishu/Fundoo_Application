package com.example.fundoo_notes.dto;

import com.example.fundoo_notes.model.Note;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor

public class LabelDto {

    public Long noteID;
    @NotNull(message = "Please Enter Title")
    public String labelName;
    public Long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private List<Note> notes;

}
