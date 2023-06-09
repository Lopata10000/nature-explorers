package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.ExcursionDao;
import com.fanta.natureexplorers.dao.ExcursionParticipantDao;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.Excursion;
import com.fanta.natureexplorers.entity.ExcursionParticipant;
import com.fanta.natureexplorers.validator.ErrorMessage;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExcursionNodeController extends ErrorMessage {
    private MainController mainController;
    @FXML private Button registrateInTure;
    @FXML private Label nameLabel;

    @FXML private Label dateLabel;

    @FXML private Label descriptionLabel;

    @FXML private Label locationLabel;

    public void setData(Excursion excursion) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        registrateInTure.setUserData(String.valueOf(excursion.getExcursionId()));
        nameLabel.setText("Name: " + excursion.getName());
        dateLabel.setText("Date: " + excursion.getDate().toString());
        descriptionLabel.setText("Description: " + excursion.getDescription());
        locationLabel.setText("Location: " + excursion.getLocation());
    }

    public void registrationInTour() {
        Properties properties = new Properties();

        String filePath = System.getProperty("user.dir") + "/file.properties";

        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (Exception e) {
        }

        ExcursionDao excursionDao = new ExcursionDao();
        UserDao userDao = new UserDao();
        ExcursionParticipantDao excursionParticipantDao = new ExcursionParticipantDao();
        ExcursionParticipant excursionParticipant =
                new ExcursionParticipant(
                        userDao.getById(Integer.valueOf(properties.getProperty("id"))),
                        excursionDao.getById(
                                Integer.valueOf(String.valueOf(registrateInTure.getUserData()))));
        excursionParticipantDao.save(excursionParticipant);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Успішно");
        info.setTitle("Вас зареєстровано");
        info.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Інформація");
        alert.setHeaderText(null);
        alert.setContentText(message);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ExcursionNodeController(MainController mainController) {
        this.mainController = mainController;
    }

    public ExcursionNodeController() {}
}
