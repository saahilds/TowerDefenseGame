package com.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Tower {
    Rectangle shape;

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

    public Tower() {

    }

    public Tower(Rectangle rectangle) {
        shape = rectangle;
    }

    public Shape get() {
        return shape;
    }

    public Projectile getProjectile() {
        return new Projectile(
                new Rectangle(10, 10, Color.ORANGERED),
                getDx(),
                getDy()
        );
    }

    public int getDamage() {
        return 50;
    }
}
