package com.fanta.natureexplorers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trip_participants")
public class TripParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripParticipants_id;
    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @NotNull(message = "Trip cannot be null")
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;
    public TripParticipant(User user, Trip trip) {
        this.user = user;
        this.trip = trip;
    }

    public TripParticipant() {

    }

    public int getTripParticipantsId() {
        return tripParticipants_id;
    }

    public void setTripParticipantsId(int tripParticipants_id) {
        this.tripParticipants_id = tripParticipants_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
