package com.game.components.gameScene;

import com.game.model.TowerType;
import com.main.model.GameLevelType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.HashMap;

public class TowerData {
    public static TowerType type;
    public static int damage;
    public static int price;
    public static Shape shape;
    public static String name;
    public static String description;

    public static HashMap<TowerType, TowerData> towerDataMap = new HashMap<>() {{
        put(
                TowerType.TYPE_A,
                new TowerData(
                        TowerType.TYPE_A,
                        10,
                        50,
                        new Rectangle(20, 20, Color.ORANGERED),
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
                        new Rectangle(20, 20, Color.BLUE),
                        "typeA",
                        "typeA description"
                )
        );
    }};

    public TowerData(
            TowerType type,
            int damage,
            int price,
            Shape shape,
            String name,
            String description
    ) {
        this.type = type;
        this.damage = damage;
        this.price = price;
        this.shape = shape;
        this.name = name;
        this.description = description;
    }
}