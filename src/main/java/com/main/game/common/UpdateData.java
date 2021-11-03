package com.main.game.common;

import com.main.model.UpdateDataTypeType;

public class UpdateData {
    public UpdateDataTypeType type;
    public float damage;

    public UpdateData(
            UpdateDataTypeType type,
            float damage
    ) {
        this.type = type;
        this.damage = damage;
    }

}
