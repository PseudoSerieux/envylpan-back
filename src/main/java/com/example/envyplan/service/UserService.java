package com.example.envyplan.service;

import com.example.envyplan.dto.LoginDto;
import com.example.envyplan.dto.SignUpDto;
import com.example.envyplan.model.LoginResponse;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.util.JwtUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    JwtUtil jwtUtil;

    @Inject
    io.quarkus.security.identity.SecurityIdentity securityIdentity;

    @Transactional
    public Response saveUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Ce pseudo existe déjà :("))
                    .build();
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Cet email est déjà utilisé !"))
                    .build();
        }

        try {
            User user = new User();
            user.setUsername(signUpDto.getUsername());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(signUpDto.getPassword()); // À encoder selon votre stratégie Quarkus
            user.setDateCreation(LocalDateTime.now());

            userRepository.save(user);

            return Response.status(Response.Status.ACCEPTED)
                    .entity(Map.of("message", "OK !"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }

    public Response signInUser(LoginDto loginDto) {
        // À adapter selon votre stratégie d’authentification Quarkus
        boolean authenticated = true; // Simuler l'authentification pour l'exemple
        String token = null;
        if (authenticated) {
            token = jwtUtil.generateToken(loginDto.getEmail());
        }
        LoginResponse response = new LoginResponse(token);

        if (token != null) {
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }
}