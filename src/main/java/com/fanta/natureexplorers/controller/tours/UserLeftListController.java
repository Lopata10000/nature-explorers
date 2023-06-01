package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserLeftListController {
    @FXML private Button excursionButton;
    @FXML private Button tripButton;
    private MainController mainController;

    public void trips() {
        mainController.tripToursWindow();
    }

    public void excursions() {
        mainController.excursionTourWindow();
    }

    public void backToMainWindow() {
        mainController.mainWindow();
    }

    public UserLeftListController(MainController mainController) {
        this.mainController = mainController;
    }

    public UserLeftListController() {}

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
