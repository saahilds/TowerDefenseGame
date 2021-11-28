package com.game;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Util {
    public static boolean isIntersecting(int x, int y, Node target) {
        Bounds bounds = target.getBoundsInParent();
        double minX = bounds.getMinX();
        double maxX = bounds.getMaxX();
        double minY = bounds.getMinY();
        double maxY = bounds.getMaxY();
        boolean outX = minX > x || x > maxX;
        boolean outY = minY > y || y > maxY;

        return (outX || outY)
            ? false
            : true;
    }

    public static boolean isIntersecting(Node a, Node b) {
        return a.getBoundsInParent().intersects(b.getBoundsInParent());
    }

    public final ImagePattern fireImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/fireball_01.gif"))
    );

    public final ImagePattern fireExplosionImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/fireball_02.gif"))
    );

    public final ImagePattern eggImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/egg_01.gif"))
    );

    public Util() {};
}
