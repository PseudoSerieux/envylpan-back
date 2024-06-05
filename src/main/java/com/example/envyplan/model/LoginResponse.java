package com.example.envyplan.model;

public class LoginResponse {
    private boolean success;

    public LoginResponse(String token) {
        this.token = token;
    }

    private String token;

    // Getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
