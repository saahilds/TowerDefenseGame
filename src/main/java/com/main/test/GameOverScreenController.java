package com.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Concentration 64
 */
public class GameOverScreenController extends MainApplication
        implements Initializable, ControlledScreen {

    @FXML
    private Text nameOut;

    @FXML
    private TextField nameIn;

    @FXML
    private Text errorText;

    @FXML
    private Text levelOut;

    private GameLevelType gameLevel;
    private String playerName;

    private ScreensController screensController;

    /**
     * Initializes the controller class.
     */
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


    @FXML
    public void saveData(MouseEvent mouseEvent) {
        String nameInText = nameIn.getText();
        if (getDataController().isPlayerNameValid(nameInText)) {
            errorText.setText("");
            nameOut.setText("Welcome, " + nameIn.getText());
            setPlayerName(nameInText);
        } else {
            errorText.setText("Invalid Name Input");
            nameOut.setText("");
        }
    }


    @FXML
    void playEasy(MouseEvent event) {
        levelOut.setText("Level: Easy");
        setGameLevel(GameLevelType.EASY);
    }

    @FXML
    void playNormal(MouseEvent event) {
        levelOut.setText("Level: Normal");
        setGameLevel(GameLevelType.NORMAL);
    }

    @FXML
    void playHard(MouseEvent event) {
        levelOut.setText("Level: Hard");
        setGameLevel(GameLevelType.HARD);
    }

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
    }

    // GETTER / SETTER
    public void setGameLevel(GameLevelType gameLevel) {
        if (gameLevel != null) {
            getDataController().setGameLevel(gameLevel);
            this.gameLevel = gameLevel;
        }
    }

    public GameLevelType getGameLevel() {
        return gameLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        if (getDataController().isPlayerNameValid(playerName)) {
            getDataController().setPlayerName(playerName);
            this.playerName = playerName;
        }
    }

}