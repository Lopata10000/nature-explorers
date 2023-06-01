package com.fanta.natureexplorers.controller.tours;

import com.fanta.natureexplorers.controller.authentication.AuthorizationController;
import com.fanta.natureexplorers.controller.main.MainController;
import com.fanta.natureexplorers.dao.ExcursionDao;
import com.fanta.natureexplorers.entity.Excursion;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class ExcursionToursController implements Initializable {
    @FXML
    private TilePane tilePane;
    private MainController mainController;
    private ExcursionDao excursionDao = new ExcursionDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Отримуємо дані з бази даних
        List<Excursion> excursions = excursionDao.getAll();

        // Для кожної екскурсії створюємо новий вузол і додаємо його до TilePane
        for (Excursion excursion : excursions) {
            try {
                Node excursionNode = createExcursionNode(excursion);
                tilePane.getChildren().add(excursionNode);
            } catch (IOException e) {
                // обробити помилку
                e.printStackTrace();
            }
        }
    }
    private Node createExcursionNode(Excursion excursion) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tours/Excursion.fxml"));
        Node node = loader.load();

        ExcursionNodeController controller = loader.getController();
        controller.setData(excursion);

        return node;
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public ExcursionToursController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Instantiates a new Authorization controller.
     */
    public ExcursionToursController() {}
}
