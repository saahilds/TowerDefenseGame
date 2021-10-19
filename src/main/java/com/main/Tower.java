package com.main;

public class Tower {
    private String name;
    private String description;
    private int cost;
    private int[][] position;
    private int dps;
    private int health;

    public Tower(String n, String desc, int c, int[][] pos, int dps, int h) {
        name = n;
        description = desc;
        cost = c;
        position = pos;
        this.dps = dps;
        health = h;
    }


}
