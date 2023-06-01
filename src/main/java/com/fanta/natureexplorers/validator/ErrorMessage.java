package com.fanta.natureexplorers.validator;

import javafx.scene.control.Alert;

public abstract class ErrorMessage {
    protected Alert alert = new Alert(Alert.AlertType.ERROR);
     protected void showErrorMessage(String message) {
        alert.setTitle("Помилка валідації");
        alert.setHeaderText("Будь ласка, виправте наступні помилки:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
