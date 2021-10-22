package com.main.game.entity.tower;

//import com.main.config.Config;
import com.main.game.entity.EntityWithBackground;
import com.main.model.TowerEntityStatusType;
//import javafx.scene.shape.Rectangle;

public class TowerEntity extends EntityWithBackground {
    private TowerData towerData;
    private TowerEntityStatusType towerEntityStatus;

    public TowerEntity() {
        super();
        this.getChildren().addAll(entity);
    }
    public TowerEntity(TowerData towerData) {
        this();
        this.towerData = towerData;
        this.setFillWithImageSrc(towerData.getImageSource());
    }
    public TowerEntity(TowerData towerData, TowerEntityStatusType towerEntityStatus) {
        this(towerData);
        this.towerEntityStatus = towerEntityStatus;
    }

}
