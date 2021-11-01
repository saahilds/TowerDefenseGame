package com.main.game.entity;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class EnemyEntity extends EntityWithHealth {
    public EnemyEntity(float maxHP, float currHP) {
        super(maxHP, currHP);

        movementTest();
    }

    private void movementTest() {
        System.out.println("=====================");
        System.out.println(getxIdx() + "|" + getyIdx());
        System.out.println(getTranslateX());
        System.out.println("=====================");
        //Instantiating TranslateTransition class
        TranslateTransition translate = new TranslateTransition();

        //shifting the X coordinate of the centre of the circle by 400
        translate.setByX(this.getTranslateX() + 32);

        //setting the duration for the Translate transition
        translate.setDuration(Duration.millis(1000));

        //setting cycle count for the Translate transition
//        translate.setCycleCount(500);

        //the transition will set to be auto reversed by setting this to true
//        translate.setAutoReverse(true);

        translate.setNode(this);

        translate.play();
    }
}
