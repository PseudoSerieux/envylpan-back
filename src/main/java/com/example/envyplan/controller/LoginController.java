package com.example.envyplan.controller;

import com.example.envyplan.model.LoginResponse;
import com.example.envyplan.model.User;
import com.example.envyplan.dto.LoginDto;
import com.example.envyplan.dto.SignUpDto;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.service.AuthService;
import com.example.envyplan.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Récupérez la clé secrète à partir du fichier de propriétés
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping(value = "/inscription", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postRegisterUser(@RequestBody SignUpDto signUpDto) {
        //premier user crée : abcd mdp : ABCDabcd
        // Check si username existe déjà
        Boolean userByUsername = userRepository.existsByUsername(signUpDto.getUsername());
        if (userByUsername) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Ce pseudo existe déjà :("));
        }

        // Check si email existe déjà
        Boolean userByEmail = userRepository.existsByEmail(signUpDto.getEmail());
        if (userByEmail) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Cet email est déjà utilisé !"));
        }

        // Création du l'utilisateur
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setDateCreation(LocalDateTime.now());

        // Save the user to the database
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping(value = "/connexion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<LoginResponse> postLogin(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Récupération du token de AuthService
        String token = jwtUtil.generateToken(loginDto.getEmail());

        LoginResponse response = new LoginResponse(token);

        if (token != null) {
            System.out.println("AAAAAAAAA B " +  SecurityContextHolder.getContext().getAuthentication());
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
