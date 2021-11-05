package com.main;

import com.main.config.Config;
//import com.main.game.DataController;
import com.main.game.GameDataController;
import com.main.game.GameFlowController;
import com.main.game.common.UpdateData;
import com.main.game.components.gameScreen.GameFlowControllerComponent;
import com.main.game.components.gameScreen.TowerMenuComponent;
import com.main.game.gamePane.GamePaneWrapper;
import com.main.model.GameLevelType;
import com.main.model.GameScreenType;
import com.main.model.UpdateDataTypeType;
//import io.reactivex.functions.Action;
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
    private GameFlowController gameFlowController = new GameFlowController();
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
        gamePaneWrapper = new GamePaneWrapper(
                this.gamePane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT,
                gameFlowController
        );
        gameDataController = new GameDataController(
                gamePaneWrapper,
                getDataController(),
                gameFlowController,
                gameLevel
        );
        getDataController().getGameMoneyObservable().addObserver(gameMoneyObserber);
        towerMenu = new TowerMenuComponent(
                towerMenuEl,
                getDataController().getGameLevel()
        );
        towerMenu.setGameDataController(gameDataController);
        gameFlowControllerComponent = new GameFlowControllerComponent(
                gameFlowControllerEl,
                gameFlowController
        );
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
            this.gameFlowController.getGameUpdateDataSubject().
                    subscribe(data -> onGameUpdateData(data));
        }
    }

    public void onGameUpdateData(UpdateData data) {
        if (data.getType() == UpdateDataTypeType.END_GAME) {
            goToGameOverScreen();
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

    private void goToGameOverScreen() {
        screensController.setScreen(GameScreenType.GAME_OVER_SCREEN);
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