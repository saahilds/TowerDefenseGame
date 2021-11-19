package com.game.components.gameScene;

import com.game.model.GameLevelType;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TowerMenuComponent extends StackPane {
    private TowerData selectedTowerData;
    private ArrayList<TowerData> towerDataItemList;

    public TowerMenuComponent(GameLevelType gameLevel) {
        towerDataItemList = new ArrayList<>();

        initMenuItemList();
    }


    private void initMenuItemList() {
        //for (TowerData item: towerDataItemList) {
        /**Group group = new Group();


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
            this.getChildren().add(splitMenuButton);
        } */
    }

    public TowerData getSelectedTowerData() {
        return selectedTowerData;
    }

    public void setSelectedTowerData(TowerData selectedTowerData) {
    }

    public ArrayList<TowerData> getTowerDataItemList() {
        return towerDataItemList;
    }
}
