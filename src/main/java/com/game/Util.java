package com.game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Util {
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
