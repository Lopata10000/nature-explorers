package com.fanta.natureexplorers.service;

import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.User;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import javafx.scene.control.Alert;
import javax.persistence.NoResultException;

public class UserService implements ServiceInterface<User> {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    @Override
    public User getById(Integer id) {
        try {
            return userDao.getById(id);
        } catch (Exception exception) {
            showErrorMessage("Користувача з таким ідентифікатором не знайдено");
        }
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void save(User entity) {
        validateAndSave(entity);
        userDao.save(entity);
        Properties properties = new Properties();
        properties.setProperty("id", String.valueOf(entity.getUserId()));
        String filePath = System.getProperty("user.dir") + "/file.properties";

        try (FileOutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, null);
        } catch (Exception e) {
        }
    }

    @Override
    public void update(Integer id, User entity) {
        validateAndUpdate(id, entity);
        User existingUser = userDao.getById(id);
        if (existingUser != null) {
            entity.setUserId(existingUser.getUserId());
            userDao.update(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        User user = userDao.getById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    public void getByEmailAndPassword(String email, String password) {

        try {
            userDao.findUserByEmailAndPassword(email, password);
        } catch (NoResultException e) {
            showErrorMessage("Не знайдено такого користувача");
        }
    }

    public void findByEmail(String email) {
        if (userDao.findByEmail(String.valueOf(true))) {
            showErrorMessage("Користувач з такою електронною поштою вже існує");
        }
    }

    public void successfulAuthorization() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успіх");
        alert.setHeaderText("Успішний вхід");
        alert.setContentText("Ви успішно увійшли!");
        alert.showAndWait();
    }

    public void authorizationFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText("Помилка авторизації");
        alert.setContentText("Невірна електронна адреса або пароль!");
        alert.showAndWait();
    }
}
