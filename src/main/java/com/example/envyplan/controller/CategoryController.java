package com.example.envyplan.controller;

import com.example.envyplan.configuration.SecurityConfig;
import com.example.envyplan.dto.CategoryDto;
import com.example.envyplan.model.Category;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.UserRepository;
import com.example.envyplan.service.AuthService;
import com.example.envyplan.util.JwtUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

@Path("/category")
public class CategoryController {

    @Inject
    JwtUtil jwtUtil;

    @Inject
    SecurityConfig securityConfig;

    @Inject
    AuthService authService;

    @Inject
    UserRepository userRepository;

    @Inject
    CategoryRepository categoryRepository;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDto categoryDto) {
        try {
            // Récupérer le nom d'utilisateur depuis le JWT
            String kk = jwtUtil.getCurrentUsername();

            // Décoder le token si nécessaire (adapter selon votre implémentation)
            securityConfig.jwtDecoder().decode(kk);

            // Récupérer l'utilisateur
            User user = userRepository.findByUsername(kk);

            // Création de la catégorie
            Category category = new Category();
            category.setBanniere(categoryDto.getBanniere());
            category.setNameCategory(categoryDto.getNameCategory());
            category.setPlaceCategory(categoryDto.getPlaceCategory());
            category.setDateCategoryStart(categoryDto.getDateCategoryStart());
            category.setDateCategoryEnd(categoryDto.getDateCategoryEnd());
            category.setOwner(user);

            // Sauvegarder la catégorie
            categoryRepository.save(category);

            return Response.ok(Map.of("message", "c'est ok")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Erreur interne", e.getMessage()))
                    .build();
        }
    }
}