package com.example.envyplan.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class EnvyDto {

    private Long id;
    private String nameEnvy;
    private String placeEnvy;
    private String description;
    private String typeEnvy; // Assuming Type is an enum, we'll use a String for simplicity in the DTO
    private LocalDateTime dateEnvyStart;
    private LocalDateTime dateEnvyEnd;
    private Long ownerId; // Use owner's ID instead of the entire User object
    private Long categoryId; // Use category's ID instead of the entire Category object
}
