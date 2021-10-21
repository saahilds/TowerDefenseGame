package com.main.game.entity;

import com.main.config.Config;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EntityWithHealth extends EntityWithBackground {

    private final float hpPXLRATIO = (100 / 32);

    private float maxHP;
    private float currHP;
    private int maxWidth;

    private int width;
    private int height;

    private Rectangle entity;
    private HealthBar hpBar;
    private Label hpText;

    public EntityWithHealth() {
        width = Config.UNIT;
        height = Config.UNIT;
        entity = new Rectangle(width, height);
    }

    public EntityWithHealth(int w, int h, float maxHP, float currHP) {
        this.width = w;
        this.height = h;

        this.maxHP = maxHP;
        this.currHP = currHP;
        entity = new Rectangle(width, height);

        int hpBarTranslateY = (int) (h + 20) / 2;
        this.hpBar = new HealthBar(w, 10, maxHP, currHP);
        hpBar.setTranslateY(-hpBarTranslateY);

        this.hpText = new Label(currHP + " / " + maxHP);
        int hpTextTranslateY = hpBarTranslateY + 15;
        this.hpText.setTranslateY(-hpTextTranslateY);

        this.getChildren().addAll(entity, hpBar, hpText);
    }

    public void initHealthBar() {
        Rectangle hpBar = new Rectangle(200.0, 50.0, Color.RED);
    }

    public float getPercentage() {
        return currHP / maxHP;
    }

    public float getMaxHP() {
        return maxHP;
    }
}
