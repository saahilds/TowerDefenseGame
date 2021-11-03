package com.main.game.common;

public class UpdateData {
    public UpdateDataTypeType type;
    public int damage;

    public UpdateData(
            UpdateDataTypeType type,
            int damage
    ) {
        this.type = type;
        this.damage = damage;
    }

    enum UpdateDataTypeType {
        NONE,
        PLAYER_DAMAGE,
        END_GAME
    }
}
