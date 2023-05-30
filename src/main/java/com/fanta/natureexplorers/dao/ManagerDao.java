package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.Manager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManagerDao {
    private Session session;

    public ManagerDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public Manager getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Manager manager = session.get(Manager.class, id);
        transaction.commit();
        return manager;
    }

    public void save(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(manager);
        transaction.commit();
    }

    public void update(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(manager);
        transaction.commit();
    }

    public void delete(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(manager);
        transaction.commit();
    }

    public List<Manager> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Manager> managers =
                session.createQuery("SELECT m FROM Manager m", Manager.class).getResultList();
        transaction.commit();
        return managers;
    }
}
