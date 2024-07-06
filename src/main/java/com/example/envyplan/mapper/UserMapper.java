package com.example.envyplan.mapper;

import com.example.envyplan.dto.UserDto;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.EnvyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class UserMapper {

    @Autowired
    private static CategoryRepository categoryRepository;

    @Autowired
    private static EnvyRepository envyRepository;

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setDateCreation(user.getDateCreation());
        dto.setCategoryIds(user.getCategory().stream()
                .map(category -> category.getId())
                .collect(Collectors.toList()));
        dto.setEnvyIds(user.getEnvy().stream()
                .map(envy -> envy.getId())
                .collect(Collectors.toList()));
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setDateCreation(dto.getDateCreation());

        // Note: Vous devrez récupérer et définir les entités Category et Envy dans une vraie application
         user.setCategory(categoryRepository.findAllById(dto.getCategoryIds()));
         user.setEnvy(envyRepository.findAllById(dto.getEnvyIds()));

        return user;
    }
}
