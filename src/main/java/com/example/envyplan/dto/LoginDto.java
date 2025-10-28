package com.example.envyplan.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    public String email;
    private String password;
}