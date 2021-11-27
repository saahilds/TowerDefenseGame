package com.game.components.gameScene;

import com.game.Tower;
import com.game.model.GameLevelType;
import com.game.model.TowerType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;


public class TowerMenuComponent extends VBox {
    private TowerData selectedTowerData;
    private AnchorPane root;

    public final ArrayList<TowerData> towerDataArrayList = new ArrayList<>() {{
        add(new TowerData(
                TowerType.TYPE_A,
                50,
                50,
                new ImagePattern(
                        new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
                ),
                "typeA",
                "typeA Description"
        ));
        add(new TowerData(
                TowerType.TYPE_B,
                100,
                100,
                new ImagePattern(
                        new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
                ),
                "typeB",
                "typeB Description"
        ));
        add(new TowerData(
                TowerType.TYPE_C,
                150,
                150,
                new ImagePattern(
                        new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
                ),
                "typeC",
                "typeC Description"
        ));
    }};

    private Function<String, Void> func;
    public TowerMenuComponent(GameLevelType gameLevel, AnchorPane root, Function<String, Void> method) {
        this.root = root;
        initTextList();
        func = method;
    }

    private void initTextList() {
//        for (int i = 0; i < towerDataArrayList.size(); i++) {
//            TowerData item = towerDataArrayList[i];
        for (TowerData item: towerDataArrayList) {

            System.out.println(item.getName());
            VBox group = new VBox();
            group.setPadding(new Insets(8.0D, 8.0D, 8.0D, 8.0D));
            group.setSpacing(10);


            Rectangle imgaeWrapper = new Rectangle(40, 40);
            imgaeWrapper.setFill(item.getImagePattern());
            Text name = new Text("" + item.getName());
            name.setFill(Color.GHOSTWHITE);

            HBox firstRow = new HBox();
            firstRow.getChildren().addAll(imgaeWrapper, name);
            firstRow.setAlignment(Pos.CENTER);

            Text description = new Text(item.getDescription());
            description.setFill(Color.GHOSTWHITE);
            Text price = new Text("COST " + item.getPrice());
            price.setFill(Color.GHOSTWHITE);


            Button purchaseButton = new Button("SELECT");
            purchaseButton.setOnMouseClicked((event) -> {
                func.apply(item.getName() + " selected");
                setSelectedTowerData(item);
            });
            purchaseButton.getStyleClass().setAll("btn", "btn-default");
            purchaseButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent;");
            group.getChildren().addAll(firstRow, price, description, purchaseButton);
            this.getChildren().add(group);
        }
    }

    public TowerData getSelectedTowerData() {
        return selectedTowerData;
    }

    public void setSelectedTowerData(TowerData selectedTowerData) {
        this.selectedTowerData = selectedTowerData;
    }
}
