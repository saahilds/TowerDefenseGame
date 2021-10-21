package com.main.game.entity;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
//import javafx.scene.text.TextBoundsType;
//import org.w3c.dom.Text;

public class EntityWithHealth extends StackPane implements EntityWithBackground {

    private final float hpPXLRATIO = (100 / 32);

    private float maxHP;
    private float currHP;
    private int maxWidth;


    private Rectangle entity;
    private HealthBar hpBar;
    private Label hpText;


    public EntityWithHealth(int w, int h, float maxHP, float currHP) {
        this.maxHP = maxHP;
        this.currHP = currHP;
        this.entity = new Rectangle(w, h);


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

    public void setEntityImgPattern(ImagePattern pattern) {
        if (entity != null) {
            entity.setFill(pattern);
        }
    }

    public float getMaxHP() {
        return maxHP;
    }
}
