package com.main.game.entity.tower;

import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TowerMenu {
    private VBox towerMenuEl;
    private TowerData selectedTowerData;
    private GameLevelType gameLevel = GameLevelType.EASY;

    private DataController dataController;

    private ArrayList<TowerData> towerDataList;

    public TowerMenu(GameLevelType gameLevel) {
        towerDataList = new ArrayList<>();
        TowerData techTower = new TowerData(
                "tech_tower",
                "Tech Tower",
                "Iconic Building of Georgia Tech",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                50,
                100.0,
                "/com/main/TechTower01.png"
        );
        TowerData westin = new TowerData(
                "westing_hotel",
                "Westin",
                "Luxury Hotel with rotating Sun Dial Restaurant",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                100,
                200.0,
                "/com/main/Westin01.png"
        );
        TowerData pencilBuilding = new TowerData(
                "pencil_building",
                "Pencil Building",
                "Skinny illuminated building owned by Bank of America",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                75,
                150.0,
                "/com/main/PencilBuilding01.png"
        );
        TowerData catapultTower = new TowerData(
                "catapult_tower",
                "Catapult Tower",
                "Catapult Tower Desc",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                75,
                150.0,
                "/com/main/catapult.png"
        );
        towerDataList.add(techTower);
        towerDataList.add(westin);
        towerDataList.add(pencilBuilding);
        towerDataList.add(catapultTower);
//        setSelectedTowerData(catapultTower);
        generateTowerMenuItemList(towerDataList);
    }

    public TowerMenu(VBox towerMenuEl, DataController dataController) {
        this(dataController.getGameLevel());
        this.towerMenuEl = towerMenuEl;
        this.dataController = dataController;
        this.gameLevel = dataController.getGameLevel();
    }

    public TowerData getSelectedTowerData() {
        return selectedTowerData;
    }

    public void setSelectedTowerData(TowerData selectedTowerData) {
        this.selectedTowerData = selectedTowerData;
    }

    public ArrayList<TowerData> getTowerDataList() {
        return towerDataList;
    }

    private void generateTowerMenuItemList(ArrayList<TowerData> towerDataList) {
//        towerMenuEl.getChildren().add()
        for (TowerData el: towerDataList) {
            MenuItem name = new MenuItem(el.getName());
            SplitMenuButton itemWrapper = new SplitMenuButton(name);
//            itemWrapper.getChildrenUnmodifiable()
            towerMenuEl.getChildren().add(itemWrapper);
        }
    }
}
