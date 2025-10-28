package com.example.envyplan.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime dateCreation;
    private List<Long> categoryIds;
    private List<Long> envyIds;
}