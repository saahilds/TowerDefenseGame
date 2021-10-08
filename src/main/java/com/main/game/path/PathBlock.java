package com.main.game.path;

//import javafx.scene.image.Image;
//import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PathBlock extends Rectangle {
    private int pathIdx;
    public PathBlock(int w, int h, int pathIdx) {
        super(w, h);
        this.pathIdx = pathIdx;
    }
}
