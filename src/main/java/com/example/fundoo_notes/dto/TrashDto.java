package com.example.fundoo_notes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor

public class TrashDto {
    private String title;
    private String description;
    private long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
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
