package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.ManagerDao;
import com.fanta.natureexplorers.entity.Manager;
import com.fanta.natureexplorers.entity.Trip;
import com.fanta.natureexplorers.service.TripService;
import com.fanta.natureexplorers.validator.ErrorMessage;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TripController extends ErrorMessage implements Initializable {
    @FXML private TableView<Trip> tripTable;
    @FXML private TextField managerId;
    @FXML private TextField nameTrip;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField descriptionTrip;
    @FXML private TextField locationTrip;
    @FXML private TextField findByIdField;
    private final TripService tripService = new TripService();

    @FXML
    public void createTrip() {
        Integer managerID = Integer.valueOf(managerId.getText());
        ManagerDao managerDao = new ManagerDao();
        Manager manager = managerDao.getById(managerID);
        if (manager == null) {
            showAlert("Користувача з таким id не існує");
            alert.showAndWait();
        } else {
            String tripName = nameTrip.getText();
            String tripDescription = descriptionTrip.getText();
            LocalDate dateStart = startDate.getValue();
            LocalDate endStart = endDate.getValue();
            String tripLocation = locationTrip.getText();
            Trip trip =
                    new Trip(tripName, tripDescription, dateStart, endStart, tripLocation, manager);
            tripService.save(trip);
            refreshTable();
        }
    }

    @FXML
    public void updateTrip() {
        Trip selectedTrip = tripTable.getSelectionModel().getSelectedItem();
        Integer tripID = Integer.parseInt(String.valueOf(selectedTrip.getTripId()));
        Integer managerID = Integer.parseInt(managerId.getText());
        ManagerDao managerDao = new ManagerDao();
        Manager manager = managerDao.getById(managerID);
        if (manager == null) {
            showAlert("Користувача з таким id не існує");
            alert.showAndWait();
        } else {
            String tripName = nameTrip.getText();
            String tripDescription = descriptionTrip.getText();
            LocalDate dateStart = startDate.getValue();
            LocalDate endStart = endDate.getValue();
            String tripLocation = locationTrip.getText();
            Trip trip =
                    new Trip(tripName, tripDescription, dateStart, endStart, tripLocation, manager);
            tripService.update(tripID, trip);
            refreshTable();
        }
    }

    @FXML
    public void deleteTrip() {
        Trip selectedTrip = tripTable.getSelectionModel().getSelectedItem();
        try {
            Integer tripId = Integer.parseInt(String.valueOf(selectedTrip.getTripId()));
            tripService.delete(tripId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    @FXML
    void searchTrip() {
        tripTable.getItems().clear();
        String tripIdText = findByIdField.getText();
        Integer tripId = Integer.parseInt(tripIdText);
        Trip trips = tripService.getById(tripId);
        tripTable.getItems().add(trips);
        if (trips == null) {
            showAlert("Такого походу не знайдено");
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
        List<Trip> trips = tripService.getAll();
        // Очистити таблицю перед додаванням нових даних
        tripTable.getItems().clear();

        // Додати користувачів до таблиці
        tripTable.getItems().addAll(trips);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Trip selectedTrip = tripTable.getSelectionModel().getSelectedItem();

            if (selectedTrip != null) {
                managerId.setText(String.valueOf(selectedTrip.getManager().getManagerId()));
                nameTrip.setText(String.valueOf(selectedTrip.getName()));
                descriptionTrip.setText(String.valueOf(selectedTrip.getDescription()));
                locationTrip.setText(String.valueOf(selectedTrip.getLocation()));
                endDate.setValue(selectedTrip.getEndDate());
                startDate.setValue(selectedTrip.getStartDate());
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "trips", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<Trip, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі Trip
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                tripTable.getColumns().add(column);
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

    public TripController() {}

    public TripController(MainController mainController) {}

    public void setMainController(MainController mainController) {}
}
