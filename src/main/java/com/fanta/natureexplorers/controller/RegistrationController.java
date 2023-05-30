package com.fanta.natureexplorers.controller;

import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * The type Registration controller.
 */
public class RegistrationController implements Initializable {
    private MainController mainController;

    /**
     * Instantiates a new Registration controller.
     */
    public RegistrationController() {}

    /**
     * Instantiates a new Registration controller.
     *
     * @param mainController the main controller
     */
    public RegistrationController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    private final UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /**
     * Create user.
     */
    public void createUser() {
        User user = new User( firstNameTextField.getText(),
                lastNameTextField.getText(),
                emailTextField.getText(),
                passwordTextField.getText(),
                UserRole.USER);
        userService.save(user);
        if (user != null) {
            userService.successfulAuthorization();
            mainController.dataBaseWindow();
        }
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
