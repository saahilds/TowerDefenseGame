package com.game;

import com.main.game.entity.HealthBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Monument {
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

    public Monument() {

    }

    public Monument(Rectangle rectangle) {
        shape = rectangle;
        text = new Text(currHP + "/" + maxHP);
        text.setFill(Color.RED);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(shape, text);
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
        return currHP;
    }

}
