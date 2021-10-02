package com.main;

import com.main.config.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static String mainID = "main";
    public static String mainFile = "main.fxml";
    public static String initialScreenID = "initial-screen";
    public static String initialScreenFile = "initial-screen.fxml";
    public static String gameScreenID = "game-screen";
    public static String gameScreenFile = "game-screen.fxml";

    private static Stage primaryStagen;

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApplication.mainID, MainApplication.mainFile);
        mainContainer.loadScreen(MainApplication.initialScreenID, MainApplication.initialScreenFile);
        mainContainer.loadScreen(MainApplication.gameScreenID, MainApplication.gameScreenFile);
        mainContainer.setScreen(MainApplication.mainID);
        primaryStagen = primaryStage;
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, Config.STAGE_WIDTH, Config.STAGE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage(){
        return primaryStagen;
    }
}