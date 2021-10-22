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

    public void setAtPos(IndexPosition position, Node node) {
        setAtIdx(position.getX(), position.getY(), node);
    }

    public void setAtIdx(int xIdx, int yIdx, Node node) {
        if (xIdx < widthCapacity && yIdx < heightCapacity) {
            this.map[yIdx][xIdx].node = node;
        }
    }

    public Node getAtPos(IndexPosition position) {
        if (position != null) {
            return getAtIdx(position.getX(), position.getY());
        }
        return null;
    }

    public Node getAtIdx(int xIdx, int yIdx) {
        try {
            if (xIdx < widthCapacity && yIdx < heightCapacity) {
                return this.map[yIdx][xIdx].node;
            }
            return null;
        } catch (Exception exception) {
            System.out.println("ERR PositionMap getAtIdx" + exception);
        }
        return null;
    }

    public static class IndexPosition {
        private int x;
        private int y;

        @Override
        public String toString() {
            return "IndexPosition{"
                    + "x=" + x
                    + ", y=" + y
                    + '}';
        }

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
