package com.example.envyplan.controller;

import com.example.envyplan.model.LoginResponse;
import com.example.envyplan.dto.LoginDto;
import com.example.envyplan.dto.SignUpDto;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.service.AuthService;
import com.example.envyplan.service.UserService;
import com.example.envyplan.util.JwtUtil;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;

import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    JwtUtil jwtUtil;

    @Inject
    AuthService authService;

    @Inject
    UserRepository userRepository;

    @Inject
    UserService userService;

    @Inject
    Instance<Config> config;

    @POST
    @Path("/inscription")
    public Response postRegisterUser(SignUpDto signUpDto) {
        return userService.saveUser(signUpDto);
    }

    @POST
    @Path("/connexion")
    public Response postLogin(LoginDto loginDto) {
        return userService.signInUser(loginDto);
    }
}