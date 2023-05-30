package com.fanta.natureexplorers.dao;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.Review;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ReviewDao {
    private Session session;

    public ReviewDao() {
        session = new Configuration().configure().buildSessionFactory().openSession();
    }

    public Review getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Review review = session.get(Review.class, id);
        transaction.commit();
        return review;
    }

    public void save(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(review);
        transaction.commit();
    }

    public void update(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(review);
        transaction.commit();
    }

    public void delete(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(review);
        transaction.commit();
    }

    public List<Review> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Review> reviews =
                session.createQuery("SELECT r FROM Review r", Review.class).getResultList();
        transaction.commit();
        return reviews;
    }
}
