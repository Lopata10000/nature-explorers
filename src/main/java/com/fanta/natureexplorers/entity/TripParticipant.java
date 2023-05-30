package com.fanta.natureexplorers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TripParticipants")
public class TripParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "User_ID", referencedColumnName = "User_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Trip_ID", referencedColumnName = "Trip_ID")
    private Trip trip;

    // Getters and Setters
}
