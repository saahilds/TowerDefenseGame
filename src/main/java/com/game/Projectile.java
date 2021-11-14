package com.game;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Projectile {
    Shape shape;
    boolean isCircle;
    boolean isRectangle;

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

    public Projectile() {

    }
    public Projectile(Circle circle) {
        shape = circle;
        isCircle = true;
    }

    public Projectile(Rectangle rectangle) {
        shape = rectangle;
        isRectangle = true;
    }

    public Projectile(Rectangle rectangle, int dx, int dy) {
        this(rectangle);
        setDx(dx);
        setDy(dy);
    }

    public Shape get() {
        return shape;
    }

    public int getDamage() {
        return -50;
    }

}
