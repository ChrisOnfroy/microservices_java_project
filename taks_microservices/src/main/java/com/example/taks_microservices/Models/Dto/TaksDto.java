package com.example.taks_microservices.Models.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaksDto {

    private String uuid;

    @NotBlank(message = "Task name is required")
    @Size(max = 100, message = "Task name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Task description is required")
    @Size(max = 300, message = "Task description cannot exceed 300 characters")
    private String description;

    @NotNull(message = "Task points are required")
    @PositiveOrZero(message = "Task points must be zero or positive")
    private Integer points;

    private Boolean status;

    private LocalDate creationDate;

    @FutureOrPresent(message = "Task deadline cannot be in the past")
    @JsonAlias("deathline")
    private LocalDate deadline;
}
