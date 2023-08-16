package com.example.envyplan.service;

import com.example.envyplan.model.User;

public interface IAuthService {
    User findByUsername(String username);
}
