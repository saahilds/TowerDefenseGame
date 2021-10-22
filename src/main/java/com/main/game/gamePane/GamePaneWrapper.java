package com.main.game.gamePane;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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

    private EventHandler<? super MouseEvent> onMouseClickedHandler;

    public void setOnMouseClickedHandler(EventHandler<? super MouseEvent> onMouseClickedHandler) {
        this.onMouseClickedHandler = onMouseClickedHandler;
        pane.setOnMouseClicked(onMouseClickedHandler);
    }

    public EventHandler<? super MouseEvent> getOnMouseClickedHandler() {
        return onMouseClickedHandler;
    }

    private EventHandler<? super MouseEvent> onMouseMovedHandler;

    public void setOnMouseMovedHandler(EventHandler<? super MouseEvent> onMouseMovedHandler) {
        this.onMouseMovedHandler = onMouseMovedHandler;
        pane.setOnMouseMoved(onMouseMovedHandler);
    }

    public EventHandler<? super MouseEvent> getOnMouseMovedHandler() {
        return onMouseMovedHandler;
    }

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
    }

    public void addNodeWithXidxYidx(int xIdx, int yIdx, Node node) {
        this.posMap.setAtIdx(xIdx, yIdx, getPosNodeWithIdx(xIdx, yIdx, node));
    }

    public void addNodeWithPosition(int xIdx, int yIdx, Node node) {
        this.posMap.setAtIdx(xIdx, yIdx, getPosNodeWithIdx(xIdx, yIdx, node));
    }

    public Node getPosNodeWithIdx(int xIdx, int yIdx, Node node) {
        this.getPane().getChildren().add(node);
        node.setTranslateY(nodeHeight * yIdx);
        node.setTranslateX(nodeWidth * xIdx);
        return node;
    }

    public PositionMap.IndexPosition getIdxWithPos(double xPos, double yPos) {
        return new PositionMap.IndexPosition(
                (int) xPos / nodeWidth,
                (int) yPos / nodeHeight
        );
    }

}