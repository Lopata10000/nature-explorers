package com.fanta.natureexplorers.controller.authentication;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;
import com.fanta.natureexplorers.validator.ErrorMessage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class RegistrationController extends ErrorMessage implements Initializable {
    private MainController mainController;

    public RegistrationController() {}

    public RegistrationController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    private final UserService userService = new UserService();
    private final UserDao userDao = new UserDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    /** Create user. */
    public void createUser() {
        User user =
                new User(
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        emailTextField.getText(),
                        passwordTextField.getText(),
                        UserRole.USER);
            if (userDao.findByEmail(user.getEmail()) != true) {
                userService.save(user);
                if (user.getRole() == UserRole.USER) {
                    userService.successfulAuthorization();
                    mainController.userActionWindow();
                }
                if (user.getRole() == UserRole.ADMIN) {
                    userService.successfulAuthorization();
                    mainController.dataBaseWindow();
                }
                if (user.getRole() == UserRole.MANAGER) {
                    userService.successfulAuthorization();
                    mainController.managerActionWindow();
                }
            }
            else
                showErrorMessage("Користувач з такою електронною адресою існує");
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
