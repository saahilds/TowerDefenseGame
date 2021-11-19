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
        towerDataItemList = new ArrayList<>();
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
                70,
                150.0,
                "/com/main/catapult.png"
        );
        towerDataItemList.add(techTower);
        towerDataItemList.add(westin);
        towerDataItemList.add(pencilBuilding);
        towerDataItemList.add(catapultTower);
        setSelectedTowerData(catapultTower);
    }

    public TowerMenuComponent(VBox towerMenuEl, GameLevelType gameLevel) {
        this(gameLevel);
        this.towerMenuEl = towerMenuEl;
        initMenuItemList();
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
