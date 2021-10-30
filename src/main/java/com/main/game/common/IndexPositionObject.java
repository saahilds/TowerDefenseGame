package com.main.game.common;

import javafx.scene.Node;

public class IndexPositionObject extends IndexPosition {
    private Node node;
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public IndexPositionObject(int x, int y) {
        super(x, y);
    }

    IndexPositionObject(int x, int y, Node node) {
        this(x, y);
        this.node = node;
    }
}

