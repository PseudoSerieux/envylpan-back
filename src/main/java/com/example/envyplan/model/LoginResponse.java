package com.example.envyplan.model;

import lombok.Getter;
import lombok.Setter;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Getter
@Setter
@RegisterForReflection
public class LoginResponse {
    private boolean success;
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}