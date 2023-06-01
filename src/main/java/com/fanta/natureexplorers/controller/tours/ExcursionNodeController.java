package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.entity.Excursion;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ExcursionNodeController {
    private MainController mainController;
        @FXML
        private Label nameLabel;

        @FXML
        private Label dateLabel;

        @FXML
        private Label descriptionLabel;

        @FXML
        private Label locationLabel;

        public void setData (Excursion excursion) {
            nameLabel.setText("Name: " + excursion.getName());
            dateLabel.setText("Date: " + excursion.getDate().toString());
            descriptionLabel.setText("Description: " + excursion.getDescription());
            locationLabel.setText("Location: " + excursion.getLocation());
        }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public ExcursionNodeController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Instantiates a new Authorization controller.
     */
    public ExcursionNodeController() {}


}

