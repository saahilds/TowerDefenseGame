package com.main.game;

import com.main.config.Config;
import com.main.game.common.IndexPosition;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.EnemyEntity;
import com.main.game.entity.tower.TowerData;
import com.main.game.entity.tower.TowerEntity;
import com.main.game.entity.EntityWithHealth;
import com.main.game.gamePane.GamePaneWrapper;
import com.main.game.path.PathBlock;
import com.main.game.path.TexturePathBlock;
import com.main.model.GameLevelType;
import com.main.model.TowerEntityStatusType;
import io.reactivex.observables.ConnectableObservable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class GameDataController {


    public DataController getDataController() {
        return dataController;
    }

    public void setDataController(DataController dataController) {
        this.dataController = dataController;
    }


    public TowerData getSelectedTower() {
        return selectedTower;
    }

    public void setSelectedTower(TowerData selectedTower) {
        this.selectedTower = selectedTower;
    }


    private GameLevelType gameLevel = GameLevelType.EASY;

    private TowerData selectedTower;
    private TowerEntity cursorTowerEntity;
    private IndexPosition prevPos;
    private boolean skipDeletingCuror = false;

    private GamePaneWrapper gamePaneWrapper;

    private DataController dataController;
    private GameFlowController gameFlowController;

    private EnemyEntity enemy;
    private ArrayList<IndexPosition> pathPositionArray;

    public GameDataController(
            GamePaneWrapper gamePaneWrapper,
            DataController dataController,
            GameFlowController gameFlowController,
            GameLevelType gameLevel
    ) {
        this.gamePaneWrapper = gamePaneWrapper;
        this.dataController = dataController;
        this.gameFlowController = gameFlowController;
        this.gameLevel = gameLevel;
        this.pathPositionArray = this.getPathPositionArray();
        this.generatePathBlock(this.pathPositionArray);
        this.initMouseEventHandlerSetting();
        this.initClockSubscription();
        // game scenario
        this.initGameScenario();
    }

    public void initGameScenario() {
        System.out.println(gamePaneWrapper.getWidthCapacity()
                + "|" + gamePaneWrapper.getHeightCapacity());
        int maxXidx = gamePaneWrapper.getMaxXidx();
        int maxYidx = gamePaneWrapper.getMaxYidx();

        int vCenterIdx = (int) Math.floor(gamePaneWrapper.getMaxYidx() / 2);
        Integer startingMonumentHealth;
        if (this.gameLevel == null) {
            startingMonumentHealth = 100;
        } else {
            startingMonumentHealth = GameSettingDataMap.getStartingMonumentHealth(this.gameLevel);
        }

        enemy = new EnemyEntity(
                startingMonumentHealth,
                startingMonumentHealth);
        enemy.setFillWithImageSrc("/com/main/skeleton_01.png");
        enemy.setId("enemyEntity");

        EntityWithHealth player = new EntityWithHealth(
                startingMonumentHealth,
                startingMonumentHealth
        );
        player.setFillWithImageSrc("/com/main/steve_01.jpeg");
        player.setId("playerEntity");
        System.out.println("Player");
        System.out.println(player);

        TowerEntity tower = new TowerEntity();
        tower.setFillWithImageSrc("/com/main/catapult.png");
        player.setId("catapultEntity");

        System.out.println("GDC initGameScenario 30");

        this.gamePaneWrapper.addNodeWithXidxYidx(0, vCenterIdx, player);
//        this.gamePaneWrapper.addNodeWithXidxYidx(maxXidx, vCenterIdx, enemy);
        this.gamePaneWrapper.addNodeWithXidxYidx(1, 1, tower);
        this.gamePaneWrapper.addEntityWithDesginatedPath(enemy, pathPositionArray);
    }

    private void initClockSubscription() {
        ConnectableObservable<Long> connectableObservable$ = gameFlowController.getIntervalObservable$();
        connectableObservable$.subscribe(this::onClockInterval);
    }

    public void onClockInterval(Long tick) {
        if (tick > (long) 1) {
            enemy.movementTest();
        }
    }

    public ArrayList<IndexPosition> getPathPositionArray() {
        ArrayList<IndexPosition> pathPositionArray = new ArrayList<>();
        int vCenterIdx = (int) Math.floor(gamePaneWrapper.getMaxYidx() / 2);
        for (int xIdx = gamePaneWrapper.getMaxXidx(); xIdx > 0; xIdx--) {
            pathPositionArray.add(new IndexPosition(xIdx, vCenterIdx));
        }
        return pathPositionArray;
    }

    public void generatePathBlock(ArrayList<IndexPosition> pathPositionArray) {
        int cnt = 0;
        for (IndexPosition pos: pathPositionArray) {
            Image textureImage = new Image(
                    getClass().getResourceAsStream("/com/main/grass_1.png")
            );
            ImagePattern textureImagePattern = new ImagePattern(textureImage);
            PathBlock pathBlock =
                    new TexturePathBlock(Config.UNIT, Config.UNIT, cnt++, textureImagePattern);
            pathBlock.setTranslateX(Config.UNIT * pos.getX());
            pathBlock.setTranslateY(Config.UNIT * pos.getY());
            this.gamePaneWrapper.addNode(pathBlock);
        }
    }

    public boolean isCursorMovedIdx(IndexPosition position) {
        boolean isCursorMovedIdx;
        if (prevPos == null) {
            isCursorMovedIdx = true;
        } else {
            isCursorMovedIdx = prevPos.getX() != position.getX()
                    || prevPos.getY() != position.getY();
        }
        prevPos = position;
        return isCursorMovedIdx;
    }

    public void initMouseEventHandlerSetting() {
        gamePaneWrapper.setOnMouseClickedHandler(mouseEvent -> {
            handleOnMouseClicked(mouseEvent);
        });
        gamePaneWrapper.setOnMouseMovedHandler(mouseEvent -> {
            handleOnMouseMovedHandler(mouseEvent);
        });
    }

    private void handleOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent != null) {
            System.out.println("========== MOUSE CLICKED ==========");
            System.out.println(mouseEvent);
            System.out.println("========== MOUSE CLICKED ==========");
            registerTowerEntity(mouseEvent);
        }
    }

    private void registerTowerEntity(MouseEvent mouseEvent) {
        if (
                gamePaneWrapper != null
                        && gamePaneWrapper.getPane() != null
                        && selectedTower != null
                        && prevPos.getX() < gamePaneWrapper.getWidthCapacity()
                        && prevPos.getY() < gamePaneWrapper.getHeightCapacity()
        ) {
            TowerEntity targetTowerEntity = new TowerEntity(selectedTower,
                    TowerEntityStatusType.REGISTERED);
            gamePaneWrapper.removeAtPos(prevPos);
            gamePaneWrapper.addNodeWithIndexPosition(prevPos, targetTowerEntity);
            this.cursorTowerEntity = null;
            this.skipDeletingCuror = true;
            this.selectedTower = null;
            onConfirmPurchase(targetTowerEntity.getTowerData());
        }
    }

    private void onConfirmPurchase(TowerData towerData) {
        int currMoney = getDataController().getGameMoney();
        int towerCose = towerData.getCost();
        getDataController().setGameMoney(currMoney - towerCose);
    }

    private void handleOnMouseMovedHandler(MouseEvent mouseEvent) {
        if (mouseEvent != null) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            IndexPosition prevPos = this.prevPos;
            IndexPosition currPos = gamePaneWrapper.getIdxWithPos(x, y);
            if (isCursorMovedIdx(currPos)) {
                if (skipDeletingCuror) {
                    this.skipDeletingCuror = false;
                } else {
                    Node node = gamePaneWrapper.getNodeWithIndexPosition(prevPos);
                    if (
                            node != null
                            && node instanceof TowerEntity
                            && ((TowerEntity) node).getTowerEntityStatus()
                                    == TowerEntityStatusType.STAGED
                    ) {
                        gamePaneWrapper.removeAtPos(prevPos);
                    }
                    registerTemporaryTowerEntity(prevPos, currPos);
                }
            }
        }
    }

    private void registerTemporaryTowerEntity(
            IndexPosition prevPos,
            IndexPosition currPos
    ) {
        if (
                gamePaneWrapper == null
                        || gamePaneWrapper.getPane() == null
                        || selectedTower == null
                        || currPos.getX() >= gamePaneWrapper.getWidthCapacity()
                        || currPos.getY() >= gamePaneWrapper.getHeightCapacity()
                        || gamePaneWrapper.getNodeWithIndexPosition(currPos) != null
        ) {
            return;
        } else {
            cursorTowerEntity = new TowerEntity(selectedTower, TowerEntityStatusType.STAGED);
            cursorTowerEntity.setId("cursorTowerEntity");
            gamePaneWrapper.addNodeWithIndexPosition(currPos, cursorTowerEntity);
        }
    }
}
