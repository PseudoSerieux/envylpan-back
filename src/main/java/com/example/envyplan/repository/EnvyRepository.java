package com.example.envyplan.repository;

import com.example.envyplan.model.Envy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnvyRepository extends JpaRepository<Envy, Long> {
    Optional<Envy> findById(Long id);
}
