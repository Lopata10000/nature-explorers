package com.fanta.natureexplorers.controller;

import com.fanta.natureexplorers.controller.tablecontroller.ReviewController;
import com.fanta.natureexplorers.controller.tablecontroller.ManagerController;
import com.fanta.natureexplorers.controller.tablecontroller.UserController;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The type Main controller.
 */
public class MainController {
    @FXML private BorderPane mainApp;
    @FXML private JFXButton registrationButton;
    @FXML private JFXButton authorizationButton;

    /**
     * Instantiates a new Main controller.
     */
    public MainController() {}

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        authorizationButton.setOnAction(event -> authorizationWindow());
        registrationButton.setOnAction(event -> registrationWindow());
    }

    /**
     * Authorization window.
     */
    public void authorizationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/com/fanta/nature-explorers/Authorization.fxml"));
            AnchorPane authorizationPane = loader.load();

            AuthorizationController authorizationController = loader.getController();
            authorizationController.setMainController(this);

            mainApp.setCenter(authorizationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registration window.
     */
    public void registrationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/com/fanta/nature-explorers/Registration.fxml"));
            AnchorPane registrationPane = loader.load();

            RegistrationController registrationController = loader.getController();
            registrationController.setMainController(this);

            mainApp.setCenter(registrationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Data base window.
     */
    public void dataBaseWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/fanta/nature-explorers/database/LeftList.fxml"));
            Pane dataBasePane = loader.load();

            LeftController leftController = loader.getController();
            leftController.setMainController(this);

            mainApp.setLeft(dataBasePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * User window.
     */
    public void userWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/fanta/nature-explorers/database/UserTable.fxml"));
            AnchorPane userController = loader.load();

            UserController userController1 = loader.getController();
            userController1.setMainController(this);

            mainApp.setCenter(userController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Budget window.
     */
    public void managerWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/fanta/nature-explorers/database/ManagerTable.fxml"));
            AnchorPane mangerController = loader.load();

            ManagerController mangerController1 = loader.getController();
            mangerController1.setMainController(this);

            mainApp.setCenter(mangerController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    /**
     * Transaction window.
     */
    public void reviewWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/com/fanta/nature-explorers/database/ReviewTable.fxml"));
            AnchorPane reviewController = loader.load();

            ReviewController reviewController1 = loader.getController();
            reviewController1.setMainController(this);

            mainApp.setCenter(reviewController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * Exchange rate window.
//     */
//    public void exchangeRateWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/ExchangeRateTable.fxml"));
//            AnchorPane exchangeRateController = loader.load();
//
//            ExchangeRateController exchangeRateController1 = loader.getController();
//            exchangeRateController1.setMainController(this);
//
//            mainApp.setCenter(exchangeRateController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Cost window.
//     */
//    public void costWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/CostTable.fxml"));
//            AnchorPane costController = loader.load();
//
//            CostController controller = loader.getController();
//            controller.setMainController(this);
//
//            mainApp.setCenter(costController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Earning window.
//     */
//    public void earningWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/EarningTable.fxml"));
//            AnchorPane earningController = loader.load();
//
//            EarningController controller = loader.getController();
//            controller.setMainController(this);
//
//            mainApp.setCenter(earningController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Planning cost window.
//     */
//    public void planningCostWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/ReviewTable.fxml"));
//            AnchorPane planningCostController = loader.load();
//
//            ReviewController controller = loader.getController();
//            controller.setMainController(this);
//
//            mainApp.setCenter(planningCostController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Cost category window.
//     */
//    public void costCategoryWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/CostCategoryTable.fxml"));
//            AnchorPane costCategoryController = loader.load();
//
//            CostCategoryController controller = loader.getController();
//            controller.setMainController(this);
//
//            mainApp.setCenter(costCategoryController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Earning category window.
//     */
//    public void earningCategoryWindow() {
//        try {
//            FXMLLoader loader =
//                    new FXMLLoader(
//                            getClass()
//                                    .getResource(
//                                            "/com/fanta/nature-explorers/DataBase/EarningCategoryTable.fxml"));
//            AnchorPane earningCategoryController = loader.load();
//
//            EarningCategoryController controller = loader.getController();
//            controller.setMainController(this);
//
//            mainApp.setCenter(earningCategoryController);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Reset left pane.
     */
    public void resetLeftPane() {
        try {
            FXMLLoader leftLoader =
                    new FXMLLoader(getClass().getResource("/com/fanta/nature-explorers/Main.fxml"));
            Pane leftPane = leftLoader.load();

            mainApp.setLeft(leftPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main window.
     */
    public void mainWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/com/fanta/nature-explorers/Main.fxml"));
            BorderPane mainBorderPane = loader.load();

            // assuming mainApp is currently displayed
            mainApp.getScene().setRoot(mainBorderPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
