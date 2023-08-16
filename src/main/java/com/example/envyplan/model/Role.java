package com.example.envyplan.model;

import jakarta.persistence.*;

@Entity
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", length = 65, nullable = false)
    private String roleName;

    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;

    public Role(int roleName) {
        this.roleName = String.valueOf(roleName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static Role toRole(int roleId) {
        if (roleId == 1) {
            return new Role(Role.ROLE_ADMIN);
        } else if (roleId == 2) {
            return new Role(Role.ROLE_USER);
        } else {
            throw new IllegalArgumentException("Role ID invalide");
        }
    }
}