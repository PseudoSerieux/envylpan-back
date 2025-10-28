package com.example.envyplan.mapper;

import com.example.envyplan.dto.UserDto;
import com.example.envyplan.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "categoryIds", source = "category", qualifiedByName = "categoryToIds")
    @Mapping(target = "envyIds", source = "envy", qualifiedByName = "envyToIds")
    UserDto toDto(User user);

    @Mapping(target = "category", source = "categoryIds", qualifiedByName = "idsToCategory")
    @Mapping(target = "envy", source = "envyIds", qualifiedByName = "idsToEnvy")
    User toEntity(UserDto dto);
}
