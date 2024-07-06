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

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getPlaceCategory() {
        return placeCategory;
    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public LocalDateTime getDateCategoryStart() {
        return dateCategoryStart;
    }

    public void setDateCategoryStart(LocalDateTime dateCategoryStart) {
        this.dateCategoryStart = dateCategoryStart;
    }

    public LocalDateTime getDateCategoryEnd() {
        return dateCategoryEnd;
    }

    public void setDateCategoryEnd(LocalDateTime dateCategoryEnd) {
        this.dateCategoryEnd = dateCategoryEnd;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<EnvyDto> getEnvyList() {
        return envyList;
    }

    public void setEnvyList(List<EnvyDto> envyList) {
        this.envyList = envyList;
    }
}
