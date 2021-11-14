package com.game;

import com.main.game.entity.HealthBar;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Enemy {
    Rectangle shape;
    StackPane stackPane;
    Text text;

    public HealthBar getHpBar() {
        return hpBar;
    }

    public void setHpBar(HealthBar hpBar) {
        this.hpBar = hpBar;
    }

    private HealthBar hpBar;
    private int maxHP = 100;
    private int currHP = 100;

    public Enemy() {

    }

    public Enemy(Rectangle rectangle) {
        shape = rectangle;
        text = new Text(currHP + "/" + maxHP);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(shape, text);

//        group = new Group();
//        shape = rectangle;
//
//        this.maxHP = maxHP;
//        this.currHP = currHP;
//
//        int hpBarTranslateY = (int) (rectangle.getHeight() + 20) / 2;
//        this.hpBar = new HealthBar((int) rectangle.getWidth(), 10, maxHP, currHP);
//        hpBar.setTranslateY(-hpBarTranslateY);

//        this.group.getChildren().addAll(rectangle, hpBar);
    }

    public Shape get() {
        return shape;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public int applyDamage(int delta) {
        System.out.println(delta);
        currHP += delta;
        text.setText(currHP + "/" + maxHP);
//        hpBar.applyHpChange(delta);
        return currHP;
    }

}
