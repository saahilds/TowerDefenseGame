package com.game.components.gameScene;

import com.game.model.TowerType;
import javafx.scene.paint.ImagePattern;

public class TowerData {
    public TowerType getType() {
        return type;
    }

    public void setType(TowerType type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern = imagePattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpgradePrice() {
        return price / 2;
    }

    private TowerType type;
    private int damage;
    private int price;
    private ImagePattern imagePattern;
    private String name;
    private String description;

    public TowerData(
            TowerType type,
            int damage,
            int price,
            ImagePattern imagePattern,
            String name,
            String description
    ) {
        this.type = type;
        this.damage = damage;
        this.price = price;
        this.imagePattern = imagePattern;
        this.name = name;
        this.description = description;
    }


}