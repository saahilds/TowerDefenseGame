package com.game;

import com.main.config.Config;
import com.game.components.gameScene.TowerMenuComponent;
import com.main.model.GameLevelType;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
import java.util.HashMap;
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

    public int getCounter() {
        return counter;
    }

    private int counter = 0, enemySpeed = 4;
    private boolean goLeft, goRight, goUp, goDown, isShooting;
    private int spawnTime = 180;
    private final int pathHeight = 100;

    private AnchorPane root;
    private Stage stage;
    private Scene scene;

    private Projectile playerProjectile;
    private Circle player = new Circle(x, y, 10, Color.GRAY);
    private Enemy enemy;

    private ArrayList<Projectile> projectiles = new ArrayList();
    private ArrayList<Tower> towers = new ArrayList();
    private ArrayList<Enemy> enemies = new ArrayList();

    private TowerMenuComponent towerMenuComponent;
    private Monument monument;

    private boolean isStopped = false, showMemu = false;

    StackPane startStackPane;

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


    double orgSceneX, orgSceneY;


    private ImagePattern enemyImagePattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/bird1.gif"))
    );

    private ImagePattern moonImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/moon.png"))
    );


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
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7), CornerRadii.EMPTY, Insets.EMPTY)
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
                gameLevelText
        );
        gameMoneyText.setTranslateY(-28);
        gameLevelText.setFill(Color.GHOSTWHITE);
        gameLevelText.setFont(Font.font(20));
        //gameLevelText.setText("Game Level: " + getGameLevel().toString());
        gameLevelText.setTranslateY(-10);
//        if (getGameLevel() == GameLevelType.EASY) {
//            setGameMoney(1000);
//        } else if (getGameLevel() == GameLevelType.NORMAL) {
//            setGameMoney(500);
//        } else if (getGameLevel() == GameLevelType.HARD){
//            setGameMoney(100);
//        }
//        setGameMoney( (int) getGameMoneyMap().get(getGameLevel()));
        controls();
        loop();
    }

    public StackPane gameStatusStackPane = new StackPane();
    public Text gameMoneyText = new Text("$: ");
    public Text monumentHealthText = new Text("Health: ");
    public Text gameLevelText = new Text("");

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
                    showMemu = !showMemu;
                    if (showMemu) {
                        this.towerMenuComponent.setVisible(true);
                    } else {
                        this.towerMenuComponent.setVisible(false);
                    }
                    break;
                case SPACE:
                    if (!isShooting) {
                        playerProjectile = new Projectile(
                                new Rectangle(5, 5, Color.ORANGERED)
                        );
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
        for (Projectile p : projectiles) {
            Shape entity = p.get();
            entity.relocate(
                    entity.getLayoutX() + p.getDx(),
                    (entity.getLayoutY() + p.getDy())
            );
        }

        Iterator<Projectile> iterator = projectiles.iterator();
        Projectile temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.get().getLayoutY() < root.getLayoutY()) {
                iterator.remove();
                root.getChildren().remove(temp.get());
            }
        }

    }

    @SuppressWarnings("checkstyle:WhitespaceAfter")
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

    private void loop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
                int xi = x, yi = y;
                player.relocate(x += dx, y += dy);
                if (isAvailableToMove()) {

                } else {
                    player.relocate(xi, yi);
                }
                shoot();
                counter++;
                spawnEnemy();
                moveEnemy(enemySpeed);
                triggerTowerShot();
                handleGameMoney();
                checkHit();
            }
        };
        timer.start();
    }

    private void handleGameMoney() {
        if (counter % gameMoneyIncrementSpeed == 0) {
            setGameMoney(getGameMoney() + 50);
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

    private void triggerTowerShot() {
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

        // monumnet hit
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

    private void onEnter() {
        boolean isIntersect = false;
        boolean isOnPath = false;

        if (y <= pathHeight) {
            modalToast(root, "cannot place the tower");
            return;
        }

        Rectangle rectangle = new Rectangle(10, 10, Color.ORANGERED);
        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            Rectangle c = (Rectangle) (t.getSource());
            c.toFront();
        });
        rectangle.setOnMouseDragged((t) -> {
            if (t.getSceneY() < 100) {
//                isOnPath = true;
                modalToast(root, "Cannot place the tower on the path");
                return;
            } else {
//                isOnPath = false;

            }
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

        Tower tower = new Tower(rectangle);
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
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7), CornerRadii.EMPTY, Insets.EMPTY)
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
        exitButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white;");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMaxWidth(280);

        Button newGameButton = new Button("New Game");
        exitButton.getStyleClass().setAll("btn", "btn-default");
        newGameButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white;");
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
