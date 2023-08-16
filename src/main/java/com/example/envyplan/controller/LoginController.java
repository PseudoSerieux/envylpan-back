package com.example.envyplan.controller;

import com.example.envyplan.model.LoginRequest;
import com.example.envyplan.model.LoginResponse;
import com.example.envyplan.model.Role;
import com.example.envyplan.model.User;
import com.example.envyplan.payload.LoginDto;
import com.example.envyplan.payload.SignUpDto;
import com.example.envyplan.repository.RoleRepository;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.service.AuthService;
import com.example.envyplan.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

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
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Récupérez la clé secrète à partir du fichier de propriétés
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping(value = "/inscription")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        //premier user crée : abcd mdp : ABCDabcd
        // Check if the username already exists
        Boolean userByUsername = userRepository.existsByUsername(signUpDto.getUsername());
        if (userByUsername) {
            return new ResponseEntity<>("Ce pseudo existe déjà !", HttpStatus.BAD_REQUEST);
        }

        // Check if the email already exists
        Boolean userByEmail = userRepository.existsByEmail(signUpDto.getEmail());
        if (userByEmail) {
            return new ResponseEntity<>("Cet email est déjà utilisé !", HttpStatus.BAD_REQUEST);
        }

        // Create a new user object
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setDateCreation(LocalDateTime.now());

        // Create a new role object
        Role role = Role.toRole(Role.ROLE_USER);
        roleRepository.save(role);

        // Assign the role to the user
        user.setRole(role);

        // Save the user and role to the database
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());

        return new ResponseEntity<>(token,HttpStatus.OK);
    }
/*2023-08-15T19:20:31.398+02:00 DEBUG 6596 --- [nio-8080-exec-6] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.example.envyplan.controller.LoginController#registerUser(SignUpDto)
2023-08-15T19:20:31.398+02:00 DEBUG 6596 --- [nio-8080-exec-5] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.example.envyplan.controller.LoginController#registerUser(SignUpDto)
2023-08-15T19:20:31.400+02:00 DEBUG 6596 --- [nio-8080-exec-6] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)
2023-08-15T19:20:31.401+02:00 DEBUG 6596 --- [nio-8080-exec-5] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)*/
    @PostMapping("/connexion")
    public ResponseEntity<String> login(@RequestBody @NotNull LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the token from the AuthService
        String token = jwtUtil.generateToken(loginDto.getUsername());

        // Return the token
        return new ResponseEntity<>(token, HttpStatus.OK);

        // OU : return new ResponseEntity<>("L'utilisateur a réussi à se connecter !.", HttpStatus.OK);
/*        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println("llllllll" + username);
        System.out.println("azt" + password);
        if (authService.authenticate(username, password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(new LoginResponse(true, token));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Utilisateur introuvable ou mot de passe incorrect"));
        }*/
    }

}
