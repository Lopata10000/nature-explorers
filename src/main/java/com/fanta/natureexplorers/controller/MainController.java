package com.fanta.natureexplorers.controller;

import com.fanta.natureexplorers.controller.tablecontroller.ExcursionController;
import com.fanta.natureexplorers.controller.tablecontroller.ExcursionParticipantController;
import com.fanta.natureexplorers.controller.tablecontroller.ReviewController;
import com.fanta.natureexplorers.controller.tablecontroller.ManagerController;
import com.fanta.natureexplorers.controller.tablecontroller.TripController;
import com.fanta.natureexplorers.controller.tablecontroller.TripParticipantController;
import com.fanta.natureexplorers.controller.tablecontroller.UserController;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The type Main controller.
 */
public class MainController {
    @FXML private BorderPane mainApp;
    @FXML private Button registrationButton;
    @FXML private Button authorizationButton;

    /**
     * Instantiates a new Main controller.
     */
    public MainController() {}

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        authorizationButton.setOnAction(event -> authorizationWindow());
        registrationButton.setOnAction(event -> registrationWindow());
    }

    /**
     * Authorization window.
     */
    public void authorizationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/fxml/Authorization.fxml"));
            AnchorPane authorizationPane = loader.load();

            AuthorizationController authorizationController = loader.getController();
            authorizationController.setMainController(this);

            mainApp.setCenter(authorizationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registration window.
     */
    public void registrationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/fxml/Registration.fxml"));
            AnchorPane registrationPane = loader.load();

            RegistrationController registrationController = loader.getController();
            registrationController.setMainController(this);

            mainApp.setCenter(registrationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Data base window.
     */
    public void dataBaseWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/LeftList.fxml"));
            Pane dataBasePane = loader.load();

            LeftController leftController = loader.getController();
            leftController.setMainController(this);

            mainApp.setLeft(dataBasePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * User window.
     */
    public void userWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/UserTable.fxml"));
            AnchorPane userController = loader.load();

            UserController userController1 = loader.getController();
            userController1.setMainController(this);

            mainApp.setCenter(userController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Budget window.
     */
    public void managerWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/ManagerTable.fxml"));
            AnchorPane mangerController = loader.load();

            ManagerController mangerController1 = loader.getController();
            mangerController1.setMainController(this);

            mainApp.setCenter(mangerController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    /**
     * Transaction window.
     */
    public void reviewWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/ReviewTable.fxml"));
            AnchorPane reviewController = loader.load();

            ReviewController reviewController1 = loader.getController();
            reviewController1.setMainController(this);

            mainApp.setCenter(reviewController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exchange rate window.
     */
    public void tripWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/TripTable.fxml"));
            AnchorPane tripController = loader.load();

            TripController tripController1 = loader.getController();
            tripController1.setMainController(this);

            mainApp.setCenter(tripController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cost window.
     */
    public void tripParticipantsWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/TripParticipantTable.fxml"));
            AnchorPane tripParticipantController1 = loader.load();

            TripParticipantController tripParticipantController = loader.getController();
            tripParticipantController.setMainController(this);

            mainApp.setCenter(tripParticipantController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Earning window.
     */
    public void excursionWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/ExcursionTable.fxml"));
            AnchorPane excursionController1 = loader.load();

            ExcursionController excursionController = loader.getController();
            excursionController.setMainController(this);

            mainApp.setCenter(excursionController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Planning cost window.
     */
    public void excursionParticipantsWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/fxml/database/ExcursionParticipantTable.fxml"));
            AnchorPane excursionParticipantController1 = loader.load();

            ExcursionParticipantController excursionParticipantController = loader.getController();
            excursionParticipantController.setMainController(this);

            mainApp.setCenter(excursionParticipantController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reset left pane.
     */
    public void resetLeftPane() {
        try {
            FXMLLoader leftLoader =
                    new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Pane leftPane = leftLoader.load();

            mainApp.setLeft(leftPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main window.
     */
    public void mainWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            BorderPane mainBorderPane = loader.load();

            // assuming mainApp is currently displayed
            mainApp.getScene().setRoot(mainBorderPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
