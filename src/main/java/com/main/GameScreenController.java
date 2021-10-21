package com.main;

import com.main.config.Config;
import com.main.game.GameDataController;
import com.main.game.GamePaneWrapper;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class GameScreenController extends
        MainApplication implements Initializable, ControlledScreen {

    @FXML
    private Pane gamePane;

    private ScreensController screensController;
    private GamePaneWrapper gamePaneWrapper;
    private GameDataController gameDataController;

    private GameLevelType gameLevel = GameLevelType.EASY;
    private String playerName = "";
    private Integer gameMoney = 0;

    @FXML
    private Text gameLevelText;
    @FXML
    private Text playerNameText;
    @FXML
    private Text gameMoneyText;


    public void initGamePaneSetting() {
        this.gamePaneWrapper = new GamePaneWrapper(
                this.gamePane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT
        );

        gameDataController = new GameDataController(
                gamePaneWrapper,
                gameLevel
        );
        gameDataController.generateSimplePath();
        gameDataController.initGameScenario();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (url != null && getDataController() != null) {
            setGameLevel(getDataController().getGameLevel());
            setPlayerName(getDataController().getPlayerName());
            setGameMoney(getDataController().getGameMoney());
            this.initGamePaneSetting();
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.WELCOME_SCREEN);
    }

    @FXML
    private void goToInitialScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.CONFIG_SCREEN);
    }

    public void setPlayerName(String playerName) {
        if (this.playerNameText != null) {
            this.playerNameText.setText(playerName);
        }
        this.playerName = playerName;
    }

    public void setGameMoney(Integer gameMoney) {
        if (this.gameMoneyText != null) {
            this.gameMoneyText.setText(gameMoney.toString());
        }
        this.gameMoney = gameMoney;
    }

    public void setGameLevel(GameLevelType gameLevel) {
        this.gameLevel = gameLevel;
    }
}