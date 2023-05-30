package com.fanta.natureexplorers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    private String reviewText;

    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "User_ID", referencedColumnName = "User_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Trip_ID", referencedColumnName = "Trip_ID")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "Excursion_ID", referencedColumnName = "Excursion_ID")
    private Excursion excursion;

    public Review() {
    }

    // Getters and Setters
}
