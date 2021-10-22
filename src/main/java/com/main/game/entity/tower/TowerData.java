package com.main.game.entity.tower;

public class TowerData {
    private String towerDataId;
    private String name;
    private String description;
    private int cost;
    private int dps;
    private double health;
    private String imageSource;

    public TowerData(
            String towerDataId,
            String name,
            String description,
            int cost,
            int dps,
            double health,
            String imageSource
    ) {
        this.towerDataId = towerDataId;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.dps = dps;
        this.health = health;
        this.imageSource = imageSource;
    }

    public String getTowerDataId() {
        return towerDataId;
    }

    public void setTowerDataId(String towerDataId) {
        this.towerDataId = towerDataId;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
