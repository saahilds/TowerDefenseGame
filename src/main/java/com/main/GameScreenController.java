package com.main;

import com.main.config.Config;
import com.main.game.GamePaneWrapper;
import com.main.game.entity.EntityWithHealth;
import com.main.game.path.PathBlock;
import com.main.game.path.TexturePathBlock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class GameScreenController extends MainApplication implements Initializable, ControlledScreen {

    ScreensController screensController;

    @FXML
    private Pane gamePane;

    private GamePaneWrapper gamePaneWrapper;

    public void initGamePaneSetting() {
        System.out.println("gamePane" + gamePane);
        this.gamePaneWrapper = new GamePaneWrapper(
                this.gamePane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
                Config.UNIT, Config.UNIT
        );

        Rectangle b = new Rectangle(32, 32, Color.RED);
        Rectangle c = new Rectangle(32, 32, Color.RED);
        System.out.println(gamePaneWrapper.widthCapacity + "|" + gamePaneWrapper.heightCapacity);
        int maxXidx = gamePaneWrapper.maxXidx;
        int maxYidx = gamePaneWrapper.maxYidx;

        this.generateSimplePath();

        int vCenterIdx = (int) Math.floor(gamePaneWrapper.maxYidx / 2);

        EntityWithHealth enemy = new EntityWithHealth(32, 32, 100, 50);
        Image enemyImage = new Image(
                getClass().getResourceAsStream("/com/main/skeleton_01.png")
        );
        ImagePattern enemyImgPattern = new ImagePattern(enemyImage);
        enemy.setEntityImgPattern(enemyImgPattern);

        EntityWithHealth player = new EntityWithHealth(32, 32, 100, 80);
        Image playerImage = new Image(
                getClass().getResourceAsStream("/com/main/steve_01.jpeg")
        );
        ImagePattern playerImgPattern = new ImagePattern(playerImage);
        player.setEntityImgPattern(playerImgPattern);

        this.gamePaneWrapper.addNodeWithXidxYidx(0, vCenterIdx, player);
        this.gamePaneWrapper.addNodeWithXidxYidx(maxXidx - 1, vCenterIdx, enemy);
        this.gamePaneWrapper.addNodeWithXidxYidx(0, maxYidx, c);
    }

    public void generateSimplePath() {
        int vCenterIdx = (int) Math.floor(gamePaneWrapper.maxYidx / 2);
        for (int xIdx = 0; xIdx < gamePaneWrapper.maxXidx + 1; xIdx++) {
            Image textureImage = new Image(
                    getClass().getResourceAsStream("/com/main/grass_1.png")
            );
            ImagePattern textureImagePattern = new ImagePattern(textureImage);
            PathBlock pathBlock = new TexturePathBlock(Config.UNIT, Config.UNIT, xIdx, textureImagePattern);
            this.gamePaneWrapper.addNodeWithXidxYidx(xIdx, vCenterIdx, pathBlock);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.initGamePaneSetting();
    }

    public void setScreenParent(ScreensController screenParent) {
        screensController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event) {
        screensController.setScreen(MainApplication.mainID);
    }

    @FXML
    private void goToInitialScreen(ActionEvent event) {
        screensController.setScreen(MainApplication.initialScreenID);
    }
}