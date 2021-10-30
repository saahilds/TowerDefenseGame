package com.main.game.entity;

public class EnemyEntity extends EntityWithHealth {
    public EnemyEntity(float maxHP, float currHP) {
        super(maxHP, currHP);
        movementTest();
    }

    private void movementTest() {
        System.out.println("=====================");
        System.out.println(getxIdx() + "|" + getyIdx());
        System.out.println("=====================");
    }
}
