package com.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.model.GameScreenType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class MainController extends MainApplication implements Initializable, ControlledScreen {
    @FXML
    private Menu idt;
    private ScreensController screensController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @FXML
    private void goToInitialScreen(ActionEvent event) {
        if (screensController != null) {
            screensController.setScreen(GameScreenType.CONFIG_SCREEN);
        }
    }

    @FXML
    private void goToGameScreen(ActionEvent event) {
        if (screensController != null) {
            screensController.setScreen(GameScreenType.GAME_SCREEN);
        }
    }

    public void performDialoge() {
    }
}