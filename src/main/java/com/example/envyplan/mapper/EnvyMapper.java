package com.example.envyplan.mapper;

import com.example.envyplan.dto.EnvyDto;
import com.example.envyplan.model.Envy;
import com.example.envyplan.model.Type;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvyMapper {
    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static CategoryRepository categoryRepository;
    public static EnvyDto toDTO(Envy envy) {
        if (envy == null) {
            return null;
        }

        EnvyDto dto = new EnvyDto();
        dto.setId(envy.getId());
        dto.setNameEnvy(envy.getNameEnvy());
        dto.setPlaceEnvy(envy.getPlaceEnvy());
        dto.setDescription(envy.getDescription());
        dto.setTypeEnvy(envy.getTypeEnvy().name());
        dto.setDateEnvyStart(envy.getDateEnvyStart());
        dto.setDateEnvyEnd(envy.getDateEnvyEnd());
        dto.setOwnerId(envy.getOwner().getId());
        dto.setCategoryId(envy.getCategory().getId());

        return dto;
    }

    public static Envy toEntity(EnvyDto dto) {
        if (dto == null) {
            return null;
        }

        Envy envy = new Envy();
        envy.setId(dto.getId());
        envy.setNameEnvy(dto.getNameEnvy());
        envy.setPlaceEnvy(dto.getPlaceEnvy());
        envy.setDescription(dto.getDescription());
        envy.setTypeEnvy(Type.valueOf(dto.getTypeEnvy()));
        envy.setDateEnvyStart(dto.getDateEnvyStart());
        envy.setDateEnvyEnd(dto.getDateEnvyEnd());
        // Fetch and set the actual User and Category entities
        envy.setOwner(userRepository.findById(dto.getOwnerId()).orElse(null));
        envy.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));

        return envy;
    }
}
