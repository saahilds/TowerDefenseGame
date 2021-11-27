package com.game;

import javafx.scene.Node;

public class Util {
    public static boolean isIntersecting(Node a, Node b) {
        return a.getBoundsInParent().intersects(b.getBoundsInParent());
    }
}
