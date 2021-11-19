package com.game.components.gameScene;

import com.game.model.TowerType;
//import com.main.model.GameLevelType;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.HashMap;

public class TowerData {
    private static TowerType type;
    private static int damage;
    private static int price;
    private static Shape shape;
    private static String name;
    private static String description;

    private static HashMap<TowerType, TowerData> towerDataMap = new HashMap<>() {{
            put(
                TowerType.TYPE_A,
                new TowerData(
                        TowerType.TYPE_A,
                        10,
                        50,
                        "typeA",
                        "typeA description"
                )
            );
            put(
                TowerType.TYPE_A,
                new TowerData(
                        TowerType.TYPE_A,
                        10,
                        50,
                        "typeA",
                        "typeA description"
                )
            );
        }};

    public TowerData(
            TowerType type,
            int damage,
            int price,
            String name,
            String description
    ) {
        this.type = type;
        this.damage = damage;
        this.price = price;
        this.name = name;
        this.description = description;
    }

}