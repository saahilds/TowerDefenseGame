package com.main.game;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class GamePaneWrapper {
    private Pane pane;

    //getter
    public Pane getPane() {
        return pane;
    }

    private PositionMap posMap;

    private int width;
    private int height;
    private int nodeWidth;
    private int nodeHeight;

    private int widthCapacity;

    public int getWidthCapacity() {
        return widthCapacity;
    }

    private int heightCapacity;

    public int getHeightCapacity() {
        return heightCapacity;
    }

    private int maxXidx;

    public int getMaxXidx() {
        return maxXidx;
    }

    private int maxYidx;

    public int getMaxYidx() {
        return maxYidx;
    }

    public GamePaneWrapper(
            Pane pane, int width, int height,
            int nodeWidth, int nodeHeight
    ) {
        this.pane = pane;
        this.width = width;
        this.height = height;
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.pane.setPrefWidth(width);
        this.pane.setPrefHeight(height);
        this.widthCapacity = (int) Math.floor(this.width / this.nodeWidth);
        this.heightCapacity = (int) Math.floor(this.height / this.nodeHeight);
        this.maxXidx = this.widthCapacity - 1;
        this.maxYidx = this.heightCapacity - 1;
        this.posMap = new PositionMap(this.width, this.height, this.nodeWidth, this.nodeHeight);

        initMouseSetting();
    }

    private void initMouseSetting() {
        pane.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                pane.setCursor(Cursor.HAND);
            }
        });
        pane.setOnMouseMoved(mouseEvent -> {
            if (mouseEvent != null) {
                System.out.println(mouseEvent);
            }
        });
    }

    public void addNodeWithXidxYidx(int xIdx, int yIdx, Node node) {
        this.posMap.setAtIdx(xIdx, yIdx, getPosNodeWithIdx(xIdx, yIdx, node));
    }

    public Node getPosNodeWithIdx(int xIdx, int yIdx, Node node) {
        this.getPane().getChildren().add(node);
        node.setTranslateY(nodeHeight * yIdx);
        node.setTranslateX(nodeWidth * xIdx);
        return node;
    }


    public static class PositionMap {
        private PositionObject[][] map;
        private int width;
        private int height;
        private int nodeWidth;
        private int nodeHeight;
        private int widthCapacity;
        private int heightCapacity;

        private PositionMap(
                int width, int height,
                int nodeWidth, int nodeHeight
        ) {
            this.width = width;
            this.height = height;
            this.nodeWidth = nodeWidth;
            this.nodeHeight = nodeHeight;
            this.widthCapacity = (int) Math.floor(this.width / this.nodeWidth);
            this.heightCapacity = (int) Math.floor(this.height / this.nodeHeight);
            this.map = new PositionObject[heightCapacity][widthCapacity];
            System.out.println(widthCapacity + "|" + heightCapacity);
            for (int yIdx = 0; yIdx < heightCapacity; yIdx++) {
                int yPos = yIdx * nodeHeight;
                for (int xIdx = 0; xIdx < widthCapacity; xIdx++) {
                    PositionObject posObj = new PositionObject(
                            xIdx * nodeWidth,
                            yPos
                    );
                    this.map[yIdx][xIdx] = posObj;
                }
            }
        }

        private void setAtIdx(int xIdx, int yIdx, Node node) {
            this.map[yIdx][xIdx].node = node;
        }

        public static class PositionObject {
            private int x;
            private int y;
            private Node node;

            PositionObject(int x, int y) {
                this.x = x;
                this.y = y;
            }

            PositionObject(int x, int y, Node node) {
                this(x, y);
                this.node = node;
            }
        }
    }
}