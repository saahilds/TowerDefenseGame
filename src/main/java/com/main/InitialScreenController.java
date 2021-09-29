package com.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class InitialScreenController implements Initializable, ControlledScreen {

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
    private void goToGameScreen(ActionEvent event){
        screensController.setScreen(MainApplication.gameScreenID);
    }
}