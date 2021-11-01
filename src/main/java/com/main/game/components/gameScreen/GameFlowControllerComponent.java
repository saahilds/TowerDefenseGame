package com.main.game.components.gameScreen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.BootstrapFX;

public class GameFlowControllerComponent {
    private VBox gameFlowControllerEl;

    public GameFlowControllerComponent(VBox gameFlowControllerEl) {
        this.gameFlowControllerEl = gameFlowControllerEl;

        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        Button button = new Button("Start Game");
        button.getStyleClass().setAll("btn", "btn-primary");
        content.setCenter(button);
        gameFlowControllerEl.getChildren().addAll(content);


    }
}
