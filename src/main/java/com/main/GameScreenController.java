package com.main;

import com.almasb.fxgl.app.GameApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class GameScreenController extends MainApplication implements Initializable, ControlledScreen {

    ScreensController screensController;

    SimpleGameApp simpleGameApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Stage primaryStagen = MainApplication.getMainStage();
//        System.out.println(primaryStagen);
        simpleGameApp = new SimpleGameApp();
        GameApplication.customLaunch(simpleGameApp, primaryStagen);
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