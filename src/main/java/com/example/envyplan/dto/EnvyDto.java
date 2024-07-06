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

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEnvy() {
        return nameEnvy;
    }

    public void setNameEnvy(String nameEnvy) {
        this.nameEnvy = nameEnvy;
    }

    public String getPlaceEnvy() {
        return placeEnvy;
    }

    public void setPlaceEnvy(String placeEnvy) {
        this.placeEnvy = placeEnvy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeEnvy() {
        return typeEnvy;
    }

    public void setTypeEnvy(String typeEnvy) {
        this.typeEnvy = typeEnvy;
    }

    public LocalDateTime getDateEnvyStart() {
        return dateEnvyStart;
    }

    public void setDateEnvyStart(LocalDateTime dateEnvyStart) {
        this.dateEnvyStart = dateEnvyStart;
    }

    public LocalDateTime getDateEnvyEnd() {
        return dateEnvyEnd;
    }

    public void setDateEnvyEnd(LocalDateTime dateEnvyEnd) {
        this.dateEnvyEnd = dateEnvyEnd;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
