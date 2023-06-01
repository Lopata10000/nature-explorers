package com.fanta.natureexplorers.controller.main;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/** The type Left controller. */
public class LeftController implements Initializable {

    @FXML JFXButton backButton;

    @FXML JFXButton usersTableButton;

    @FXML JFXButton managerTableButton;

    @FXML JFXButton excursionTableButton;

    @FXML JFXButton excursionParticipantsTableButton;

    @FXML JFXButton tripTableButton;

    @FXML JFXButton tripParticipantsTableButton;

    private MainController mainController; // Додано приватне поле mainController

    /**
     * Instantiates a new Left controller.
     *
     * @param mainController the main controller
     */
    public LeftController(MainController mainController) {
        this.mainController = mainController;
    }

    /** Instantiates a new Left controller. */
    public LeftController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction(event -> backToMainWindow());
    }

    /** Back to main window. */
    public void backToMainWindow() {
        mainController.mainWindow();
    }

    /**
     * Sets main controller.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void usersTable() {
        mainController.userWindow();
    }

    public void managerTable() {
        mainController.managerWindow();
    }

    public void excursionTable() {
        mainController.excursionWindow();
    }

    public void excursionParticipantsTable() {
        mainController.excursionParticipantsWindow();
    }

    public void tripTable() {
        mainController.tripWindow();
    }

    public void tripParticipantsCostsTable() {
        mainController.tripParticipantsWindow();
    }
}
