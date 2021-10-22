package com.main.game.entity.tower;

import com.main.game.data.GameSettingDataMap;
import com.main.model.GameLevelType;

import java.util.ArrayList;
import java.util.HashMap;

public class TowerMenu {
    private TowerData selectedTowerData;

    private ArrayList<TowerData> towerMenu;

    public TowerMenu(GameLevelType gameLevelType) {
        towerMenu = new ArrayList<TowerData>();
        TowerData techTower = new TowerData(
                "tech_tower",
                "Tech Tower",
                "Iconic Building of Georgia Tech",
                GameSettingDataMap.getTowerInitialCost(gameLevelType),
                50,
                100.0,
                "/com/main/TechTower01.png"
        );
        TowerData westin = new TowerData(
                "westing_hotel",
                "Westin",
                "Luxury Hotel with rotating Sun Dial Restaurant",
                GameSettingDataMap.getTowerInitialCost(gameLevelType),
                100,
                200.0,
                "/com/main/Westin01.png"
        );
        TowerData pencilBuilding = new TowerData(
                "pencil_building",
                "Pencil Building",
                "Skinny illuminated building owned by Bank of America",
                GameSettingDataMap.getTowerInitialCost(gameLevelType),
                75,
                150.0,
                "/com/main/PencilBuilding01.png"
        );
        TowerData catapultTower = new TowerData(
                "catapult_tower",
                "Catapult Tower",
                "Catapult Tower Desc",
                GameSettingDataMap.getTowerInitialCost(gameLevelType),
                75,
                150.0,
                "/com/main/catapult.png"
        );
        towerMenu.add(techTower);
        towerMenu.add(westin);
        towerMenu.add(pencilBuilding);
        towerMenu.add(catapultTower);

        setSelectedTowerData(catapultTower);
    }

    public TowerData getSelectedTowerData() {
        return selectedTowerData;
    }

    public void setSelectedTowerData(TowerData selectedTowerData) {
        this.selectedTowerData = selectedTowerData;
    }

    public ArrayList<TowerData> getTowerMenu() {
        return towerMenu;
    }
}
