package com.fanta.natureexplorers.controller.main;

import com.fanta.natureexplorers.controller.authentication.AuthorizationController;
import com.fanta.natureexplorers.controller.authentication.RegistrationController;
import com.fanta.natureexplorers.controller.tablecontroller.ExcursionController;
import com.fanta.natureexplorers.controller.tablecontroller.ExcursionParticipantController;
import com.fanta.natureexplorers.controller.tablecontroller.ManagerController;
import com.fanta.natureexplorers.controller.tablecontroller.TripController;
import com.fanta.natureexplorers.controller.tablecontroller.TripParticipantController;
import com.fanta.natureexplorers.controller.tablecontroller.UserController;
import com.fanta.natureexplorers.controller.tours.ExcursionToursController;
import com.fanta.natureexplorers.controller.tours.TripToursController;
import com.fanta.natureexplorers.controller.tours.UserLeftListController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainController extends Application {
    @FXML private BorderPane mainApp;
    @FXML private JFXButton registrationButton;
    @FXML private JFXButton authorizationButton;
    @FXML private MediaView mediaView;

    public MainController() {}

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    /** Initialize. */
    @FXML
    public void initialize() {
        URL resourceUrl = getClass().getResource("/com/fanta/nature-explorers/video/MainVideo.mp4");
        Media media = new Media(resourceUrl.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // To loop the video
        mediaPlayer.play();
    }

    public void mainWindow() {
        try {
            StackPane mainStackPane = new StackPane();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main/Main.fxml"));
            Pane mainPane = loader.load();
            URL resourceUrl = getClass().getResource("/com/fanta/nature-explorers/video/MainVideo.mp4");
            Media media = new Media(resourceUrl.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // To loop the video
            mediaPlayer.play();

            mainStackPane.getChildren().add(mediaView); // Adding mediaView to StackPane
            mainStackPane.getChildren().add(mainPane); // Adding your layout on top of the video

            mainApp.getScene().setRoot(mainStackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Authorization window. */
    public void authorizationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/fxml/authentication/Authorization.fxml"));
            AnchorPane authorizationPane = loader.load();

            AuthorizationController authorizationController = loader.getController();
            authorizationController.setMainController(this);

            mainApp.setCenter(authorizationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Registration window. */
    public void registrationWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/fxml/authentication/Registration.fxml"));
            AnchorPane registrationPane = loader.load();

            RegistrationController registrationController = loader.getController();
            registrationController.setMainController(this);

            mainApp.setCenter(registrationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tripToursWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tours/TripMain.fxml"));
            AnchorPane tripToursController = loader.load();

            TripToursController tripToursController1 = loader.getController();
            tripToursController1.setMainController(this);

            mainApp.setCenter(tripToursController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataBaseWindow() {
        try {
            FXMLLoader left =
                    new FXMLLoader(
                            getClass().getResource("/fxml/possibilities/AdminLeftList.fxml"));
            FXMLLoader top = new FXMLLoader(getClass().getResource("/fxml/Main/TopLable.fxml"));
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/UserTable.fxml"));
            AnchorPane userController = loader.load();

            UserController userController1 = loader.getController();
            userController1.setMainController(this);

            StackPane TopLable = top.load();

            Pane dataBasePane = left.load();
            LeftController leftController = left.getController();
            leftController.setMainController(this);

            mainApp.setCenter(userController);
            mainApp.setTop(TopLable);
            mainApp.setLeft(dataBasePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void managerActionWindow() {
        try {
            FXMLLoader left =
                    new FXMLLoader(
                            getClass().getResource("/fxml/possibilities/ManagerLeftList.fxml"));
            FXMLLoader top = new FXMLLoader(getClass().getResource("/fxml/Main/TopLable.fxml"));
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/TripTable.fxml"));
            AnchorPane tripController = loader.load();

            TripController tripController1 = loader.getController();
            tripController1.setMainController(this);

            StackPane TopLable = top.load();

            Pane leftController1 = left.load();
            LeftController leftController = left.getController();
            leftController.setMainController(this);

            mainApp.setCenter(tripController);
            mainApp.setTop(TopLable);
            mainApp.setLeft(leftController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userActionWindow() {
        try {
            FXMLLoader left =
                    new FXMLLoader(getClass().getResource("/fxml/possibilities/UserLeftList.fxml"));
            FXMLLoader top = new FXMLLoader(getClass().getResource("/fxml/Main/TopLable.fxml"));
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/tours/ExcursionMain.fxml"));
            AnchorPane excursionToursController = loader.load();

            ExcursionToursController excursionToursController1 = loader.getController();
            excursionToursController1.setMainController(this);

            StackPane topLable = top.load();

            Pane tourPane = left.load();
            UserLeftListController leftController = left.getController();
            leftController.setMainController(this);

            mainApp.setCenter(excursionToursController);
            mainApp.setTop(topLable);
            mainApp.setLeft(tourPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excursionTourWindow() {
        try {
            FXMLLoader center =
                    new FXMLLoader(getClass().getResource("/fxml/tours/ExcursionMain.fxml"));
            FXMLLoader top = new FXMLLoader(getClass().getResource("/fxml/Main/TopLable.fxml"));

            StackPane TopLable = top.load();

            AnchorPane excursionNodeController = center.load();

            ExcursionToursController excursionNodeController1 = center.getController();
            excursionNodeController1.setMainController(this);

            mainApp.setCenter(excursionNodeController);
            mainApp.setTop(TopLable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/UserTable.fxml"));
            AnchorPane userController = loader.load();

            UserController userController1 = loader.getController();
            userController1.setMainController(this);

            mainApp.setCenter(userController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void managerWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/ManagerTable.fxml"));
            AnchorPane mangerController = loader.load();

            ManagerController mangerController1 = loader.getController();
            mangerController1.setMainController(this);

            mainApp.setCenter(mangerController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //

    public void tripWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/TripTable.fxml"));
            AnchorPane tripController = loader.load();

            TripController tripController1 = loader.getController();
            tripController1.setMainController(this);

            mainApp.setCenter(tripController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Cost window. */
    public void tripParticipantsWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/fxml/database/TripParticipantTable.fxml"));
            AnchorPane tripParticipantController1 = loader.load();

            TripParticipantController tripParticipantController = loader.getController();
            tripParticipantController.setMainController(this);

            mainApp.setCenter(tripParticipantController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Earning window. */
    public void excursionWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/fxml/database/ExcursionTable.fxml"));
            AnchorPane excursionController1 = loader.load();

            ExcursionController excursionController = loader.getController();
            excursionController.setMainController(this);

            mainApp.setCenter(excursionController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Planning cost window. */
    public void excursionParticipantsWindow() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/fxml/database/ExcursionParticipantTable.fxml"));
            AnchorPane excursionParticipantController1 = loader.load();

            ExcursionParticipantController excursionParticipantController = loader.getController();
            excursionParticipantController.setMainController(this);

            mainApp.setCenter(excursionParticipantController1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Reset left pane. */
    public void resetLeftPane() {
        try {
            FXMLLoader leftLoader = new FXMLLoader(getClass().getResource("/fxml/Main/Main.fxml"));
            Pane leftPane = leftLoader.load();

            mainApp.setLeft(leftPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
