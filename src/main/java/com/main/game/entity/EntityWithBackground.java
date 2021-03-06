package com.main.game.entity;

import com.main.config.Config;
import javafx.scene.image.Image;
//import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class EntityWithBackground extends Entity {
    private int width;
    private int height;

    public Rectangle getEntity() {
        return entity;
    }

    public void setEntity(Rectangle entity) {
        this.entity = entity;
    }

    private Rectangle entity;

    public EntityWithBackground() {
        width = Config.UNIT;
        height = Config.UNIT;
        entity = new Rectangle(width, height);
    }

    public EntityWithBackground(int w, int h) {
        width = w;
        height = h;
        entity = new Rectangle(width, height);
    }

    public EntityWithBackground(String imgSrc) {
        this();
        setFillWithImageSrc(imgSrc);
    }

    public EntityWithBackground(Image image) {
        this();
        setFillWithImage(image);
    }

    public EntityWithBackground(ImagePattern pattern) {
        this();
        setFillWithImagePattern(pattern);
    }

    public void setFillWithImageSrc(String imgSrc) {
        Image image = new Image(
                getClass().getResourceAsStream(imgSrc)
        );
        setFillWithImage(image);
    }

    public void setFillWithImage(Image image) {
        ImagePattern imgPattern = new ImagePattern(image);
        setFillWithImagePattern(imgPattern);
    }

    public void setFillWithImagePattern(ImagePattern imgPattern) {
        System.out.println(imgPattern);
        if (entity != null) {
            entity.setFill(imgPattern);
        }
    }
}
