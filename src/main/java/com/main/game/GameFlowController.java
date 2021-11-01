package com.main.game;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;

import java.util.concurrent.TimeUnit;

public class GameFlowController {
    boolean clockStarted = false;
    Long currTime = (long) 0;

    public ConnectableObservable<Long> intervalObservable$;

    public ConnectableObservable<Long> getIntervalObservable$() {
        return intervalObservable$;
    }

    public ConnectableObservable<Long> initClock() {
        clockStarted = true;
        // Cold Observable
//        Observable<Long> rawIntervalObservable$ = Observable.interval(0, 1000, TimeUnit.MILLISECONDS);
        // Hot Observable
//        intervalObservable$ = rawIntervalObservable$.publish();
//        intervalObservable$.connect();
        return intervalObservable$;
    }

    public GameFlowController() {
        intervalObservable$ = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .map(
                        time -> this.clockStarted ? this.currTime++ : this.currTime
                )
                .distinctUntilChanged()
                .publish();
        intervalObservable$.connect();
    }
}
