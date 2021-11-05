package com.main.game.gamePane;

import com.main.game.common.IndexPosition;
import com.main.game.common.IndexPositionObject;
import com.main.game.entity.Entity;
//import javafx.scene.Node;

public class PositionMap {
    private IndexPositionObject[][] map;
    private int width;
    private int height;
    private int nodeWidth;
    private int nodeHeight;
    private int widthCapacity;
    private int heightCapacity;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNodeWidth() {
        return nodeWidth;
    }

    public void setNodeWidth(int nodeWidth) {
        this.nodeWidth = nodeWidth;
    }

    public int getNodeHeight() {
        return nodeHeight;
    }

    public void setNodeHeight(int nodeHeight) {
        this.nodeHeight = nodeHeight;
    }

    public int getWidthCapacity() {
        return widthCapacity;
    }

    public void setWidthCapacity(int widthCapacity) {
        this.widthCapacity = widthCapacity;
    }

    public int getHeightCapacity() {
        return heightCapacity;
    }

    public void setHeightCapacity(int heightCapacity) {
        this.heightCapacity = heightCapacity;
    }

    PositionMap(
            int width, int height,
            int nodeWidth, int nodeHeight
    ) {
        this.width = width;
        this.height = height;
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.widthCapacity = (int) Math.floor(this.width / this.nodeWidth);
        this.heightCapacity = (int) Math.floor(this.height / this.nodeHeight);
        this.map = new IndexPositionObject[heightCapacity][widthCapacity];
        System.out.println(widthCapacity + "|" + heightCapacity);
        for (int yIdx = 0; yIdx < heightCapacity; yIdx++) {
            int yPos = yIdx * nodeHeight;
            for (int xIdx = 0; xIdx < widthCapacity; xIdx++) {
                IndexPositionObject posObj = new IndexPositionObject(
                        xIdx * nodeWidth,
                        yPos
                );
                this.map[yIdx][xIdx] = posObj;
            }
        }
    }

    public void setAtPos(IndexPosition position, Entity node) {
        setAtIdx(position.getX(), position.getY(), node);
    }

    public void setAtIdx(int xIdx, int yIdx, Entity node) {
        if (xIdx < widthCapacity && yIdx < heightCapacity) {
            this.map[yIdx][xIdx].setNode(node);
        }
    }

    public Entity getAtPos(IndexPosition position) {
        if (position != null) {
            return getAtIdx(position.getX(), position.getY());
        }
        return null;
    }

    public Entity getAtIdx(int xIdx, int yIdx) {
        try {
            if (xIdx < widthCapacity && yIdx < heightCapacity) {
                return this.map[yIdx][xIdx].getNode();
            }
            return null;
        } catch (Exception exception) {
            System.out.println("ERR PositionMap getAtIdx" + exception);
        }
        return null;
    }

}
