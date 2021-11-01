package com.main.game.entity;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;

public class Entity extends Group {
    public int getxIdx() {
        return xIdx;
    }

    public void setxIdx(int xIdx) {
        this.xIdx = xIdx;
    }

    public int getyIdx() {
        return yIdx;
    }

    public void setyIdx(int yIdx) {
        this.yIdx = yIdx;
    }

    private int xIdx = -1;
    private int yIdx = -1;

    public void setPosIdx(int xIdx, int yIdx) {
        setxIdx(xIdx);
        setyIdx(yIdx);
    }

    public Entity() {

    }
}
