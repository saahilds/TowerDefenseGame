package com.main.game.entity.tower;

import com.main.model.GameLevelType;

import java.util.ArrayList;
import java.util.HashMap;

public class TowerMenu {
    private ArrayList<TowerData> towerMenu;
    private static HashMap<GameLevelType, Integer> towerInitialCostMap = new HashMap<>() {
        {
            put(GameLevelType.EASY, 25);
            put(GameLevelType.NORMAL, 50);
            put(GameLevelType.HARD, 100);
        }
    };

    public TowerMenu(GameLevelType gameLevelType) {
        towerMenu = new ArrayList<TowerData>();
        TowerData techTower = new TowerData("Tech Tower", "Iconic Building of Georgia Tech",
                towerInitialCostMap.get(gameLevelType), 50, 100.0, "/com/main/TechTower01.png");
        TowerData westin = new TowerData("Westin", "Luxury Hotel with rotating Sun Dial Restaurant",
                towerInitialCostMap.get(gameLevelType), 100, 200.0, "/com/main/Westin01.png");
        TowerData pencilBuilding = new TowerData("Pencil Building",
                "Skinny illuminated building owned by Bank of America", towerInitialCostMap.get(gameLevelType),
                75,150.0, "/com/main/PencilBuilding01.png");
        towerMenu.add(techTower);
        towerMenu.add(westin);
        towerMenu.add(pencilBuilding);
    }

    public ArrayList<TowerData> getTowerMenu() {
        return towerMenu;
    }

    public static HashMap<GameLevelType, Integer> getTowerInitialCostMap() {
        return towerInitialCostMap;
    }
}
