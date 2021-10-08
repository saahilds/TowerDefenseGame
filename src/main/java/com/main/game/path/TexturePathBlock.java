package com.main.game.path;

import com.main.game.path.PathBlock;
import javafx.scene.paint.ImagePattern;

public class TexturePathBlock extends PathBlock {
    public TexturePathBlock(int w, int h, int pathIdx, ImagePattern textureImagePattern) {
        super(w, h, pathIdx);
        this.setFill(textureImagePattern);
    }
}