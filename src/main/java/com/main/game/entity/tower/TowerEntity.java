package com.main.game.entity.tower;

import com.main.config.Config;
import com.main.game.entity.EntityWithBackground;
import javafx.scene.shape.Rectangle;

public class TowerEntity extends EntityWithBackground {
    private Rectangle entity;
    private TowerData towerData;

    private int width;
    private int height;

    public TowerEntity() {
        super();
        width = Config.UNIT;
        height = Config.UNIT;
        entity = new Rectangle(width, height);
    }

}
