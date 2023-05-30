package com.fanta.natureexplorers;

import static com.fanta.natureexplorers.database.Hibernate.sessionFactory;

import com.fanta.natureexplorers.entity.Excursion;
import com.fanta.natureexplorers.entity.Manager;
import com.fanta.natureexplorers.entity.Review;
import com.fanta.natureexplorers.entity.Trip;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.ManagerStatus;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {
    @FXML Button data;

    public void dataclick() {
//        // Приклад додавання записів до кожної таблиці
//
//// Створення об'єктів з використанням конструкторів
//        User user = new User("John", "", "johndoexample.com", "sword");
//
//        Manager manager = new Manager(user);
//
//        Trip trip = new Trip("Nature Exploration", "Join us for an exciting nature exploration trip.",
//                LocalDate.now(), LocalDate.now().plusDays(7), "Nature Reserve", manager);
//
//        Excursion excursion = new Excursion("Hiking Adventure", "",
//                LocalDate.now(), "", manager);
//
//        Review review = new Review(5, "The", user, trip, excursion);
//
//// Збереження записів в базі даних за допомогою Hibernate
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//UserService userService = new UserService();
//
//        userService.save(user);
//
//        transaction.commit();
//        session.close();


    }
}
