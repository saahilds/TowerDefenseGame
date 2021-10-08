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

<<<<<<< HEAD
    public static DataController dataController = new DataController();
=======
    private static DataController dataController = new DataController();
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
<<<<<<< HEAD
        mainContainer.loadScreen(GameScreenType.WELCOME_SCREEN, GameSettingDataMap.getFileName(GameScreenType.WELCOME_SCREEN));
=======
        mainContainer.loadScreen(GameScreenType.WELCOME_SCREEN,
                GameSettingDataMap.getFileName(GameScreenType.WELCOME_SCREEN));
>>>>>>> 38e31749abd80f5cbea4c212fd4d6f58bfe05a4b
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