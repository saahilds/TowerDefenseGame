package com.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class GameScreenController implements Initializable, ControlledScreen {

    ScreensController screensController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        screensController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event){
        screensController.setScreen(MainApplication.mainID);
    }

    @FXML
    private void goToInitialScreen(ActionEvent event){
        screensController.setScreen(MainApplication.initialScreenID);
    }
}