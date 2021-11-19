//package com.test;
//
//import com.main.config.Config;
//import com.main.game.DataController;
//import com.main.game.GameDataController;
//import com.main.game.GameFlowController;
//import com.main.game.common.UpdateData;
//import com.main.game.entity.PlayerEntity;
//import com.main.game.gamePane.GamePaneWrapper;
//import com.main.model.GameLevelType;
//import com.main.model.UpdateDataTypeType;
//import io.reactivex.Observable;
//import io.reactivex.observables.ConnectableObservable;
//import io.reactivex.observers.TestObserver;
//import io.reactivex.subjects.BehaviorSubject;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//
//import static org.junit.Assert.*;
//
//public class RxJavaTest extends ApplicationTest {
//
//    private GamePaneWrapper gamePaneWrapper;
//    private GameDataController gameDataController;
//    private DataController dataController;
//    private AnchorPane pane;
//    private GameFlowController gameFlowController;
//
//    private Stage mainstage;
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        gameFlowController = new GameFlowController();
//        pane = new AnchorPane();
//        gamePaneWrapper = new GamePaneWrapper(
//                pane, Config.STAGE_WIDTH - Config.LEFT_TOOLBAR_WIDTH,
//                Config.STAGE_HEIGHT - Config.GNB_TOP_HEIGHT,
//                Config.UNIT, Config.UNIT,
//                gameFlowController
//        );
//        dataController = new DataController();
//        gameDataController = new GameDataController(
//                gamePaneWrapper,
//                dataController,
//                gameFlowController,
//                GameLevelType.HARD
//        );
//        mainstage = stage;
//        Scene scene = new Scene(pane, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
//        stage.setScene(scene);
//        stage.show();
//        stage.toFront();
//    }
//
//    @Test
//    public void testRxJavaIntervalObservableInit() {
//        ConnectableObservable<Long> observable = gameFlowController.getIntervalObservable();
//        observable.test();
//        observable
//                .autoConnect()
//                .test(true)
//                .assertEmpty();
//    }
//
//    @Test
//    public void testRxJavaGameUpdateDataObservableInit() {
//        BehaviorSubject<UpdateData> subject = gameFlowController.getGameUpdateDataSubject();
//        Observable<UpdateData> observable
//                = Observable.wrap(gameFlowController.getGameUpdateDataSubject());
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.END_GAME, 0)
//        );
//        observable.subscribe(data -> assertEquals(UpdateDataTypeType.END_GAME, data.getType()));
//    }
//
//    @Test
//    public void testRxJavaGameUpdateDataObservableInitB() {
//        TestObserver<UpdateData> observer = TestObserver.create();
//        BehaviorSubject<UpdateData> subject = gameFlowController.getGameUpdateDataSubject();
//        Observable<UpdateData> observable
//                = Observable.wrap(gameFlowController.getGameUpdateDataSubject());
//        float hp;
//        float delta = (float) 0;
//        PlayerEntity player = gameDataController.getPlayer();
//        assertNotNull(player);
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 100), ((float) 100));
//
//        observable.subscribe(observer);
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 80), ((float) 80));
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 60), ((float) 60));
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 40), ((float) 40));
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 20), ((float) 20));
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        hp = player.applyHpChange(delta);
//        assertEquals(hp, ((float) 0), ((float) 0));
//
//        subject.onNext(
//                new UpdateData(UpdateDataTypeType.PLAYER_DAMAGE, -20)
//        );
//        assertEquals(hp, ((float) 0), ((float) 0));
//
//        observer.assertNoErrors();
//        observer.assertValueCount(8);
//    }
//}
