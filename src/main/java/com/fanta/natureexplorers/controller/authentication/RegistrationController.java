package com.fanta.natureexplorers.controller.authentication;

import com.fanta.natureexplorers.validator.ErrorMessage;
import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * The type Registration controller.
 */
public class RegistrationController extends ErrorMessage implements Initializable {
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
            User user = new User(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    passwordTextField.getText(),
                    UserRole.ADMIN
            );
        try{
            userService.save(user);
        }catch (Exception exception){
            showErrorMessage("Користувач з такою електронною адресою існує");
        }
        if (user != null && user.getRole() == UserRole.USER) {
            userService.successfulAuthorization();
            mainController.userActionWindow();
        }
        else if(user != null && user.getRole() == UserRole.ADMIN) {
            userService.successfulAuthorization();
            mainController.dataBaseWindow();
        }
        else if (user != null && user.getRole() == UserRole.MANAGER) {
            userService.successfulAuthorization();
            mainController.excursionTourWindow();
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