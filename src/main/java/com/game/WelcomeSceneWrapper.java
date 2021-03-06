package com.game;

//import com.game.MainApplication;
//import javafx.fxml.Initializable;
import javafx.geometry.Insets;
//import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.ResourceBundle;

public class WelcomeSceneWrapper extends SceneWrapper {

    private AnchorPane root;
    private Stage stage;
    private Scene scene;
    private StackPane welcomStackPane;

    public WelcomeSceneWrapper(
            Stage stage,
            AnchorPane root,
            Scene scene
    ) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;

        welcomStackPane = new StackPane();
        Text welcomeText = new Text(
                "Welcome!"
        );
        welcomeText.setFont(Font.font("Verdana", 20));
        welcomeText.setFill(Color.GHOSTWHITE);

        Rectangle logo = new Rectangle(320, 40);
        logo.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/com/game/text_01.png"))));
        logo.setTranslateY(50);

        Button startButton = new Button("Start Game");
        startButton.setOnMouseClicked(event -> {
            //initGameScene();
            initConfigScene(stage, root);
        });
        startButton.getStyleClass().setAll("btn", "btn-default");
        startButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent;");
        startButton.setTranslateY(110);
        welcomStackPane.getChildren().addAll(
                welcomeText,
                logo,
                startButton
        );
        welcomStackPane.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7),
                                CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        root.getChildren().add(welcomStackPane);
        root.setTopAnchor(welcomStackPane, 0.0);
        root.setLeftAnchor(welcomStackPane, 0.0);
        root.setBottomAnchor(welcomStackPane, 0.0);
        root.setRightAnchor(welcomStackPane, 0.0);
    }

}
