package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.validator.ErrorMessage;
import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.TripDao;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.TripParticipant;
import com.fanta.natureexplorers.service.TripParticipantService;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * The type Cost category controller.
 */
public class TripParticipantController extends ErrorMessage implements Initializable {
    @FXML private TableView<TripParticipant> tripParticipantTable;
    @FXML private TextField userId;
    @FXML private TextField tripId;
    @FXML private TextField findByIdField;

    private final TripParticipantService tripParticipantService = new TripParticipantService();

    /**
     * Create trip participant.
     */
    @FXML
    public void createTripParticipant() {
            Integer userID = Integer.valueOf(userId.getText());
            Integer tripID = Integer.valueOf(tripId.getText());
            UserDao userDao = new UserDao();
            TripDao tripDao = new TripDao();
            if(userDao.getById(userID) == null)
            {
                showErrorMessage("Такого користувача не існує");
            }
            else {
                TripParticipant tripParticipant = new TripParticipant(userDao.getById(userID), tripDao.getById(tripID));
                tripParticipantService.save(tripParticipant);
                refreshTable();
            }
    }

    /**
     * Update trip participant.
     */
    @FXML
    public void updateTripParticipant() {
            TripParticipant selectedTripParticipant =
                    tripParticipantTable.getSelectionModel().getSelectedItem();
            Integer tripParticipantId =
                    Integer.parseInt(String.valueOf(selectedTripParticipant.getTripParticipantsId()));
            Integer userID = Integer.valueOf(userId.getText());
            Integer tripID = Integer.valueOf(tripId.getText());
            UserDao userDao = new UserDao();
            TripDao tripDao = new TripDao();
        if(userDao.getById(userID) == null)
        {
            showErrorMessage("Такого користувача не існує");
        }
        else {
            TripParticipant tripParticipant = new TripParticipant(userDao.getById(userID), tripDao.getById(tripID));
            tripParticipantService.update(tripParticipantId, tripParticipant);
            refreshTable();
        }
    }

    /**
     * Delete trip participant.
     */
    @FXML
    public void deleteTripParticipant() {
        try {
            TripParticipant selectedTripParticipant =
                    tripParticipantTable.getSelectionModel().getSelectedItem();
            Integer tripParticipantId =
                    Integer.parseInt(String.valueOf(selectedTripParticipant.getTripParticipantsId()));
            tripParticipantService.delete(tripParticipantId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    /**
     * Search trip participant.
     */
    @FXML
    void searchTripParticipant() {
            tripParticipantTable.getItems().clear();
            String tripParticipantIdText = findByIdField.getText();
            Integer tripParticipantId = Integer.parseInt(tripParticipantIdText);
            TripParticipant tripParticipants = tripParticipantService.getById(tripParticipantId);
            tripParticipantTable.getItems().add(tripParticipants);
            if (tripParticipants == null) {
                showAlert("Нічого не знайдено");
                refreshTable();
            }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTableView();
        refreshTable();
    }

    @FXML
    private void refreshTable() {
        List<TripParticipant> tripParticipants = tripParticipantService.getAll();
        // Очистити таблицю перед додаванням нових даних
        tripParticipantTable.getItems().clear();

        // Додати користувачів до таблиці
        tripParticipantTable.getItems().addAll(tripParticipants);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            TripParticipant selectedTripParticipant =
                    tripParticipantTable.getSelectionModel().getSelectedItem();

            if (selectedTripParticipant != null) {
                tripId.setText(
                        String.valueOf(selectedTripParticipant.getTrip().getTripId()));
                userId.setText(
                        String.valueOf(selectedTripParticipant.getUser().getUserId()));
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "trip_participants", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<TripParticipant, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі TripParticipant
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                tripParticipantTable.getColumns().add(column);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertColumnNameToVariableName(String columnName) {
        // Розділяємо назву стовпця по символу "_"
        String[] words = columnName.split("_");
        StringBuilder variableName = new StringBuilder();

        for (String word : words) {
            // Замінюємо першу літеру на заголовну
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            variableName.append(capitalizedWord);
        }

        return variableName.toString();
    }

    /**
     * Instantiates a new Cost category controller.
     */
    public TripParticipantController() {}

    /**
     * Instantiates a new Cost category controller.
     *
     * @param mainController the main controller
     */
    public TripParticipantController(MainController mainController) {}

    /**
     * Sets main controller.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {}
}
