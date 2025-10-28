package com.example.envyplan.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String nameCategory;
    private String placeCategory;
    private LocalDateTime dateCategoryStart;
    private LocalDateTime dateCategoryEnd;

    private String banniere;
    private Long ownerId; // Use owner's ID instead of the entire User object
    private List<EnvyDto> envyList;

}
