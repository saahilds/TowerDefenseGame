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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    private int dx = 1;

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    private boolean isBoss = false;

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
    private int currHP = 100;

    public Enemy() {

    }

    public Enemy(int width, int height, int level, int baseHealth, int baseDamage, boolean isBoss) {
        this.isBoss = isBoss;
        this.level = level;
        int w;
        int h;
        if (!isBoss) {
            w = (int) (width * (1 + (level - 1) * 0.25));
            h = (int) (height * (1 + (level - 1) * 0.25));
            if (level < 3) {
                setDx(3);
            } else if (level == 3) {
                setDx(2);
            } else {
                setDx(1);
            }
            damage = (int) (baseDamage * (1 + (level - 1) * 0.25));
            maxHP = (int) (baseHealth * (1 + (level - 1) * 0.5));
            currHP = maxHP;
        } else {
            w = (int) (width * 3);
            h = (int) (height * 3);
            setDx(1);
            damage = (int) (baseDamage * (1 + (level - 1) * 0.25));
            maxHP = (int) (baseHealth * 20);
            currHP = maxHP;
        }
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
        Image img = new Image(getClass().getResourceAsStream("/com/game/dino_09.gif"));;
        if (isBoss) {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_10.gif"));
        } else if (level == 1) {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_09.gif"));
        } else if (level == 2) {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_07.gif"));
        } else if (level == 3) {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_06.gif"));
        } else if (level == 4) {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_04.gif"));
//        } else if (level == 5) {
        } else {
            img = new Image(getClass().getResourceAsStream("/com/game/dino_08.gif"));
        }
        return new ImagePattern(img);
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
