package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.enumrole.UserRole;
import com.fanta.natureexplorers.service.UserService;
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

public class UserController extends ErrorMessage implements Initializable {
    @FXML private TableView<User> userTable;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private TextField userStatusField;
    @FXML private TextField findByIdField;

    private final UserService userService = new UserService();

    @FXML
    public void createUser() {
        try {
            try {
                User user =
                        new User(
                                firstNameField.getText(),
                                lastNameField.getText(),
                                emailField.getText(),
                                passwordField.getText(),
                                UserRole.valueOf(userStatusField.getText()));
                UserDao userDao = new UserDao();
                userDao.findByEmail(emailField.getText());
                if (userDao.findByEmail(emailField.getText()) == true) {
                    showAlert("Користувач з такою електронною адресою існує");
                } else userService.save(user);
                refreshTable();

            } catch (IllegalArgumentException exception) {
                showAlert("Не валідний статус(USER; ADMIN; MANAGER)");
            }
        } catch (RuntimeException exception) {
            showAlert("Корстувач з такою електронною адресою існує");
        }
    }

    @FXML
    public void updateUser() {
        try {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            Integer userId = Integer.parseInt(String.valueOf(selectedUser.getUserId()));
            User user =
                    new User(
                            firstNameField.getText(),
                            lastNameField.getText(),
                            emailField.getText(),
                            passwordField.getText(),
                            UserRole.valueOf(userStatusField.getText()));
            UserDao userDao = new UserDao();
            if (userDao.findByEmail(emailField.getText()) != true) {
                userService.update(userId, user);
                refreshTable();
            } else showAlert("Корстувач з такою електронною адресою існує");
        } catch (RuntimeException exception) {
            showAlert("Корстувач з такою електронною адресою існує");
        }
    }

    @FXML
    public void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        try {
            Integer userId = Integer.parseInt(String.valueOf(selectedUser.getUserId()));
            userService.delete(userId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    @FXML
    void searchUser() {
        userTable.getItems().clear();
        // Додати користувачів до таблиці
        String userIdText = findByIdField.getText();
        Integer userId = Integer.parseInt(userIdText);
        User users = userService.getById(userId);
        if (users == null) {
            showAlert("Такого користувача не знайдено");
            refreshTable();
        }
        userTable.getItems().add(users);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTableView();
        refreshTable();
    }

    @FXML
    private void refreshTable() {
        List<User> users = userService.getAll();
        // Очистити таблицю перед додаванням нових даних
        userTable.getItems().clear();

        // Додати користувачів до таблиці
        userTable.getItems().addAll(users);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();

            if (selectedUser != null) {
                firstNameField.setText(selectedUser.getFirstName());
                lastNameField.setText(selectedUser.getLastName());
                emailField.setText(selectedUser.getEmail());
                passwordField.setText(selectedUser.getPassword());
                userStatusField.setText(String.valueOf(selectedUser.getRole()));
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "users", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<User, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі User
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                userTable.getColumns().add(column);
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

    public UserController() {}

    public UserController(MainController mainController) {}


    public void setMainController(MainController mainController) {}
}
