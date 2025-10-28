package com.example.envyplan.repository;

import com.example.envyplan.model.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
    public Optional<Category> findById(String id) {
        return find("id", id).firstResultOptional();
    }
}