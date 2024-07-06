package com.example.envyplan.mapper;

import com.example.envyplan.dto.CategoryDto;
import com.example.envyplan.model.Category;
import com.example.envyplan.repository.EnvyRepository;
import com.example.envyplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class CategoryMapper {
    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static EnvyRepository envyRepository;

    public static CategoryDto toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setNameCategory(category.getNameCategory());
        dto.setPlaceCategory(category.getPlaceCategory());
        dto.setDateCategoryStart(category.getDateCategoryStart());
        dto.setDateCategoryEnd(category.getDateCategoryEnd());
        dto.setOwnerId(category.getOwner().getId());

        dto.setEnvyList(category.getEnvyList().stream()
                .map(EnvyMapper::toDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    public static Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setNameCategory(dto.getNameCategory());
        category.setPlaceCategory(dto.getPlaceCategory());
        category.setDateCategoryStart(dto.getDateCategoryStart());
        category.setDateCategoryEnd(dto.getDateCategoryEnd());
        // Fetch and set the actual User entity
        category.setOwner(userRepository.findById(dto.getOwnerId()).orElse(null));

        // Fetch and set the actual Envy entities
        if (dto.getEnvyList() != null) {
            category.setEnvyList(dto.getEnvyList().stream()
                    .map(envyId -> envyRepository.findById(Long.valueOf(String.valueOf(envyId))).orElse(null))
                    .collect(Collectors.toList()));
        }
        return category;
    }
}
