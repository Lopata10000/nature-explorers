package com.fanta.natureexplorers.entity;

import com.fanta.natureexplorers.validator.OnlyLetters;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "excursions")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private int excursionId;
    @OnlyLetters
    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private String description;

    private LocalDate date;

    @NotNull(message = "Location cannot be null")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    private byte[] photo;

    public Excursion(String name, String description, LocalDate date, String location, Manager manager) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.manager =manager;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;

    public Excursion() {

    }

    public int getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(int excursionId) {
        this.excursionId = excursionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
