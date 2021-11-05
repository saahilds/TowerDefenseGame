package com.main.game;

import com.main.config.Config;
import com.main.game.common.UpdateData;
import io.reactivex.Observable;
//import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
//import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.BehaviorSubject;

//import javax.security.auth.Subject;
import java.util.concurrent.TimeUnit;

public class GameFlowController {
    public boolean isClockStarted() {
        return clockStarted;
    }

    public void setClockStarted(boolean clockStarted) {
        this.clockStarted = clockStarted;
    }

    private boolean clockStarted = false;

    public Long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Long currTime) {
        this.currTime = currTime;
    }

    private Long currTime = (long) 0;

    private ConnectableObservable<Long> intervalObservable$;

    public ConnectableObservable<Long> getIntervalObservable$() {
        return intervalObservable$;
    }

    public ConnectableObservable<Long> initClock() {
        clockStarted = true;
        return intervalObservable$;
    }

    public BehaviorSubject<UpdateData> getGameUpdateDataSubject() {
        return gameUpdateDataSubject;
    }

    public void setGameUpdateDataSubject(BehaviorSubject<UpdateData> gameUpdateDataSubject) {
        this.gameUpdateDataSubject = gameUpdateDataSubject;
    }

    private BehaviorSubject<UpdateData> gameUpdateDataSubject;

    public GameFlowController() {
        intervalObservable$ = Observable.interval(0, Config.TICK, TimeUnit.MILLISECONDS)
                .map(time -> this.clockStarted ? this.currTime++ : this.currTime)
                .distinctUntilChanged()
                .publish();
        intervalObservable$.connect();
        gameUpdateDataSubject = BehaviorSubject.create();
    }
}
