package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.ExcursionParticipant;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ExcursionParticipantDao {
    private Session session;

    public ExcursionParticipantDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public ExcursionParticipant getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ExcursionParticipant excursionParticipant = session.get(ExcursionParticipant.class, id);
        transaction.commit();
        return excursionParticipant;
    }

    public void save(ExcursionParticipant excursionParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(excursionParticipant);
        transaction.commit();
    }

    public void update(ExcursionParticipant excursionParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(excursionParticipant);
        transaction.commit();
    }

    public void delete(ExcursionParticipant excursionParticipant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(excursionParticipant);
        transaction.commit();
    }

    public List<ExcursionParticipant> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<ExcursionParticipant> excursionParticipants =
                session.createQuery(
                                "SELECT ep FROM ExcursionParticipant ep",
                                ExcursionParticipant.class)
                        .getResultList();
        transaction.commit();
        return excursionParticipants;
    }
}
