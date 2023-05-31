package com.fanta.natureexplorers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "excursion_participants")
public class ExcursionParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull(message = "User cannot be null")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @NotNull(message = "Excursion cannot be null")
    @JoinColumn(name = "excursion_id", referencedColumnName = "excursion_id")
    private Excursion excursion;

    public ExcursionParticipant(User user, Excursion excursion) {
        this.user = user;
        this.excursion = excursion;
    }

    public ExcursionParticipant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
}
