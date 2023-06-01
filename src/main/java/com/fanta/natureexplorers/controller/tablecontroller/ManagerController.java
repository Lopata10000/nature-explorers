package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.Manager;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.ManagerStatus;
import com.fanta.natureexplorers.service.ManagerService;
import com.fanta.natureexplorers.validator.ErrorMessage;
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

public class ManagerController extends ErrorMessage implements Initializable {
    @FXML private TableView<Manager> managerTable;
    @FXML private TextField managerStatus;
    @FXML private TextField userId;
    @FXML private TextField findByIdField;

    private final ManagerService managerService = new ManagerService();

    @FXML
    public void createManager() {
        try {

            Integer userID = Integer.valueOf(userId.getText());
            UserDao userDAO = new UserDao();
            User user = userDAO.getById(userID);
            if (user == null) {
                showAlert("Користувача з таким id не існує");
            } else {
                Manager manager = new Manager(user, ManagerStatus.valueOf(managerStatus.getText()));
                managerService.save(manager);
                refreshTable();
            }
        } catch (IllegalArgumentException exception) {
            showAlert("Не валідний статус (ACTIVE; PASSIVE)");
        }
    }

    @FXML
    public void updateManager() {
        try {
            Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();
            Integer userID = Integer.valueOf(userId.getText());
            UserDao userDAO = new UserDao();
            User user = userDAO.getById(userID);
            if (user == null) {
                showAlert("Користувача з таким id не існує");
            } else {
                Manager manager = new Manager(user);
                managerService.update(Integer.valueOf(userId.getText()), manager);
                refreshTable();
            }
        } catch (IllegalArgumentException exception) {
            showAlert("Не валідний статус (ACTIVE; PASSIVE)");
        }
    }

    @FXML
    public void deleteManager() {
        Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();
        try {
            Integer managerId = Integer.parseInt(String.valueOf(selectedManager.getManagerId()));
            managerService.delete(managerId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    @FXML
    void searchManager() {
        managerTable.getItems().clear();
        String managerIdText = findByIdField.getText();
        Integer managerId = Integer.parseInt(managerIdText);
        Manager managers = managerService.getById(managerId);
        if (managers == null) {
            showAlert("Такого керівника не знайдено");
            refreshTable();
        } else {
            managerTable.getItems().add(managers);
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
        List<Manager> managers = managerService.getAll();
        // Очистити таблицю перед додаванням нових даних
        managerTable.getItems().clear();

        // Додати користувачів до таблиці
        managerTable.getItems().addAll(managers);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();

            if (selectedManager != null) {
                userId.setText(String.valueOf(selectedManager.getUser().getUserId()));
                managerStatus.setText(String.valueOf(selectedManager.getStatus()));
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "managers", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<Manager, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі Manager
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                managerTable.getColumns().add(column);
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

    public ManagerController() {}

    public ManagerController(MainController mainController) {}

    public void setMainController(MainController mainController) {}

    Alert alert = new Alert(Alert.AlertType.ERROR);
}
