package com.main.game.common;

import com.main.game.entity.Entity;
//import javafx.scene.Node;

public class IndexPositionObject extends IndexPosition {
    private Entity node;
    public Entity getNode() {
        return node;
    }

    public void setNode(Entity node) {
        this.node = node;
    }

    public IndexPositionObject(int x, int y) {
        super(x, y);
    }

    IndexPositionObject(int x, int y, Entity node) {
        this(x, y);
        this.node = node;
    }
}

