package com.example.envyplan.mapper;

import com.example.envyplan.dto.CategoryDto;
import com.example.envyplan.model.Category;
import com.example.envyplan.repository.EnvyRepository;
import com.example.envyplan.repository.UserRepository;
import org.mapstruct.*;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "cdi", uses = {EnvyMapper.class})
public abstract class CategoryMapper {

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected EnvyRepository envyRepository;

    @Mapping(target = "ownerId", expression = "java(category.getOwner() != null ? category.getOwner().getId() : null)")
    @Mapping(target = "envyList", expression = "java(category.getEnvyList() != null ? category.getEnvyList().stream().map(envy -> envy.getId()).collect(Collectors.toList()) : null)")
    public abstract CategoryDto toDTO(Category category);

    @Mapping(target = "owner", expression = "java(dto.getOwnerId() != null ? userRepository.findById(dto.getOwnerId()).orElse(null) : null)")
    @Mapping(target = "envyList", expression = "java(dto.getEnvyList() != null ? dto.getEnvyList().stream().map(id -> envyRepository.findById(id).orElse(null)).collect(Collectors.toList()) : null)")
    public abstract Category toEntity(CategoryDto dto);
}