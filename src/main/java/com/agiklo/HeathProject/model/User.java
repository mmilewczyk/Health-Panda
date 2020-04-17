package com.agiklo.HeathProject.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String role;

    public User(Long id, String email, String userName, String password, String role) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = "USER";
    }
}
