package com.example.fundoo_notes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoteDto {
    private String title;
    private String description;
    private long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean isArchieve;
    private boolean pin;
    private Long labelId;
    private String emailid;
    private String color;
    private LocalDateTime remindertime;
    public Long noteID;

    @NotNull(message = "Please Enter Title")
    public String labelName;

}
