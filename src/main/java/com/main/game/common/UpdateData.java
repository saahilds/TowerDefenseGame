package com.main.game.common;

import com.main.model.UpdateDataTypeType;

public class UpdateData {
    private UpdateDataTypeType type;
    private float damage;

    public UpdateDataTypeType getType() {
        return type;
    }

    public void setType(UpdateDataTypeType type) {
        this.type = type;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public UpdateData(
            UpdateDataTypeType type,
            float damage
    ) {
        this.type = type;
        this.damage = damage;
    }

}
