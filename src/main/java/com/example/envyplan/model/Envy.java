package com.example.envyplan.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Envy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 65, nullable = false, unique = true)
    private String nameEnvy;

    @Column(name = "place", length = 65)
    private String placeEnvy;

    @Column(name = "description", length = 250)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private Type typeEnvy;

    @Column(name = "date_envy_start")
    private LocalDateTime dateEnvyStart;

    @Column(name = "date_envy_end")
    private LocalDateTime dateEnvyEnd;

    //voir pour images + PJ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /* GETTERS and SETTERS */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEnvy() {
        return nameEnvy;
    }

    public void setNameEnvy(String nameEnvy) {
        this.nameEnvy = nameEnvy;
    }

    public String getPlaceEnvy() {
        return placeEnvy;
    }

    public void setPlaceEnvy(String placeEnvy) {
        this.placeEnvy = placeEnvy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getTypeEnvy() {
        return typeEnvy;
    }

    public void setTypeEnvy(Type typeEnvy) {
        this.typeEnvy = typeEnvy;
    }

    public LocalDateTime getDateEnvyStart() {
        return dateEnvyStart;
    }

    public void setDateEnvyStart(LocalDateTime dateEnvyStart) {
        this.dateEnvyStart = dateEnvyStart;
    }

    public LocalDateTime getDateEnvyEnd() {
        return dateEnvyEnd;
    }

    public void setDateEnvyEnd(LocalDateTime dateEnvyEnd) {
        this.dateEnvyEnd = dateEnvyEnd;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
