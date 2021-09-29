package com.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class MainController implements Initializable, ControlledScreen {
    @FXML
    private Menu IDT;
    ScreensController screensController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label lblRegister = new Label("File");
        lblRegister.setOnMouseClicked((e) -> performRegistration());
        IDT.setGraphic(lblRegister);
        IDT.setText("");

    }


    @FXML
    private void performRegistration() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("initial-screen.fxml"));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException s) {
            System.out.println(s);
            s.printStackTrace();
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @FXML
    private void goToInitialScreen(ActionEvent event) {
        screensController.setScreen(MainApplication.initialScreenID);
    }

    @FXML
    private void goToGameScreen(ActionEvent event) {
        screensController.setScreen(MainApplication.gameScreenID);
    }

    public void performDialoge() {
//        ControllerDialog a = new ControllerDialog().start();
        //a.setId(IDRTA.getText());
    }
}