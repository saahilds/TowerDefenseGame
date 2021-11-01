package com.main;

import com.main.config.Config;
//import com.main.game.DataController;
import com.main.game.GameDataController;
import com.main.game.components.gameScreen.GameFlowControllerComponent;
import com.main.game.components.gameScreen.TowerMenuComponent;
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
import java.util.Observable;
import java.util.Observer;
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

    @FXML
    private VBox gameFlowControllerEl;

    private ScreensController screensController;
    private GamePaneWrapper gamePaneWrapper;
    private GameDataController gameDataController;
    private TowerMenuComponent towerMenu;
    private GameFlowControllerComponent gameFlowControllerComponent;
    private GameMoneyObserber gameMoneyObserber = new GameMoneyObserber();

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
        System.out.println("GSC initGamePaneSetting 1");
        gamePaneWrapper = new GamePaneWrapper(
                this.gamePane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT
        );
        System.out.println("GSC initGamePaneSetting 2");
        gameDataController = new GameDataController(
                gamePaneWrapper,
                getDataController(),
                gameLevel
        );
        System.out.println("GSC initGamePaneSetting 3");
        getDataController().getGameMoneyObservable().addObserver(gameMoneyObserber);
        System.out.println("GSC initGamePaneSetting 4");
        System.out.println("GSC initGamePaneSetting 4 GL" + getDataController().getGameLevel());
        towerMenu = new TowerMenuComponent(
                towerMenuEl,
                getDataController().getGameLevel()
        );
        System.out.println("GSC initGamePaneSetting 4.5");
        towerMenu.setGameDataController(gameDataController);
        System.out.println("GSC initGamePaneSetting 5");
        gameFlowControllerComponent = new GameFlowControllerComponent(gameFlowControllerEl);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("GSC initialize");
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

    public class GameMoneyObserber implements Observer {
        public void update(Observable o, Object arg) {
            System.out.println("GM OBSERVer setGameMoney: " + arg);
            if (arg instanceof Integer) {
                setGameMoney((Integer) arg);
            }
        }
    }
}