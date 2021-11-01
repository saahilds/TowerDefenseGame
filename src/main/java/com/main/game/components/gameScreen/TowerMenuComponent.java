package com.main.game.components.gameScreen;

//import com.main.game.DataController;
import com.main.game.GameDataController;
import com.main.game.data.GameSettingDataMap;
import com.main.game.entity.tower.TowerData;
import com.main.model.GameLevelType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
//import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TowerMenuComponent {
    private VBox towerMenuEl;

    public VBox getTowerMenuEl() {
        return towerMenuEl;
    }

    public void setTowerMenuEl(VBox towerMenuEl) {
        this.towerMenuEl = towerMenuEl;
    }

    public GameDataController getGameDataController() {
        return gameDataController;
    }

    public void setGameDataController(GameDataController gameDataController) {
        this.gameDataController = gameDataController;
    }

    private GameDataController gameDataController;

    private TowerData selectedTowerData;

    private ArrayList<TowerData> towerDataItemList;

    public TowerMenuComponent(GameLevelType gameLevel) {
        System.out.println("TowerMenu constructor(single) 0");
        System.out.println("TowerMenu constructor(single) GL" + gameLevel);
        towerDataItemList = new ArrayList<>();
        System.out.println("TowerMenu constructor(single) 1");
        TowerData techTower = new TowerData(
                "tech_tower",
                "Tech Tower",
                "Iconic Building of Georgia Tech",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                50,
                100.0,
                "/com/main/TechTower01.png"
        );
        System.out.println("TowerMenu constructor(single) 1.1");
        TowerData westin = new TowerData(
                "westing_hotel",
                "Westin",
                "Luxury Hotel with rotating Sun Dial Restaurant",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                100,
                200.0,
                "/com/main/Westin01.png"
        );
        System.out.println("TowerMenu constructor(single) 1.2");
        TowerData pencilBuilding = new TowerData(
                "pencil_building",
                "Pencil Building",
                "Skinny illuminated building owned by Bank of America",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                75,
                150.0,
                "/com/main/PencilBuilding01.png"
        );
        System.out.println("TowerMenu constructor(single) 1.3");
        TowerData catapultTower = new TowerData(
                "catapult_tower",
                "Catapult Tower",
                "Catapult Tower Desc",
                GameSettingDataMap.getTowerInitialCost(gameLevel),
                75,
                150.0,
                "/com/main/catapult.png"
        );
        System.out.println("TowerMenu constructor(single) 10");
        towerDataItemList.add(techTower);
        towerDataItemList.add(westin);
        towerDataItemList.add(pencilBuilding);
        towerDataItemList.add(catapultTower);
        System.out.println("TowerMenu constructor(single) 30");
        setSelectedTowerData(catapultTower);
        System.out.println("TowerMenu constructor(single) 99");
    }

    public TowerMenuComponent(VBox towerMenuEl, GameLevelType gameLevel) {
        this(gameLevel);
        System.out.println("TowerMenu constructor 1");
        this.towerMenuEl = towerMenuEl;
        System.out.println("TowerMenu constructor 2");
        System.out.println(123);
        System.out.println("TowerMenu constructor 3");
        initMenuItemList();
        System.out.println("TowerMenu constructor 99");
    }

    private void initMenuItemList() {
        if (towerMenuEl == null) {
            return;
        }
        for (TowerData item: towerDataItemList) {
            SplitMenuButton splitMenuButton = new SplitMenuButton();
            splitMenuButton.setText(item.getName());
            MenuItem name = new MenuItem(item.getName());
            MenuItem description = new MenuItem(item.getDescription());
            MenuItem cost = new MenuItem("COST " + item.getCost());
            MenuItem health = new MenuItem("HEALTH " + item.getHealth());
            MenuItem purchase = new MenuItem("Buy Tower");
            purchase.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    System.out.println(item.getName() + "selected");
                    setSelectedTowerData(item);
                }
            });
            splitMenuButton.getItems().addAll(name, cost, health, description, purchase);
            towerMenuEl.getChildren().add(splitMenuButton);
        }
    }

    public TowerData getSelectedTowerData() {
        return selectedTowerData;
    }

    public void setSelectedTowerData(TowerData selectedTowerData) {
        this.selectedTowerData = selectedTowerData;
        if (gameDataController != null) {
            stageTowerPurchase(selectedTowerData);
        }
    }

    private void stageTowerPurchase(TowerData towerData) {
        System.out.println(gameDataController.getDataController().getGameMoney());
        if (gameDataController.getDataController().getGameMoney() - towerData.getCost() < 0) {
            System.out.println("NOT ENOUGH MONEY");
        } else {
            gameDataController.setSelectedTower(towerData);
        }
    }

    public ArrayList<TowerData> getTowerDataItemList() {
        return towerDataItemList;
    }
}
