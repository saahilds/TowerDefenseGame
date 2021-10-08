package com.main;

import com.main.config.Config;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameScreenType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private static Stage primaryStage;

    public static DataController dataController = new DataController();

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(GameScreenType.WELCOME_SCREEN, GameSettingDataMap.getFileName(GameScreenType.WELCOME_SCREEN));
        mainContainer.setScreen(GameScreenType.WELCOME_SCREEN);
        MainApplication.primaryStage = primaryStage;
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return primaryStage;
    }

    public static DataController getDataController() {
        return dataController;
    }
}