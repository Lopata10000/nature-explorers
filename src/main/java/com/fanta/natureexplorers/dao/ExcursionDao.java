package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.Excursion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ExcursionDao{
    private Session session;

    public ExcursionDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public Excursion getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Excursion excursion = session.get(Excursion.class, id);
        transaction.commit();
        return excursion;
    }

    public void save(Excursion excursion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(excursion);
        transaction.commit();
    }

    public void update(Excursion excursion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(excursion);
        transaction.commit();
    }

    public void delete(Excursion excursion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(excursion);
        transaction.commit();
    }

    public List<Excursion> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Excursion> excursions =
                session.createQuery("SELECT e FROM Excursion e", Excursion.class).getResultList();
        transaction.commit();
        return excursions;
    }
}
