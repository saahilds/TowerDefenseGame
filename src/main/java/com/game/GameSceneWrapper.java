package com.game;

//import com.game.components.gameScene.TowerData;
import com.game.model.TowerType;
import com.main.config.Config;
import com.game.components.gameScene.TowerMenuComponent;
//import com.main.model.GameLevelType;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;

public class GameSceneWrapper extends SceneWrapper {

    private int dx;
    private int dy;
    private int x = 150;
    private int y = 470;
    private int projectileSpeed = 10;

    public int getGameMoneyIncrementSpeed() {
        return gameMoneyIncrementSpeed;
    }

    private int gameMoneyIncrementSpeed = 50;

    public int getMoneyIncrementAmount() {
        return moneyIncrementAmount;
    }

    public void setMoneyIncrementAmount(int moneyIncrementAmount) {
        this.moneyIncrementAmount = moneyIncrementAmount;
    }

    private int moneyIncrementAmount = 50;

    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    private int counter = 0;
    private int enemySpeed = 4;
    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;
    private boolean isShooting;
    private int spawnTime = 180;
    private final int pathHeight = 100;

    private AnchorPane root;
    private Stage stage;
    private Scene scene;

    private Projectile playerProjectile;
    private Circle player = new Circle(x, y, 10, Color.GRAY);
    private Enemy enemy;

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    private ArrayList<Projectile> projectiles = new ArrayList();

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    private ArrayList<Tower> towers = new ArrayList();
    private ArrayList<Enemy> enemies = new ArrayList();

    private TowerMenuComponent towerMenuComponent;
    private Monument monument;

    private boolean isStopped = false;
    private boolean showMenu = false;

    private StackPane startStackPane;

    public StackPane getStartStackPane() {
        return startStackPane;
    }

    public void setStartStackPane(StackPane startStackPane) {
        this.startStackPane = startStackPane;
    }

    private int monumentHealth;

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public void setMonumentHealth(int monumentHealth) {
        this.monumentHealth = monumentHealth;
        monumentHealthText.setText("Health: " + monumentHealth);
    }

    private int gameMoney;

    public int getGameMoney() {
        return gameMoney;
    }


    public void setGameMoney(int gameMoney) {
        this.gameMoney = gameMoney;
        gameMoneyText.setText("$: " + gameMoney);
    }


    private double orgSceneX;
    private double orgSceneY;


    private ImagePattern enemyImagePattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
    );

    private ImagePattern moonImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/moon.png"))
    );

    private TowerType towerSelected;

    public TowerType getTowerSelected() {
        return towerSelected;
    }

    public void setTowerSelected(TowerType towerSelected) {
        this.towerSelected = towerSelected;
    }

    //    private HashMap<TowerType, TowerData> towerDataMap = new HashMap<>() {{
    //        put(
    //                TowerType.TYPE_A,
    //                new TowerData(
    //                        TowerType.TYPE_A,
    //                        10,
    //                        50,
    //                        Color.RED,
    //                        //Rectangle(20, 20, Color.ORANGERED),
    //                        "Basic Tower",
    //                        "Tower with price of 50 and damage of 10"
    //                )
    //        );
    //        put(
    //                TowerType.TYPE_B,
    //                new TowerData(
    //                        TowerType.TYPE_B,
    //                        15,
    //                        75,
    //                        Color.BLUE,
    //                        " Fortified Tower",
    //                        "Tower with price of 100 and damage of 15"
    //                )
    //        );
    //        put(
    //                TowerType.TYPE_C,
    //                new TowerData(
    //                        TowerType.TYPE_C,
    //                        20,
    //                        100,
    //                        Color.YELLOW,
    //                        "Enchanted Tower",
    //                        "Tower with price of 100 and damage of 20"
    //                )
    //        );
    //        put(
    //                TowerType.TYPE_D,
    //                new TowerData(
    //                        TowerType.TYPE_D,
    //                        25,
    //                        125,
    //                        Color.RED,
    //                        "Best Tower",
    //                        "Tower with price of 125 and damage of 25"
    //                )
    //        );
    //    }};
    //
    //    public HashMap<TowerType, TowerData> getTowerDataMap() {
    //        return towerDataMap;
    //    }



    public GameSceneWrapper(
            Stage stage,
            AnchorPane root,
            Scene scene
    ) {
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        System.out.println(SceneWrapper.getGameLevel().toString());
        setGameMoney((int) getGameMoneyMap().get(SceneWrapper.getGameLevel()));

        startStackPane = new StackPane();
        Text startIntroText = new Text(
                "SPACE: shoot\n"
                        + "ENTER: place tower\n"
                        + "ESC: STOP GAME\n"
        );
        startIntroText.setFont(Font.font("Verdana", 20));
        startIntroText.setFill(Color.GHOSTWHITE);
        Button startButton = new Button("Start Game");
        startButton.setOnMouseClicked(event -> {
            initGame();
        });
        startButton.getStyleClass().setAll("btn", "btn-default");
        startButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent;");
        startStackPane.getChildren().addAll(
                startIntroText,
                startButton
        );
        StackPane.setAlignment(startButton, Pos.CENTER);
        startStackPane.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7),
                                CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        startButton.setTranslateY(100);

        System.out.println(startStackPane);
        System.out.println(this.root);
        this.root.getChildren().add(startStackPane);
        this.root.setTopAnchor(startStackPane, 0.0);
        this.root.setLeftAnchor(startStackPane, 0.0);
        this.root.setBottomAnchor(startStackPane, 0.0);
        this.root.setRightAnchor(startStackPane, 0.0);
    }


    private void initGame() {
        root.getChildren().remove(startStackPane);

        Rectangle monumentRect = new Rectangle(60, 60);
        monumentRect.setFill(moonImgPattern);
        monument = new Monument(monumentRect);
        monument.get().relocate(Config.STAGE_WIDTH - 120, 40);
        root.getChildren().addAll(player, monument.get());

        root.getChildren().add(gameStatusStackPane);
        root.setBottomAnchor(gameStatusStackPane, 18.0);
        root.setRightAnchor(gameStatusStackPane, 18.0);

        //setGameMoney((int) getGameMoneyMap().get(getGameLevel()));
        //getGameLevel();
        //final int startingMoney = (int) getGameMoneyMap().get(getGameLevel());
        //setGameMoney(startingMoney);

        gameMoneyText.setFill(Color.GHOSTWHITE);
        gameMoneyText.setFont(Font.font(20));
        monumentHealthText.setFill(Color.GHOSTWHITE);
        monumentHealthText.setFont(Font.font(20));
        gameStatusStackPane.getChildren().addAll(
                gameMoneyText,
                monumentHealthText,
                gameLevelText,
                towerMenuText,
                towerType1,
                towerType2,
                towerType3
        );
        gameMoneyText.setTranslateY(-28);
        gameLevelText.setFill(Color.GHOSTWHITE);
        gameLevelText.setFont(Font.font(20));
        //gameLevelText.setText("Game Level: " + getGameLevel().toString());
        gameLevelText.setTranslateY(-10);

        //gameLevelText.setText("Game Level: " + getGameLevel().toString());
        gameLevelText.setTranslateY(-10);
        towerMenuText.setFill(Color.GHOSTWHITE);
        towerMenuText.setFont(Font.font(20));
        towerMenuText.setTranslateX(-800);
        towerMenuText.setTranslateY(-100);

        towerType1.addEventFilter(KeyEvent.ANY, Event::consume);
        towerType1.setFocusTraversable(false); //prevents keys from iterating thru buttons

        towerType1.setTextFill(Color.RED);
        towerType1.setTranslateY(-75);
        towerType1.setTranslateX(-800);
        towerType1.setOnMouseClicked(event -> {
            setTowerSelected(TowerType.TYPE_A);
        });

        towerType2.addEventFilter(KeyEvent.ANY, Event::consume);
        towerType2.setFocusTraversable(false);
        towerType2.setTextFill(Color.RED);
        towerType2.setTranslateY(-50);
        towerType2.setTranslateX(-800);
        towerType2.setOnMouseClicked(event -> {
            setTowerSelected(TowerType.TYPE_B);
        });

        towerType3.addEventFilter(KeyEvent.ANY, Event::consume);
        towerType3.setFocusTraversable(false);


        towerType3.setOnMouseClicked(event -> {
            setTowerSelected(TowerType.TYPE_C);
        });
        towerType3.setTextFill(Color.RED);
        towerType3.setTranslateY(-25);
        towerType3.setTranslateX(-800);


        controls();
        loop();
    }

    private StackPane gameStatusStackPane = new StackPane();
    private Text gameMoneyText = new Text("$: ");
    private Text monumentHealthText = new Text("Health: ");
    private Text gameLevelText = new Text("");

    private Text towerMenuText = new Text("Tower Menu:");
    private Button towerType1 = new Button("Tower 1 " + "Cost: " + 50);
    //+ getTowerDataMap().get(TowerType.TYPE_A).getPrice());
    private Button towerType2 = new Button("Tower 2 " + "Cost: " + 75);
    //+ getTowerDataMap().get(TowerType.TYPE_B).getPrice());
    private Button towerType3 = new Button("Tower 3 " + "Cost: " + 100);
    //+ getTowerDataMap().get(TowerType.TYPE_C).getPrice());

    private boolean checkIntersects(Node a, Node b) {
        return a.getBoundsInParent().intersects(b.getBoundsInParent());
    }

    private void controls() {

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
            case LEFT:
                goLeft = true;
                break;
            case RIGHT:
                goRight = true;
                break;
            case UP:
                goUp = true;
                break;
            case DOWN:
                goDown = true;
                break;
            case M:
                showMenu = !showMenu;
                if (showMenu) {
                    this.towerMenuComponent.setVisible(true);
                } else {
                    this.towerMenuComponent.setVisible(false);
                }
                break;
            case SPACE:
                if (!isShooting) {
                    playerProjectile = new Projectile(
                            new Rectangle(5, 5, Color.YELLOW));
                    if (getTowerSelected() == TowerType.TYPE_B) {
                        playerProjectile = new Projectile(
                                new Rectangle(5, 5, Color.ORANGE));
                    } else if (getTowerSelected() == TowerType.TYPE_C) {
                        playerProjectile = new Projectile(
                                new Rectangle(5, 5, Color.RED));
                    }

                    projectiles.add(playerProjectile);
                    playerProjectile.get().relocate(x + player.getRadius(), y);
                    root.getChildren().add(playerProjectile.get());
                    isShooting = true;
                    break;
                }
            case ENTER:
                onEnter();
                break;
            case ESCAPE:
                isStopped = !isStopped;
                break;
            }

        });
        scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();

            switch (key) {
            case LEFT:
                goLeft = false;
                break;
            case RIGHT:
                goRight = false;
                break;
            case UP:
                goUp = false;
                break;
            case DOWN:
                goDown = false;
                break;
            case SPACE:
                isShooting = false;
                break;
            }

        });
    }

    private void shoot() {
        for (int p = 0; p < projectiles.size(); p++) {
            Shape entity = projectiles.get(p).get();
            entity.relocate(
                    entity.getLayoutX() + projectiles.get(p).getDx(),
                    (entity.getLayoutY() + projectiles.get(p).getDy())
            );
            projectiles.get(p).setRange(
                    projectiles.get(p).getRange() + projectiles.get(p).getDy());
            System.out.println(projectiles.get(p).getRange());


            Iterator<Projectile> iterator = projectiles.iterator();
            Projectile temp;
            while (iterator.hasNext()) {
                temp = iterator.next();
                if ((temp.get().getLayoutY() < root.getLayoutY())
                        || (temp.getRange() <= 0)) {
                    iterator.remove();
                    root.getChildren().remove(temp.get());
                }
            }

        }
    }

    private void spawnEnemy() {
        double spawnPosition = Math.random();

        int eBaseWidth = 40;
        int eBaseHeight = 40;
        int eBaseHealth = 100;
        int eBaseDamage = 10;
        double ex = (int) (root.getLayoutX());
        int ey = (int) ((100 - eBaseHeight) * spawnPosition);

        if (counter % spawnTime == 0) {
            int min = 1;
            int max = 5;
            int enemyLevel = min + (int) (Math.random() * ((max - min) + 1));
            enemy = new Enemy(eBaseWidth, eBaseHeight, enemyLevel, eBaseHealth, eBaseDamage);
            enemy.getStackPane().relocate(ex, ey);
            enemies.add(enemy);
            root.getChildren().add(enemy.getStackPane());
        }

    }

    public void moveEnemy(int delta) {
        for (Enemy enemy : enemies) {
            StackPane entity = enemy.getStackPane();
            entity.setTranslateX(entity.getTranslateX() + delta);
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            enemy = iterator.next();
            if (enemy.get().getLayoutX() < root.getLayoutX()) {
                iterator.remove();
                root.getChildren().remove(enemy.get());
            }
        }
    }

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    private AnimationTimer timer;

    private void loop() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loopHandler();
            }
        };
        timer.start();
    }

    public void loopHandler() {
        if (isStopped) {
            return;
        }
        if (goLeft) {
            dx = -5;
        }
        if (goRight) {
            dx = 5;
        }
        if (goUp) {
            dy = -5;
        }
        if (goDown) {
            dy = 5;
        }
        if (!goLeft && !goRight) {
            dx = 0;
        }
        if (!goUp && !goDown) {
            dy = 0;
        }
        //int xi = x;
        //int yi = y;
        //player.relocate(x += dx, y += dy);
        player.relocate(x + dx, y + dy);
        x += dx;
        y += dy;
        if (!isAvailableToMove()) {
            player.relocate(x - dx, y - dy);
        }
        //if (isAvailableToMove()) {

        //} else {
        //    player.relocate(xi, yi);
        //}
        shoot();
        counter++;
        spawnEnemy();
        moveEnemy(enemySpeed);
        triggerTowerShot();
        handleGameMoney();
        checkHit();
    }

    private void handleGameMoney() {
        if (counter % gameMoneyIncrementSpeed == 0) {
            setGameMoney(getGameMoney() + moneyIncrementAmount);
        }
    }

    private boolean isAvailableToMove() {
        for (Tower wrapper : towers) {
            if (checkIntersects(wrapper.get(), player)) {
                return false;
            }
        }
        return true;
    }

    public void triggerTowerShot() {
        if (counter % 50 == 0) {
            for (Tower tower : towers) {
                Projectile towerProj = tower.getProjectile();
                projectiles.add(towerProj);
                towerProj.get().relocate(tower.get().getLayoutX(), tower.get().getLayoutY());
                root.getChildren().add(towerProj.get());
            }
        }
    }

    private void checkHit() {
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        Projectile p;
        Enemy e;
        // enemy hit
        while (projectileIterator.hasNext()) {
            p = projectileIterator.next();
            enemyIterator = enemies.iterator();

            while (enemyIterator.hasNext()) {
                e = enemyIterator.next();
                if (checkIntersects(p.get(), e.getStackPane())) {
                    int hpf = e.applyDamage(p.getDamage());
                    root.getChildren().remove(p.get());
                    if (hpf <= 0) {
                        root.getChildren().remove(e.getStackPane());
                        enemyIterator.remove();
                    }
                    projectileIterator.remove();
                    return;
                }
            }
        }

        // monument hit
        enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            e = enemyIterator.next();
            if (checkIntersects(monument.get(), e.getStackPane())) {
                int hpf = monument.applyDamage(e.getDamage());
                System.out.println(hpf);
                root.getChildren().remove(e.getStackPane());
                enemyIterator.remove();
                if (hpf <= 0) {
                    handleGameOver();
                } else {
                    modalToast(root, "Damage " + e.getDamage());
                }
                setMonumentHealth(hpf);
            }
        }
    }

    public void onEnter() {
        boolean isIntersect = false;
        boolean isOnPath = false;

        if (y <= pathHeight) {
            modalToast(root, "cannot place the tower");
            return;
        }
        Rectangle rectangle = new Rectangle(10, 10, Color.YELLOW);
        if (getTowerSelected() == TowerType.TYPE_B) {
            rectangle = new Rectangle(10, 10, Color.ORANGE);
        } else if (getTowerSelected() == TowerType.TYPE_C) {
            rectangle = new Rectangle(10, 10, Color.RED);
        }

        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            Rectangle c = (Rectangle) (t.getSource());
            c.toFront();
        });
        rectangle.setOnMouseDragged((t) -> {
            if (t.getSceneY() < 100) {
                //isOnPath = true;
                modalToast(root, "Cannot place the tower on the path");
                return;
            } //else {
            //    isOnPath = false;

            //}
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            if (orgSceneY <= pathHeight) {
                modalToast(root, "cannot place the tower");
            } else {
                Rectangle c = (Rectangle) (t.getSource());
                c.setLayoutX(c.getLayoutX() + offsetX);
                c.setLayoutY(c.getLayoutY() + offsetY);
            }
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });

        Tower tower = new Tower(rectangle, getTowerSelected(),
                Tower.getTowerAttributes().get(getTowerSelected())[0],
                Tower.getTowerAttributes().get(getTowerSelected())[1]);
        root.getChildren().add(tower.get());
        tower.get().relocate(x, y);
        for (Tower wrapper : towers) {
            if (checkIntersects(tower.get(), wrapper.get())) {
                isIntersect = true;
                break;
            }
        }

        if (isIntersect || isOnPath) {
            root.getChildren().remove(tower.get());
            modalToast(root, "cannot place the tower");
        } else {
            if (getGameMoney() - tower.getPrice() < 0) {
                root.getChildren().remove(tower.get());
                modalToast(root, "not enough money");
            } else {
                towers.add(tower);
                setGameMoney(getGameMoney() - tower.getPrice());
            }
        }
    }

    public void handleGameOver() {
        isStopped = true;

        StackPane gameOverStackPane = new StackPane();
        gameOverStackPane.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7),
                                CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        Text gameOverText = new Text(
                "Game Over\n"
        );
        gameOverText.setFont(Font.font("Verdana", 20));
        gameOverText.setFill(Color.GHOSTWHITE);
        gameOverText.setTranslateY(-30);

        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        exitButton.getStyleClass().setAll("btn", "btn-default");
        exitButton.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: white;");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMaxWidth(280);

        Button newGameButton = new Button("New Game");
        exitButton.getStyleClass().setAll("btn", "btn-default");
        newGameButton.setStyle("-fx-text-fill: white; "
                + "-fx-background-color: transparent; -fx-border-color: white;");
        newGameButton.setAlignment(Pos.CENTER);
        newGameButton.setMaxWidth(280);
        newGameButton.setTranslateY(40);
        newGameButton.setOnMouseClicked(mouseEvent -> {
            initWelcomeScene(stage, root);
        });
        gameOverStackPane.getChildren().addAll(
                gameOverText,
                exitButton,
                newGameButton
        );
        root.getChildren().add(gameOverStackPane);
        root.setTopAnchor(gameOverStackPane, 0.0);
        root.setLeftAnchor(gameOverStackPane, 0.0);
        root.setBottomAnchor(gameOverStackPane, 0.0);
        root.setRightAnchor(gameOverStackPane, 0.0);
    }
}