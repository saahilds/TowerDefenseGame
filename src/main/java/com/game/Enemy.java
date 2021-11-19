package com.game;

import com.main.game.entity.HealthBar;
//import javafx.scene.Group;
//import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Enemy {
    private Rectangle shape;
    private StackPane stackPane;
    private Text text;

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public HealthBar getHpBar() {
        return hpBar;
    }

    public void setHpBar(HealthBar hpBar) {
        this.hpBar = hpBar;
    }

    private HealthBar hpBar;
    private int damage = 10;
    private int maxHP = 100;

    public int getCurrHP() {
        return currHP;
    }

    private int currHP = 100;

    public Enemy() {

    }

    public Enemy(int width, int height, int level, int baseHealth, int baseDamage) {
        int w = (int) (width * (1 + (level - 1) * 0.25));
        int h = (int) (height * (1 + (level - 1) * 0.25));
        damage = (int) (baseDamage * (1 + (level - 1) * 0.25));
        maxHP = (int) (baseHealth * (1 + (level - 1) * 0.5));
        currHP = maxHP;
        Rectangle rectangle = new Rectangle(w, h);
        rectangle.setFill(getImage());
        shape = rectangle;
        text = new Text(currHP + "/" + maxHP);
        text.setFill(Color.GRAY);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(shape, text);
    }

    public Shape get() {
        return shape;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    private ImagePattern getImage() {
        return new ImagePattern(
                new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
        );
    }

    public int applyDamage(int delta) {
        currHP += delta;
        text.setText(currHP + "/" + maxHP);
        return currHP;
    }

    public int getDamage() {
        return -1 * damage;
    }

}
