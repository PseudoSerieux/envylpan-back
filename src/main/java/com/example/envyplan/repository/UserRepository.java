package com.example.envyplan.repository;

import com.example.envyplan.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<User> findById(Long id) {
        return findByIdOptional(id);
    }

    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return find("username = ?1 or email = ?2", username, email).firstResultOptional();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public boolean existsByUsername(String username) {
        return count("username", username) > 0;
    }

    public boolean existsByEmail(String email) {
        return count("email", email) > 0;
    }
}