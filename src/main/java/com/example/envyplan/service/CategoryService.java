package com.example.envyplan.service;

import com.example.envyplan.model.Category;
import com.example.envyplan.model.User;
import com.example.envyplan.repository.CategoryRepository;
import com.example.envyplan.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public Category createCategory(Long userId, Category category) {
        System.out.println("chatgpt fait nimp comme moi");
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        category.setOwner(user);
        return categoryRepository.save(category);
    }
}
