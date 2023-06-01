package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.TripDao;
import com.fanta.natureexplorers.entity.Trip;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class TripToursController implements Initializable {
    @FXML private FlowPane boxInfo;
    private MainController mainController;
    private TripDao tripDao = new TripDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Отримуємо дані з бази даних
        List<Trip> trips = tripDao.getAll();

        // Для кожної екскурсії створюємо новий вузол і додаємо його до TilePane
        for (Trip trip : trips) {
            try {
                Node tripNode = createTripNode(trip);
                boxInfo.getChildren().add(tripNode);
            } catch (IOException e) {
                // обробити помилку
                e.printStackTrace();
            }
        }
    }

    private Node createTripNode(Trip trip) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tours/Trip.fxml"));
        Node node = loader.load();

        TripNodeController controller = loader.getController();
        controller.setData(trip);

        return node;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public TripToursController(MainController mainController) {
        this.mainController = mainController;
    }

    public TripToursController() {}
}
