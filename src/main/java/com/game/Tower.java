package com.game;

import com.game.components.gameScene.TowerData;
import com.game.model.TowerType;
//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;

public class Tower {
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

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public TowerData getTowerData() {
        return towerData;
    }

    public void setTowerData(TowerData towerData) {
        this.towerData = towerData;
    }

    private TowerData towerData;

    public Tower(Rectangle rectangle, TowerData towerData) {
        this.shape = rectangle;
        this.towerData = towerData;
    }


    public Shape get() {
        return shape;
    }

    public ArrayList<Projectile> getProjectileArrayList() {

        return new ArrayList<Projectile>() {{
            Rectangle r1 = new Rectangle(10, 10);
            Rectangle r2 = new Rectangle(10, 10);
            Rectangle r3 = new Rectangle(10, 10);
            r1.setFill(Color.GHOSTWHITE);
            r2.setFill(Color.GHOSTWHITE);
            r3.setFill(Color.GHOSTWHITE);
            add(new Projectile(
                    new Rectangle(10, 10),
                    -1,
                    -1,
                    1000,
                    100
            ));
            add(new Projectile(
                    new Rectangle(10, 10),
                    0,
                    -1,
                    1000,
                    100
            ));
            add(new Projectile(
                    new Rectangle(10, 10),
                    +1,
                    -1,
                    1000,
                    100
            ));
        }};
    }

}
