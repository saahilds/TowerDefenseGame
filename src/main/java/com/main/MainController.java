package com.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.main.model.GameScreenType;
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
<<<<<<< HEAD
    private Menu IDT;
    ScreensController screensController;
=======
    private Menu idt;
    private ScreensController screensController;

    public ScreensController getScreensController() {
        return screensController;
    }
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label lblRegister = new Label("File");
        lblRegister.setOnMouseClicked((e) -> performRegistration());
<<<<<<< HEAD
        IDT.setGraphic(lblRegister);
        IDT.setText("");
=======
        idt.setGraphic(lblRegister);
        idt.setText("");
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b

    }


    @FXML
    private void performRegistration() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("initial-screen.fxml"));
            Scene scene = new Scene(root);
<<<<<<< HEAD
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
=======
            //scene.getStylesheets().add(getClass().getResource("application.css")
            //.toExternalForm());
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
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
        screensController.setScreen(GameScreenType.CONFIG_SCREEN);
    }

    @FXML
    private void goToGameScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.GAME_SCREEN);
    }

    public void performDialoge() {
<<<<<<< HEAD
//        ControllerDialog a = new ControllerDialog().start();
=======
        //ControllerDialog a = new ControllerDialog().start();
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
        //a.setId(IDRTA.getText());
    }
}