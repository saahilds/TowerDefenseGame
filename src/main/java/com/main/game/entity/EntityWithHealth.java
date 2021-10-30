package com.main.game.entity;

import com.main.config.Config;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EntityWithHealth extends EntityWithBackground {

    private final float hpPXLRATIO = (100 / 32);

    private float maxHP;
    private float currHP;
    private int maxWidth;

    private HealthBar hpBar;
    private Label hpText;

    public EntityWithHealth() {
        super();
    }

    public EntityWithHealth(float maxHP, float currHP) {
        this(Config.UNIT, Config.UNIT, maxHP, currHP);
    }

    public EntityWithHealth(int w, int h, float maxHP, float currHP) {
        super(w, h);

        this.maxHP = maxHP;
        this.currHP = currHP;

        int hpBarTranslateY = (int) (h + 20) / 2;
        this.hpBar = new HealthBar(w, 10, maxHP, currHP);
        hpBar.setTranslateY(-hpBarTranslateY);

        this.hpText = new Label(currHP + " / " + maxHP);
        int hpTextTranslateY = hpBarTranslateY + 15;
//        this.hpText.setTranslateY(-hpTextTranslateY);
//        this.getChildren().addAll(getEntity(), hpBar, hpText);
        this.getChildren().addAll(getEntity(), hpBar);
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
