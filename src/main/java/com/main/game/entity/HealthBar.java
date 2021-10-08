package com.main.game.entity;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar extends StackPane {
    private int w;
    private int h;
    private float maxHP;
    private float currHP;

    private Rectangle hpBar;
    private Rectangle hpBarBack;

    public HealthBar(int w, int h, float maxHP, float currHP) {
        this.w = w;
        this.h = h;
        this.setMaxSize(w, h);

        this.maxHP = maxHP;
        this.currHP = currHP;

        this.hpBarBack = new Rectangle(w, h, Color.LIGHTGRAY);
        this.hpBar = new Rectangle(getCurrHpWidth(), h, Color.RED);
        this.setAlignment(hpBarBack, Pos.CENTER_LEFT);
        this.setAlignment(hpBar, Pos.CENTER_LEFT);

        this.getChildren().addAll(hpBarBack, hpBar);
    }

    public int getCurrHpWidth() {
        float percentage = currHP / maxHP;
        return (int) Math.ceil(w * percentage);
    }
}
