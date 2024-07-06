package com.example.envyplan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 65, nullable = false, unique = true)
    private String nameCategory;

    @Column(name = "place", length = 65, nullable = false)
    private String placeCategory;

    @Column(name = "date_category_start")
    private LocalDateTime dateCategoryStart;

    @Column(name = "date_category_end")
    private LocalDateTime dateCategoryEnd;

    //voir pour images
    @Column(name = "image")
    private String banniere;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Envy> envyList;

}
