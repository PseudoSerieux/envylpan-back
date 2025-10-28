package com.example.envyplan.repository;

import com.example.envyplan.model.Envy;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class EnvyRepository implements PanacheRepository<Envy> {
    public Optional<Envy> findById(Long id) {
        return find("id", id).firstResultOptional();
    }
}