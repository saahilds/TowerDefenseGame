package com.main.game.common;

public class IndexPosition {
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

    @Override
    public boolean equals(Object o) {
        try {
            if (o instanceof IndexPosition) {
                boolean x = getX() == ((IndexPosition) o).getX();
                boolean y = getY() == ((IndexPosition) o).getY();
                return (x && y);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

