package com.main.game.entity.tower;

import com.main.config.Config;
import com.main.game.entity.EntityWithBackground;
import javafx.scene.shape.Rectangle;

public class TowerEntity extends EntityWithBackground {
    private TowerData towerData;

    public TowerEntity() {
        super();
        this.getChildren().addAll(entity);
    }

}
