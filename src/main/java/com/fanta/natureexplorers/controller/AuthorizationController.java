package com.fanta.natureexplorers.controller;

import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * The type Authorization controller.
 */
public class AuthorizationController implements Initializable {

    private final UserService userService = new UserService();
    private MainController mainController;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;

    /**
     * Instantiates a new Authorization controller.
     *
     * @param mainController the main controller
     */
    public AuthorizationController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Instantiates a new Authorization controller.
     */
    public AuthorizationController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * Authorization.
     */
    @FXML
    public void authorization() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(
                () -> {
                    String email = emailTextField.getText();
                    String password = passwordTextField.getText();
                    UserDao userDAO = new UserDao();
                    User user = userDAO.findUserByEmailAndPassword(email, password);
                    if (user != null) {
                        Platform.runLater(
                                () -> {
                                    userService.successfulAuthorization();
                                    mainController.dataBaseWindow();
                                });
                    } else {
                        Platform.runLater(
                                () -> {
                                    userService.authorizationFailed();
                                });
                    }
                });
        executor.shutdown();
    }

    /**
     * Sets main controller.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
