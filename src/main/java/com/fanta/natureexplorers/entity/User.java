package com.fanta.natureexplorers.entity;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull
    @Size(min = 2, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastName;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(min = 8, max = 255)
    private String password;

    @NotNull
    private String role;

    private byte[] photo;

    @NotNull
    private Timestamp registrationDate;

    // Getters and Setters
}

