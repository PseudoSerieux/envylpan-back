package com.example.envyplan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Envy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 65, nullable = false, unique = true)
    private String nameEnvy;

    @Column(name = "place", length = 65)
    private String placeEnvy;

    @Column(name = "description", length = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type typeEnvy;

    @Column(name = "date_envy_start")
    private LocalDateTime dateEnvyStart;

    @Column(name = "date_envy_end")
    private LocalDateTime dateEnvyEnd;

    // voir pour images + PJ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}