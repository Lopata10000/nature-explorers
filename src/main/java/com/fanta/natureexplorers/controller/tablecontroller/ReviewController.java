package com.fanta.natureexplorers.controller.tablecontroller;

import static com.fanta.natureexplorers.database.PoolConfig.dataSource;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.UserDao;
import com.fanta.natureexplorers.entity.Review;
import com.fanta.natureexplorers.entity.User;
import com.fanta.natureexplorers.service.ReviewService;

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

public class ReviewController implements Initializable {
    @FXML private TableView<Review> reviewTable;
    @FXML private TextField userId;
    @FXML private TextField rating;
    @FXML private TextField reviewText;
    @FXML private TextField findByIdField;

    private final ReviewService reviewService = new ReviewService();

    /**
     * Create review.
     */
    @FXML
    public void createReview() {
        try {
            Integer userIdLong = Integer.valueOf(userId.getText());
            UserDao userDAO = new UserDao();
            User user = userDAO.getById(userIdLong);
            if (user == null) {
                showAlert("Користувача з таким id не існує");
            }
            Integer userID = Integer.valueOf(userId.getText());
            Integer ratingLong = Integer.valueOf(rating.getText());
            String textreview = reviewText.getText();
            Review review = new Review(ratingLong, textreview, user);
            reviewService.save(review);
            refreshTable();
        } catch (Exception e) {
            showAlert("Неправильний формат");
        }
    }

    /**
     * Update review.
     */
    @FXML
    public void updateReview() {
        try {
            Review selectedReview =
                    reviewTable.getSelectionModel().getSelectedItem();
            Integer reviewId =
                    Integer.parseInt(String.valueOf(selectedReview.getReviewId()));
            Integer userID = Integer.valueOf(userId.getText());
            Integer ratingLong = Integer.valueOf(rating.getText());
            String textreview = reviewText.getText();
            UserDao userDao = new UserDao();
            Review review = new Review(ratingLong, textreview, userDao.getById(userID));
            reviewService.update(reviewId, review);
            refreshTable();
        } catch (Exception e) {
            showAlert("Неправильний формат");
        }
    }

    /**
     * Delete review.
     */
    @FXML
    public void deleteReview() {
        Review selectedReview = reviewTable.getSelectionModel().getSelectedItem();
        try {
            Integer reviewId =
                    Integer.parseInt(String.valueOf(selectedReview.getReviewId()));
            reviewService.delete(reviewId);
            refreshTable();
        } catch (NumberFormatException e) {
            showAlert("Неправильний формат числа для Id");
        }
    }

    /**
     * Search review.
     */
    @FXML
    void searchReview() {
        try {
            reviewTable.getItems().clear();
            String reviewIdText = findByIdField.getText();
            Integer reviewId = Integer.parseInt(reviewIdText);
            Review  reviews = reviewService.getById(reviewId);
            reviewTable.getItems().add(reviews);
            if (reviews == null) {
                showAlert("Такого відгуку не знайдено");
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
        List<Review> reviews = reviewService.getAll();
        // Очистити таблицю перед додаванням нових даних
        reviewTable.getItems().clear();

        // Додати користувачів до таблиці
        reviewTable.getItems().addAll(reviews);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Review selectedReview =
                    reviewTable.getSelectionModel().getSelectedItem();

            if (selectedReview != null) {
                userId.setText(String.valueOf(selectedReview.getUser().getUserId()));
                reviewText.setText(
                        String.valueOf(selectedReview.getReviewText()));
                rating.setText(String.valueOf(selectedReview.getRating()));
            }
        }
    }

    @FXML
    private void updateTableView() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "reviews", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");

                TableColumn<Review, String> column = new TableColumn<>(columnName);

                // Отримуємо відповідну назву змінної у класі Review
                String variableName = convertColumnNameToVariableName(columnName);

                // Встановлюємо PropertyValueFactory з використанням назви змінної
                column.setCellValueFactory(new PropertyValueFactory<>(variableName));
                reviewTable.getColumns().add(column);
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
     * Instantiates a new Planning cost controller.
     */
    public ReviewController() {}

    /**
     * Instantiates a new Planning cost controller.
     *
     * @param mainController the main controller
     */
    public ReviewController(MainController mainController) {}

    /**
     * Sets main controller.
     *
     * @param mainController the main controller
     */
    public void setMainController(MainController mainController) {}
}
