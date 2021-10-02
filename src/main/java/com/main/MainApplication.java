package com.main;

import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import com.almasb.fxgl.app.GameApplication;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static javafx.application.Application.launch;

public class MainApplication extends Application {
    public static String mainID = "main";
    public static String mainFile = "main.fxml";
    public static String initialScreenID = "initial-screen";
    public static String initialScreenFile = "initial-screen.fxml";
    public static String gameScreenID = "game-screen";
    public static String gameScreenFile = "game-screen.fxml";

    protected static Stage primaryStagen;

    @Override
    public void start(Stage primaryStage) {
        primaryStagen = primaryStage;

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApplication.mainID, MainApplication.mainFile);
        mainContainer.loadScreen(MainApplication.initialScreenID, MainApplication.initialScreenFile);
        mainContainer.loadScreen(MainApplication.gameScreenID, MainApplication.gameScreenFile);
        mainContainer.setScreen(MainApplication.mainID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return primaryStagen;
    }
}