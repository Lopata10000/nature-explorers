package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.TripDao;
import com.fanta.natureexplorers.dao.TripParticipantDao;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.Trip;
import com.fanta.natureexplorers.entity.TripParticipant;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TripNodeController {
    private MainController mainController;
    @FXML private Button registrateInTure;
    @FXML private Label nameLabel;
    @FXML private Label startDate;
    @FXML private Label endDate;

    @FXML private Label descriptionLabel;

    @FXML private Label locationLabel;

    public void setData(Trip trip) {
        registrateInTure.setUserData(String.valueOf(trip.getTripId()));
        nameLabel.setText("Name: " + trip.getName());
        startDate.setText("Date: " + trip.getStartDate().toString());
        startDate.setText("Date: " + trip.getEndDate().toString());
        descriptionLabel.setText("Description: " + trip.getDescription());
        locationLabel.setText("Location: " + trip.getLocation());
    }

    public void registrationInTour() {
        Properties properties = new Properties();

        String filePath = System.getProperty("user.dir") + "/file.properties";

        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (Exception e) {
        }
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TripParticipantDao tripParticipantDao = new TripParticipantDao();
        TripParticipant tripParticipant =
                new TripParticipant(
                        userDao.getById(Integer.valueOf(properties.getProperty("id"))),
                        tripDao.getById(
                                Integer.valueOf(String.valueOf(registrateInTure.getUserData()))));
        tripParticipantDao.save(tripParticipant);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Успішно");
        info.setTitle("Вас зареєстровано");
        info.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Інформація");
        alert.setHeaderText(null);
        alert.setContentText(message);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public TripNodeController(MainController mainController) {
        this.mainController = mainController;
    }

    public TripNodeController() {}
}
