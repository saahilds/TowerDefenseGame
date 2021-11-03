package com.main.game.components.gameScreen;

import com.main.game.GameFlowController;
import io.reactivex.observables.ConnectableObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GameFlowControllerComponent {
    private boolean isGameStarted = false;
    private VBox gameFlowControllerEl;
    private GameFlowController gameFlowController;

    public GameFlowControllerComponent(
            VBox gameFlowControllerEl,
            GameFlowController gameFlowController
    ) {
        this.gameFlowControllerEl = gameFlowControllerEl;
        this.gameFlowController = gameFlowController;

        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        Button button = new Button("Start Game");
        button.getStyleClass().setAll("btn", "btn-primary");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                onClickGameStart(button);
            }
        });
        content.setCenter(button);
        gameFlowControllerEl.getChildren().addAll(content);
    }

    private void onClickGameStart(Button button) {
        if (!isGameStarted) {
            ConnectableObservable<Long> connectableObservable$ = gameFlowController.initClock();
            connectableObservable$.subscribe(this::onClockInterval);
            isGameStarted = true;
        } else {
            System.out.println("Game is Started");
        }
    }

    public void onClockInterval(Long tick) {
        System.out.println("=====CONT=====" + tick);
    }
}
