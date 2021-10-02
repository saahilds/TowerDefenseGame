package com.main;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class SimpleFactory implements EntityFactory {

    private String BRICK_PNG = "brick.png";

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        System.out.println("newEnemy");
        return FXGL.entityBuilder(data)
                .view(BRICK_PNG)
                .with(new ProjectileComponent(new Point2D(1, 0), 150))
                .build();
    }

    @Spawns("ally")
    public Entity newAlly(SpawnData data) {
        System.out.println("newAlly");
        var texture = FXGL.texture(BRICK_PNG).multiplyColor(Color.GREEN);

        return FXGL.entityBuilder(data)
                .view(texture)
                .with(new ProjectileComponent(new Point2D(-1, 0), 150))
                .build();
    }
}
