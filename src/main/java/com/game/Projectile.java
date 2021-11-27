package com.game;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Projectile {
    private Shape shape;
    private boolean isCircle;
    private boolean isRectangle;
    private int range = 1000;
    private int damage;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

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

    //public Projectile() {}
    public Projectile(Circle circle) {
        shape = circle;
        isCircle = true;
    }

    public Projectile(Rectangle rectangle) {
        shape = rectangle;
        isRectangle = true;
    }

    public Projectile(Rectangle rectangle, int dx, int dy, int range, int damage) {
        this(rectangle);
        setDx(dx);
        setDy(dy);
        setRange(range);
        setDamage(damage);
    }

    public Shape get() {
        return shape;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
