package com.main;

import java.net.URL;
import java.util.ResourceBundle;

//import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
//import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Concentration 64
 */
public class GameOverScreenController extends MainApplication
        implements Initializable, ControlledScreen {

    private ScreensController screensController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.WELCOME_SCREEN);
    }

    @FXML
    private void exitGame(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
    }

}