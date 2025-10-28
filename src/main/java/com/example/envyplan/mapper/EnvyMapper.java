package com.example.envyplan.mapper;
import com.example.envyplan.dto.EnvyDto;
import com.example.envyplan.model.Category;
import com.example.envyplan.model.Envy;
import com.example.envyplan.model.Type;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@ApplicationScoped
@Mapper(componentModel = "cdi")
public abstract class EnvyMapper {

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected CategoryRepository categoryRepository;

    public static final EnvyMapper INSTANCE = Mappers.getMapper(EnvyMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "typeEnvy", target = "typeEnvy", qualifiedByName = "typeToString")
    public abstract EnvyDto toDTO(Envy envy);

    @Mapping(target = "owner", expression = "java(envyMapper.resolveUser(dto.getOwnerId()))")
    @Mapping(target = "category", expression = "java(envyMapper.resolveCategory(dto.getCategoryId()))")
    @Mapping(target = "typeEnvy", source = "typeEnvy", qualifiedByName = "stringToType")
    public abstract Envy toEntity(EnvyDto dto, @Context EnvyMapper envyMapper);

    @Named("typeToString")
    public String typeToString(Type type) {
        return type != null ? type.name() : null;
    }

    @Named("stringToType")
    public Type stringToType(String type) {
        return type != null ? Type.valueOf(type) : null;
    }

    public User resolveUser(Long id) {
        return id != null ? userRepository.findById(id).orElse(null) : null;
    }

    public Category resolveCategory(Long id) {
        return id != null ? categoryRepository.findById(id) : null;
    }
}