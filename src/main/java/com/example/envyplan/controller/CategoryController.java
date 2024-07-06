package com.example.envyplan.controller;

import com.example.envyplan.dto.CategoryDto;
import com.example.envyplan.model.Category;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.service.AuthService;
import com.example.envyplan.util.JwtUtil;
import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Récupérez la clé secrète à partir du fichier de propriétés
    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        System.out.println("zajriza " +  SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //Récupérer un utilisateur (email) depuis le token localstorage du front
        System.out.println("YO TOUS LE MONDE C SQUIZI " +  SecurityContextHolder.getContext().getAuthentication());
        String kk = jwtUtil.getCurrentUsername();
        System.out.println("YO TOUS LE MONDE C LE KK " + kk);

        //En gros après avoir réussi à récupérer ce foutu nom, il va falloir retrouver l'User
        User user = userRepository.findByUsername(kk);


        //Création de la catégorie
        Category category = new Category();
        category.setBanniere(categoryDto.getBanniere());
        category.setNameCategory(categoryDto.getNameCategory());
        category.setPlaceCategory(categoryDto.getPlaceCategory());
        category.setDateCategoryStart(categoryDto.getDateCategoryStart());
        category.setDateCategoryEnd(categoryDto.getDateCategoryEnd());
        category.setOwner(user);

        // Save the category to the database
        categoryRepository.save(category);

        return ResponseEntity.ok("c'est ok");
    }
}
