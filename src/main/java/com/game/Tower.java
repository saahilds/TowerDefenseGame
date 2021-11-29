package com.game;

import com.game.components.gameScene.TowerData;
//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
import com.game.model.TowerType;
import javafx.scene.image.Image;
//import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Tower {
    private Rectangle shape;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level = 1;

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

    public Shape get() {
        return shape;
    }

    public int getTowerUpgradeLevel() {
        return towerUpgradeLevel;
    }

    public void setTowerUpgradeLevel(int towerUpgradeLevel) {
        this.towerUpgradeLevel = towerUpgradeLevel;
    }

    private int towerUpgradeLevel = 1;

    private TowerData towerData;
    private Util util = new Util();

    public Tower(Rectangle rectangle, TowerData towerData) {
        this.shape = rectangle;
        this.towerData = towerData;

    }

    public boolean requestLevelUp() {
        if (level >= 3) {
            return false;
        }
        double w = getShape().getWidth();
        double h = getShape().getHeight();
        getShape().setWidth(w * 1.5);
        getShape().setHeight(h * 1.5);
        setLevel(getLevel() + 1);
        return true;
    }

    public ArrayList<Projectile> getProjectileArrayList() {
        int speed;
        int damage;
        double levelCoef = (level == 1)
                ? 1
                : (level == 2)
                    ? 1.25
                    : 1.75;
        double wh = 30  * levelCoef;
        switch (this.towerData.getType()) {
            case TYPE_A:
                speed = 3 * towerUpgradeLevel;
                damage = -30 * towerUpgradeLevel;
                return new ArrayList<Projectile>() {{
                    Rectangle r1 = new Rectangle(wh, wh);
                    r1.setFill(util.fireImgPattern);
                    add(new Projectile(
                            r1,
                            0,
                            1 * (speed),
                            1000,
                            damage
                    ));
                }};
            case TYPE_B:
                speed = 2 * towerUpgradeLevel;
                damage = -50 * towerUpgradeLevel;
                return new ArrayList<Projectile>() {{
                    Rectangle r1 = new Rectangle(wh, wh);
                    Rectangle r2 = new Rectangle(wh, wh);
                    Rectangle r3 = new Rectangle(wh, wh);
                    r1.setFill(util.eggImgPattern);
                    r2.setFill(util.eggImgPattern);
                    r3.setFill(util.eggImgPattern);
                    add(new Projectile(
                            r1,
                            -1 * (speed),
                            2 * (speed),
                            1000,
                            damage
                    ));
//                    add(new Projectile(
//                            r2,
//                            0,
//                            1 * (speed),
//                            1000,
//                            damage
//                    ));
                    add(new Projectile(
                            r3,
                            (speed),
                            2 * (speed),
                            1000,
                            damage
                    ));
                }};
            case TYPE_C:
                speed = 3 * towerUpgradeLevel;
                damage = -50 * towerUpgradeLevel;
                return new ArrayList<Projectile>() {{
                    Rectangle r1 = new Rectangle(wh, wh);
                    Rectangle r2 = new Rectangle(wh, wh);
                    Rectangle r3 = new Rectangle(wh, wh);
                    r1.setFill(util.fireExplosionImgPattern);
                    r2.setFill(util.fireExplosionImgPattern);
                    r3.setFill(util.fireExplosionImgPattern);

                    add(new Projectile(
                            r1,
                            -1 * (speed),
                            1 * (speed),
                            1000,
                            damage
                    ));
                    add(new Projectile(
                            r2,
                            0,
                            1 * (speed),
                            1000,
                            damage
                    ));
                    add(new Projectile(
                            r3,
                            (speed),
                            1 * (speed),
                            1000,
                            damage
                    ));
                }};
            default:
                return new ArrayList<Projectile>();
        }

    }

}
