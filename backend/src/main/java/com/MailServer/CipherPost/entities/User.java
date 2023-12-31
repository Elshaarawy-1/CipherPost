package com.MailServer.CipherPost.entities;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )

    private String username;

    public User(String username) {
        this.username = username;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
}
