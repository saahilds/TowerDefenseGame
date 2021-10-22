package com.main.game.gamePane;

import javafx.scene.Node;

public class PositionMap {
    private IndexPositionObject[][] map;
    private int width;
    private int height;
    private int nodeWidth;
    private int nodeHeight;
    private int widthCapacity;
    private int heightCapacity;

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

    public void setAtIdx(int xIdx, int yIdx, Node node) {
        this.map[yIdx][xIdx].node = node;
    }

    public static class IndexPosition {
        private int x;
        private int y;

        public IndexPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static class IndexPositionObject extends IndexPosition {
        private int x;
        private int y;
        private Node node;

        IndexPositionObject(int x, int y) {
            super(x, y);
        }

        IndexPositionObject(int x, int y, Node node) {
            this(x, y);
            this.node = node;
        }
    }
}
