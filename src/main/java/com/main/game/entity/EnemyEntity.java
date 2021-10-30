package com.main.game.entity;

public class EnemyEntity extends EntityWithHealth {
    public EnemyEntity(float maxHP, float currHP) {
        super(maxHP, currHP);
    }

    public EnemyEntity(int w, int h, float maxHP, float currHP) {
        super(w, h, maxHP, currHP);
    }
}
