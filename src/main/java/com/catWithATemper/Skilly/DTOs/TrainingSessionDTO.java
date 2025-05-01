package com.catWithATemper.Skilly.DTOs;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainingSessionDTO {
    private LocalDate date;
    private int durationMinutes;
    private String notes;
    private int rating;
    private Long userId;
    private Long skillId;
}
