package com.game;

import com.game.model.GameLevelType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import java.util.ArrayList;

public class ConfigSceneWrapper extends SceneWrapper {

    private AnchorPane root;
    private Stage stage;
    private Scene scene;
    private StackPane configStackPane;

    private boolean isNameValid;
    private boolean isLevelValid;

    private TextField nameTextField;
    private Text errorText;

    public ConfigSceneWrapper(
            Stage stage,
            AnchorPane root,
            Scene scene
    ) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;

        configStackPane = new StackPane();

        Button toWelcomeSceneButton = new Button("To Welcome Screen");
        toWelcomeSceneButton.setOnMouseClicked(event -> {
            initWelcomeScene(stage, root);
        });
        toWelcomeSceneButton.getStyleClass().setAll("btn", "btn-default");
        toWelcomeSceneButton.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: transparent;");
        toWelcomeSceneButton.setAlignment(Pos.CENTER_LEFT);
        //toWelcomeSceneButton.setMaxWidth(0);
        root.setTopAnchor(toWelcomeSceneButton, 16.0);
        root.setLeftAnchor(toWelcomeSceneButton, 8.0);

        Text nameInputText = new Text(
                "Type your name"
        );
        nameInputText.setFont(Font.font("Verdana", 20));
        nameInputText.setFill(Color.GHOSTWHITE);
        nameInputText.setTranslateY(-80);

        nameTextField = new TextField("Name: ");
        nameTextField.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: white;");
        nameTextField.setMaxWidth(200);
        nameTextField.setTranslateY(-40);
        Button saveName = new Button("Save Name");
        saveName.getStyleClass().setAll("btn", "btn-default");
        saveName.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: white;");
        saveName.setTranslateX(150);
        saveName.setTranslateY(-40);

        saveName.setOnMouseClicked(event -> {
            String temp = nameTextField.getText();
            setUsername(temp);
            if (getUsername() == null || getUsername().trim().equals("")) {
                setNameValid(false);
            } else {
                setNameValid(true);
            }
        });






        /**
         * LEVEL
         */
        Text levelInputText = new Text(
                "Select Level"
        );
        levelInputText.setFont(Font.font("Verdana", 20));
        levelInputText.setFill(Color.GHOSTWHITE);
        levelInputText.setTranslateY(0);

        for (GameLevelType level: getGameLevelTypeArr()) {
            String text;
            int xTranslate = 0;
            if (level == GameLevelType.EASY) {
                text = "Easy";
                xTranslate = -100;
            } else if (level == GameLevelType.NORMAL) {
                text = "Normal";
            } else {
                text = "Hard";
                xTranslate = 100;
            }
            Button levelButton = new Button(text);
            levelButton.setAlignment(Pos.CENTER);
            levelButton.setOnMouseClicked(event -> {
                modalToast(root, text + " selected");
                if (text.equals("Easy")) {
                    setGameLevel(GameLevelType.EASY);
                    setLevelValid(true);
                } else if (text.equals("Normal")) {
                    setGameLevel(GameLevelType.NORMAL);
                    setLevelValid(true);
                } else if (text.equals("Hard")) {
                    setGameLevel(GameLevelType.HARD);
                    setLevelValid(true);
                }
            });
            levelButton.getStyleClass().setAll("btn", "btn-default");
            levelButton.setStyle("-fx-text-fill: white; "
                    + "-fx-background-color: transparent; -fx-border-color: white;");
            levelButton.setMaxWidth(80);
            levelButton.setTranslateX(xTranslate);
            levelButton.setTranslateY(40);
            configStackPane.getChildren().add(levelButton);
        }

        /**
         * START
         */
        Button startButton = new Button("Start Game");
        startButton.setOnMouseClicked(event -> {
            if (isNameValid()) {
                if (isLevelValid()) {
                    onClickGameStart();
                } else {
                    modalToast(root, "Choose a game level!");
                }
            } else {
                modalToast(root, "Name is not valid!");
            }
        });
        startButton.getStyleClass().setAll("btn", "btn-default");
        startButton.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: white;");
        startButton.setAlignment(Pos.CENTER);
        startButton.setMaxWidth(280);
        startButton.setTranslateY(80);
        configStackPane.getChildren().addAll(
                nameInputText,
                nameTextField,
                saveName,
                levelInputText,
                startButton
        );
        configStackPane.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7),
                                CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        root.getChildren().addAll(
                configStackPane,
                toWelcomeSceneButton
        );
        root.setTopAnchor(configStackPane, 0.0);
        root.setLeftAnchor(configStackPane, 0.0);
        root.setBottomAnchor(configStackPane, 0.0);
        root.setRightAnchor(configStackPane, 0.0);
    }

    public void onClickGameStart() {
        initGameScene(stage, root);
    }

    public boolean isNameValid() {
        return isNameValid;
    }

    public void setNameValid(boolean nameValid) {
        isNameValid = nameValid;
    }

    public boolean isLevelValid() {
        return isLevelValid;
    }

    public void setLevelValid(boolean levelValid) {
        isLevelValid = levelValid;
    }
}
