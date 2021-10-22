package com.main;

import com.main.config.Config;
import com.main.game.GameDataController;
import com.main.game.entity.tower.TowerMenu;
import com.main.game.gamePane.GamePaneWrapper;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox towerMenuEl;

    private ScreensController screensController;
    private GamePaneWrapper gamePaneWrapper;
    private GameDataController gameDataController;
    private TowerMenu towerMenu;

    private GameLevelType gameLevel = GameLevelType.EASY;
    private String playerName = "";
    private Integer gameMoney = 0;

    @FXML
    private Text playerNameText;
    @FXML
    private Text gameMoneyText;

    @FXML
    private void goToMainScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.WELCOME_SCREEN);
    }

    @FXML
    private void goToInitialScreen(ActionEvent event) {
        screensController.setScreen(GameScreenType.CONFIG_SCREEN);
    }


    public void initGamePaneSetting() {
        gamePaneWrapper = new GamePaneWrapper(
                this.gamePane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT
        );

        gameDataController = new GameDataController(
                gamePaneWrapper,
                gameLevel
        );
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
            this.initTowerMenu();
            //
        }
    }

    public void initTowerMenu() {
        towerMenu = new TowerMenu(
                towerMenuEl,
                getDataController()
        );
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
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