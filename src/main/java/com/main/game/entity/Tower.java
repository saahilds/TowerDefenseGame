package com.main.game.entity;

public class Tower {
    private String name;
    private String description;
    private int cost;
    private int[] location;
    private int dps;
    private double health;
//tower ideas:
    //Tech Tower: Tech Tower, the symbol of Georgia Tech, cost:
    // Pencil Building
    // Westin

    public Tower(String name, String description, int cost, int[] location, int dps, double health) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.location = location;
        this.dps = dps;
        this.health = health;
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

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
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

    //public void attack()


}
