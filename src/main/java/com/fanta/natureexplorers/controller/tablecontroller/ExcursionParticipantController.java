package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.ExcursionDao;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.ExcursionParticipant;
import com.fanta.natureexplorers.service.ExcursionParticipantService;

import org.hibernate.exception.ConstraintViolationException;

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
 * The type Earning category controller.
 */
public class ExcursionParticipantController implements Initializable {
    @FXML private TableView<ExcursionParticipant> excursionParticipantTable;
    @FXML private TextField userId;
    @FXML private TextField excursionId;
    @FXML private TextField findByIdField;
    private final ExcursionParticipantService excursionParticipantService = new ExcursionParticipantService();

    /**
     * Create excursion participant.
     */
    @FXML
    public void createExcursionParticipant() {
        try {
            Integer userID = Integer.valueOf(userId.getText());
            Integer excursionID = Integer.valueOf(excursionId.getText());
            UserDao userDao = new UserDao();
            ExcursionDao excursionDao = new ExcursionDao();
            ExcursionParticipant excursionParticipant = new ExcursionParticipant (userDao.getById(userID), excursionDao.getById(excursionID));
            excursionParticipantService.save(excursionParticipant);
            refreshTable();
        } catch (ConstraintViolationException e) {
            showAlert("З таким іменем уже існує");
        } catch (Exception e) {
            showAlert("Не правильний формат");
        }
    }

    /**
     * Update excursion participant.
     */
    @FXML
    public void updateExcursionParticipant() {
            try {
                ExcursionParticipant selectedExcursionParticipant =
                        excursionParticipantTable.getSelectionModel().getSelectedItem();
                Integer excursionParticipantId = Integer.parseInt(String.valueOf(selectedExcursionParticipant.getId()));

                Integer userID = Integer.valueOf(userId.getText());
                Integer excursionID = Integer.valueOf(excursionId.getText());
                UserDao userDao = new UserDao();
                ExcursionDao excursionDao = new ExcursionDao();
                ExcursionParticipant excursionParticipant = new ExcursionParticipant (userDao.getById(userID), excursionDao.getById(excursionID));
                excursionParticipantService.update(excursionParticipantId,excursionParticipant);
                refreshTable();
            } catch (ConstraintViolationException e) {
                showAlert("З таким іменем уже існує");
            } catch (Exception e) {
                showAlert("Не правильний формат");
            }
        }

    /**
     * Delete excursion participant.
     */
    @FXML
    public void deleteExcursionParticipant() {
        ExcursionParticipant selectedExcursionParticipant =
                excursionParticipantTable.getSelectionModel().getSelectedItem();
        try {
            Integer excursionParticipantId =
                    Integer.parseInt(String.valueOf(selectedExcursionParticipant.getId()));
            excursionParticipantService.delete(excursionParticipantId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    /**
     * Search excursion participant.
     */
    @FXML
    void searchExcursionParticipant() {
        try {
            excursionParticipantTable.getItems().clear();
            String excursionParticipantIdText = findByIdField.getText();
            Integer excursionParticipantId = Integer.parseInt(excursionParticipantIdText);
            ExcursionParticipant excursionParticipants = excursionParticipantService.getById(excursionParticipantId);
            excursionParticipantTable.getItems().add(excursionParticipants);
            if (excursionParticipants == null) {
                showAlert("Нічого не знайдено");
                refreshTable();
            }
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
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
        List<ExcursionParticipant> excursionParticipants = excursionParticipantService.getAll();
        // Очистити таблицю перед додаванням нових даних
        excursionParticipantTable.getItems().clear();

        // Додати користувачів до таблиці
        excursionParticipantTable.getItems().addAll(excursionParticipants);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            ExcursionParticipant selectedExcursionParticipant =
                    excursionParticipantTable.getSelectionModel().getSelectedItem();

            if (selectedExcursionParticipant != null) {
                userId.setText(
                        String.valueOf(selectedExcursionParticipant.getUser().getUserId()));
                excursionId.setText(
                        String.valueOf(selectedExcursionParticipant.getUser().getUserId()));
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "excursion_participants", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<ExcursionParticipant, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі ExcursionParticipant
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                excursionParticipantTable.getColumns().add(column);
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
     * Instantiates a new Earning category controller.
     */
    public ExcursionParticipantController() {}

    /**
     * Instantiates a new Earning category controller.
     *
     * @param mainController the main controller
     */
    public ExcursionParticipantController(MainController mainController) {}

    /**
     * Sets main controller.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {}
}
