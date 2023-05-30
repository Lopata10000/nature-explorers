package com.fanta.natureexplorers.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** The type Hibernate. */
public class Hibernate {
    /** The constant sessionFactory. */
    public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //    public static void main(String[] args) {
    //        User user = new User();
    //        user.setLastName("john");
    //        user.setPassword("password");
    //        user.setFirstName("John");
    //        user.setLastName("Doe");
    //        user.setEmail("john.doe@example.com");
    //
    //// Створення сервісу користувача
    //        UserService userService = new UserService();
    //
    //// Збереження користувача в базу даних
    //        userService.save(user);
    //    }
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
}
