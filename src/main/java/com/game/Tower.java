package com.game;

import com.game.model.TowerType;
//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.HashMap;

public class Tower {
    private TowerType towerType;
    private int price;
    private int damage;
    private static HashMap<TowerType, int[]> towerAttributes = new HashMap<>() {{
            int[] a = new int[2];
            a[0] = 50;
            a[1] = -10;
            put(TowerType.TYPE_A, a);
            int[] b = new int[2];
            b[0] = 75;
            b[1] = -15;
            put(TowerType.TYPE_B, b);
            int[] c = new int[2];
            c[0] = 100;
            c[1] = -20;
            put(TowerType.TYPE_C, c);
        }};

    public static HashMap<TowerType, int[]> getTowerAttributes() {
        return towerAttributes;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    private Rectangle shape;

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    private int dx = 0;
    private int dy = -4;
    private int range = 400;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Tower() {

    }

    public Tower(Rectangle rectangle, TowerType towerType, int price, int damage) {
        shape = rectangle;
        this.towerType = towerType;
        this.price = price;
        this.damage = damage;
    }


    public Shape get() {
        return shape;
    }

    public Projectile getProjectile() {
        if (towerType == TowerType.TYPE_A) {
            return new Projectile(
                    new Rectangle(10, 10, Color.YELLOW),
                    getDx(),
                    getDy(),
                    getRange(),
                    getDamage());

        } else if (towerType == TowerType.TYPE_B) {
            return new Projectile(
                    new Rectangle(10, 10, Color.ORANGE),
                    getDx(),
                    getDy(),
                    getRange(),
                    getDamage());
        }

        return new Projectile(
                    new Rectangle(10, 10, Color.RED),
                    getDx(),
                    getDy(),
                    getRange(),
                    getDamage());

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
