package com.example.RunRestAPI.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) {
    public Run {
        if(completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Started on cannot be after completed on");
        }
    }

}
