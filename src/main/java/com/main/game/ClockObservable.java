package com.main.game;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ClockObservable {
    public Observable timer = Observable.timer(100, TimeUnit.MILLISECONDS);
}
