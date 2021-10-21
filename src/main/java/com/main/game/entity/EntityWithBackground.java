package com.main.game.entity;

import com.main.config.Config;
import com.main.game.entity.AbstractEntity;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class EntityWithBackground extends StackPane {
    private int width;
    private int height;
    private Rectangle entity;

    public EntityWithBackground() {
        width = Config.UNIT;
        height = Config.UNIT;
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
        if (entity != null) {
            entity.setFill(imgPattern);
        }
    }
}
