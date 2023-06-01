package com.fanta.natureexplorers.controller.authentication;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AuthorizationController implements Initializable {

    private final UserService userService = new UserService();

    private MainController mainController;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;


    public AuthorizationController(MainController mainController) {
        this.mainController = mainController;
    }

    public AuthorizationController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

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
                        Properties properties = new Properties();
                        properties.setProperty("id", String.valueOf(user.getUserId()));
                        String filePath = System.getProperty("user.dir") + "/file.properties";

                        try (FileOutputStream output = new FileOutputStream(filePath)) {
                            properties.store(output, null);
                        } catch (Exception e) {
                        }
                        Platform.runLater(
                                () -> {
                                    if (user.getRole() == UserRole.USER) {
                                        userService.successfulAuthorization();
                                        mainController.userActionWindow();
                                    } else if (user.getRole() == UserRole.ADMIN) {
                                        userService.successfulAuthorization();
                                        mainController.dataBaseWindow();
                                    } else if (user.getRole() == UserRole.MANAGER) {
                                        userService.successfulAuthorization();
                                        mainController.managerActionWindow();
                                    }
                                });
                    } else {
                        Platform.runLater(
                                () ->
                                    userService.authorizationFailed()
                                );
                    }
                });
        executor.shutdown();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
