package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.Trip;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TripDao {
    private Session session;

    public TripDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public Trip getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Trip trip = session.get(Trip.class, id);
        transaction.commit();
        return trip;
    }

    public void save(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(trip);
        transaction.commit();
    }

    public void update(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(trip);
        transaction.commit();
    }

    public void delete(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(trip);
        transaction.commit();
    }

    public List<Trip> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Trip> trips = session.createQuery("SELECT t FROM Trip t", Trip.class).getResultList();
        transaction.commit();
        return trips;
    }
}
