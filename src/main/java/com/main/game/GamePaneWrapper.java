package com.main.game;

import com.main.config.Config;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class GamePaneWrapper {
    public static class PositionMap {
        public static class PositionObject {
            int x;
            int y;
            Node node;

            PositionObject(int x, int y) {
                this.x = x;
                this.y = y;
            }

            PositionObject(int x, int y, Node node) {
                this(x, y);
                this.node = node;
            }
        }

        public PositionObject[][] map;
        public int width;
        public int height;
        public int nodeWidth;
        public int nodeHeight;
        public int widthCapacity;
        public int heightCapacity;

        public PositionMap(
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

        public void setAtIdx(int xIdx, int yIdx, Node node) {

            this.map[yIdx][xIdx].node = node;
        }
    }

    private Pane pane;

    //getter
    public Pane getPane() {
        return pane;
    }

    private PositionMap posMap;

    public int width;
    public int height;
    public int nodeWidth;
    public int nodeHeight;

    public int widthCapacity;
    public int heightCapacity;
    public int maxXidx;
    public int maxYidx;

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
    }

    public void addNodeWithXposYpos() {

    }

    public void addNodeWithXidxYidx(int xIdx, int yIdx, Node node) {
        this.posMap.setAtIdx(xIdx, yIdx, getPosNodeWithIdx(xIdx, yIdx, node));
    }

    public void addNodeWithXidxRangeYidxRange() {

    }

    public Node getPosNodeWithIdx(int xIdx, int yIdx, Node node) {
        this.getPane().getChildren().add(node);
//        System.out.println("getPosNodeWithIdx 1" + node);
//        System.out.println(nodeHeight * yIdx + "|" + nodeWidth * xIdx);
        node.setTranslateY(nodeHeight * yIdx);
        node.setTranslateX(nodeWidth * xIdx);
//        System.out.println("getPosNodeWithIdx 2" + node);
        return node;
    }


}