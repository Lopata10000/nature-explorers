package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.MainController;
import com.fanta.natureexplorers.dao.ManagerDao;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.Excursion;
import com.fanta.natureexplorers.entity.Manager;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.service.ExcursionService;
import com.fanta.natureexplorers.entity.Excursion;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
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
/**
 * Клас контролера для управління бюджетами.
 * Реалізує інтерфейс Initializable для ініціалізації контролера при завантаженні FXML-файлу.
 *
 * @author fanta
 * @version 1.0
 */
public class ExcursionController implements Initializable {
    @FXML
    private TableView<Excursion> excursionTable;
    @FXML
    private TextField managerId;
    @FXML
    private TextField excursionName;
    @FXML
    private TextField excursionDescription;
    @FXML
    private TextField excursionLocation;
    @FXML
    private DatePicker excursionDate;
    @FXML
    private TextField findByIdField;

    private final ExcursionService excursionService = new ExcursionService();

    /**
     * Метод для створення екскурсії.
     * Викликається при натисканні на кнопку "Створити бюджет".
     */
    @FXML
    public void createExcursion() {
            Integer managerID = Integer.valueOf(managerId.getText());
            ManagerDao managerDao = new ManagerDao();
            Manager manager = managerDao.getById(managerID);
            if (manager == null) {
                showAlert("Користувача з таким id не існує");
            }
            String excursionNameString = excursionName.getText();
            LocalDate excursionDateValue = excursionDate.getValue();
            String excursionLocationText = excursionLocation.getText();
            String excursionDescriptionText = excursionDescription.getText();
            Excursion excursion = new Excursion(excursionNameString, excursionDescriptionText, excursionDateValue, excursionLocationText, manager);
            excursionService.save(excursion);
            refreshTable();
    }

    /**
     * Метод для оновлення екскурсії.
     * Викликається при натисканні на кнопку "Оновити бюджет".
     */
    @FXML
    public void updateExcursion() {
            Excursion selectedExcursion = excursionTable.getSelectionModel().getSelectedItem();
            Integer excursionId = Integer.parseInt(String.valueOf(selectedExcursion.getExcursionId()));
            Integer managerID = Integer.valueOf(managerId.getText());
            ManagerDao managerDao = new ManagerDao();
            Manager manager = managerDao.getById(managerID);
            if (manager == null) {
                showAlert("Користувача з таким id не існує");
            }
            String excursionNameString = excursionName.getText();
            LocalDate excursionDateValue = excursionDate.getValue();
            String excursionLocationText = excursionLocation.getText();
            String excursionDescriptionText = excursionDescription.getText();
            Excursion excursion = new Excursion(excursionNameString, excursionDescriptionText, excursionDateValue, excursionLocationText, manager);
            excursionService.update(excursionId, excursion);
            refreshTable();

    }

    /**
     * Метод для видалення екскурсії.
     * Викликається при натисканні на кнопку "Видалити бюджет".
     */
    @FXML
    public void deleteExcursion() {
        Excursion selectedExcursion = excursionTable.getSelectionModel().getSelectedItem();
        try {
            Integer excursionId = Integer.parseInt(String.valueOf(selectedExcursion.getExcursionId()));
            excursionService.delete(excursionId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    /**
     * Метод для пошуку екскурсії за Id.
     * Викликається при натисканні на кнопку "Знайти бюджет".
     */
    @FXML
    void searchExcursion() {
            excursionTable.getItems().clear();
            String excursionIdText = findByIdField.getText();
            Integer excursionId = Integer.parseInt(excursionIdText);
            Excursion excursions = excursionService.getById(excursionId);
            excursionTable.getItems().add(excursions);
            if (excursions == null) {
                showAlert("Такого екскурсії не знайдено");
                refreshTable();
            }
    }

    /**
     * Метод для показу повідомлення про помилку.
     *
     * @param message Повідомлення про помилку.
     */
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

    /**
     * Метод для оновлення таблиці бюджетів.
     */
    @FXML
    private void refreshTable() {
        List<Excursion> excursions = excursionService.getAll();
        // Очистити таблицю перед додаванням нових даних
        excursionTable.getItems().clear();

        // Додати користувачів до таблиці
        excursionTable.getItems().addAll(excursions);
    }

    /**
     * Метод для обробки події кліку на таблицю.
     *
     * @param event Подія кліку мишею.
     */
    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Excursion selectedExcursion = excursionTable.getSelectionModel().getSelectedItem();

            if (selectedExcursion != null) {
                managerId.setText(String.valueOf(selectedExcursion.getManager().getManagerId()));
                excursionName.setText(selectedExcursion.getName());
                excursionDate.setValue(selectedExcursion.getDate());
                excursionDescription.setText(selectedExcursion.getDescription());
                excursionLocation.setText(String.valueOf(selectedExcursion.getLocation()));
            }
        }
    }

    /**
     * Метод для оновлення структури таблиці бюджетів.
     * Використовується для відображення відповідних стовпців у таблиці.
     */
    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "excursions", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<Excursion, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі Excursion
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                excursionTable.getColumns().add(column);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для перетворення назви стовпця в назву змінної.
     *
     * @param columnName Назва стовпця.
     * @return Назва змінної.
     */
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
     * Конструктор класу ExcursionController.
     */
    public ExcursionController() {
    }

    /**
     * Конструктор класу ExcursionController з параметром.
     *
     * @param mainController Об'єкт головного контролера.
     */
    public ExcursionController(MainController mainController) {
    }

    /**
     * Метод для встановлення головного контролера.
     *
     * @param mainController Об'єкт головного контролера.
     */
    public void setMainController(MainController mainController) {
    }
}