package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.TripParticipant;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TripParticipantDao {
    private Session session;

    public TripParticipantDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public TripParticipant getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TripParticipant tripParticipant = session.get(TripParticipant.class, id);
        transaction.commit();
        return tripParticipant;
    }

    public void save(TripParticipant tripParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tripParticipant);
        transaction.commit();
    }

    public void update(TripParticipant tripParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(tripParticipant);
        transaction.commit();
    }

    public void delete(TripParticipant tripParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tripParticipant);
        transaction.commit();
    }

    public List<TripParticipant> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<TripParticipant> tripParticipants =
                session.createQuery("SELECT tp FROM TripParticipant tp", TripParticipant.class)
                        .getResultList();
        transaction.commit();
        return tripParticipants;
    }
}
