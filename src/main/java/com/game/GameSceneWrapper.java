package com.game;

//import com.game.components.gameScene.TowerData;

import com.game.components.gameScene.TowerData;
import com.game.config.Config;
import com.game.components.gameScene.TowerMenuComponent;
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
import java.util.Iterator;
import java.util.function.Function;

import static com.game.GameSettingDataMap.getStartingMonumentHealth;

public class GameSceneWrapper extends SceneWrapper {

    private int x = 150;
    private int y = 470;
    private int projectileSpeed = 10;

    private final int FPS = 60;
    private final int BOSS_SPAWN_SEC = 10;

    public int getGameMoneyIncrementSpeed() {
        return gameMoneyIncrementSpeed;
    }

    private int gameMoneyIncrementSpeed = 60;

    public int getMoneyIncrementAmount() {
        return moneyIncrementAmount;
    }

    public void setMoneyIncrementAmount(int moneyIncrementAmount) {
        this.moneyIncrementAmount = moneyIncrementAmount;
    }

    private int moneyIncrementAmount = 5;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    private int counter = 0;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        this.gameSecondsText.setText("Time: " + seconds);
    }

    private int seconds = 0;
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
    private Circle player = new Circle(x, y, 10, Color.RED);
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
        if (gameMoney < this.gameMoney) {
            setTotalGameMoneySpent(getTotalGameMoneySpent() + gameMoney);
        } else {
            setTotalGameMoneyGain(getTotalGameMoneyGain() + gameMoney);
        }
        this.gameMoney = gameMoney;
        gameMoneyText.setText("$: " + gameMoney);
    }

    private double orgSceneX;
    private double orgSceneY;

    private ImagePattern moonImgPattern = new ImagePattern(
            new Image(getClass().getResourceAsStream("/com/game/egg_03.gif"))
    );

    private VBox gameStatusVbox = new VBox();
    private Rectangle path;
    private Text gameMoneyText = new Text("$: ");
    private Text monumentHealthText = new Text("Health: ");
    private Text gameLevelText = new Text("");
    private Text gameSecondsText = new Text("Time: ");
    private AnchorPane towerUpgradeMenu;
    private Text towerUpgradeMenuText = new Text("Time: ");

    private int totalDamage = 0;
    private int totalGameMoneyGain = 0;
    private int totalGameMoneySpent = 0;

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getTotalGameMoneyGain() {
        return totalGameMoneyGain;
    }

    public void setTotalGameMoneyGain(int totalGameMoneyGain) {
        this.totalGameMoneyGain = totalGameMoneyGain;
    }

    public int getTotalGameMoneySpent() {
        return totalGameMoneySpent;
    }

    public void setTotalGameMoneySpent(int totalGameMoneySpent) {
        this.totalGameMoneySpent = totalGameMoneySpent;
    }


    public boolean isTowerUpgradeMenuVisible() {
        return isTowerUpgradeMenuVisible;
    }

    public void setTowerUpgradeMenuVisible(boolean towerUpgradeMenuVisible) {
        isTowerUpgradeMenuVisible = towerUpgradeMenuVisible;
        if (towerUpgradeMenu != null) {
            towerUpgradeMenu.setVisible(towerUpgradeMenuVisible);
        }
    }

    private boolean isTowerUpgradeMenuVisible = false;

    public Tower getUpgradeSelectedTower() {
        return upgradeSelectedTower;
    }

    public void setUpgradeSelectedTower(Tower upgradeSelectedTower) {
        this.upgradeSelectedTower = upgradeSelectedTower;
    }

    private Tower upgradeSelectedTower;

    public TowerData getPurchaseSelectedTowerData() {
        return towerMenuComponent.getSelectedTowerData();
    }

    public GameSceneWrapper(
            Stage stage,
            AnchorPane root,
            Scene scene
    ) {
        /**
         * set local variable
         */
        this.stage = stage;
        this.root = root;
        this.scene = scene;
        /**
         * init config Data: game Money and Monument health
         */
        setGameMoney((int) getGameMoneyMap().get(SceneWrapper.getGameLevel()));
        setMonumentHealth(getStartingMonumentHealth(SceneWrapper.getGameLevel()));
        /**
         * init tutorial screen
         */
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

        root.getChildren().add(startStackPane);
        root.setTopAnchor(startStackPane, 0.0);
        root.setLeftAnchor(startStackPane, 0.0);
        root.setBottomAnchor(startStackPane, 0.0);
        root.setRightAnchor(startStackPane, 0.0);

        // FIXME: 2021/11/27
        initGame();
    }

    /**
     * when the GAME START button pressed
     */
    private void initGame() {
        // remove tutorial(intro) screen
        root.getChildren().remove(startStackPane);

        Rectangle monumentRect = new Rectangle(100, 100);
        monumentRect.setFill(moonImgPattern);
        monumentRect.setStyle("-fx-background-position: bottom;");
        monument = new Monument(monumentRect);
        monument.get().relocate(Config.MONUMENT_SPAWN_X, Config.MONUMENT_SPAWN_Y);
        root.getChildren().addAll(player, monument.get());

        root.getChildren().add(gameStatusVbox);
        root.setTopAnchor(gameStatusVbox, 18.0);
        root.setRightAnchor(gameStatusVbox, 18.0);

        setGameMoney((int) getGameMoneyMap().get(getGameLevel()));
        getGameLevel();
        final int startingMoney = (int) getGameMoneyMap().get(getGameLevel());
        setGameMoney(startingMoney);

        gameMoneyText.setFill(Color.GHOSTWHITE);
        gameMoneyText.setFont(Font.font(20));
        monumentHealthText.setFill(Color.GHOSTWHITE);
        monumentHealthText.setFont(Font.font(20));
        gameStatusVbox.getChildren().addAll(
                gameLevelText,
                gameSecondsText,
                gameMoneyText,
                monumentHealthText
        );
        gameStatusVbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4); -fx-padding: 12;");
        gameLevelText.setFill(Color.GHOSTWHITE);
        gameLevelText.setFont(Font.font(20));
        gameLevelText.setText("Game Level: " + getGameLevel().toString());
        gameSecondsText.setFill(Color.GHOSTWHITE);
        gameSecondsText.setFont(Font.font(20));

        // tower setting
        Function<String, Void> func = (String string) -> {
            this.modalToastWrapper(string);
            return null;
        };
        towerMenuComponent = new TowerMenuComponent(getGameLevel(), this.root, func);
        towerMenuComponent.addEventFilter(KeyEvent.ANY, Event::consume);
        towerMenuComponent.setFocusTraversable(false);
        root.getChildren().add(towerMenuComponent);
        root.setLeftAnchor(towerMenuComponent, 0.0);
        root.setTopAnchor(towerMenuComponent, 0.0);

        // path setting
        path = new Rectangle(Config.STAGE_WIDTH, Config.PATH_HEIGHT);
        path.setFill(Color.TRANSPARENT);
        path.setFocusTraversable(false);
        root.getChildren().add(path);
        root.setLeftAnchor(path, 0.0);
        root.setBottomAnchor(path, 0.0);
        root.setRightAnchor(path, 0.0);

        player.setFocusTraversable(true);

        towerUpgradeMenu = new AnchorPane();
        towerUpgradeMenuText.setStyle("-fx-line-spacing: 6;");
        VBox towerUpgradeMenuContent = new VBox();
        HBox towerUpgradeMenuButtonRow = new HBox();
        // confirm button
        Button towerUpgradeConfirmButton = new Button("Yes!");
        towerUpgradeConfirmButton.setPrefWidth(52);
        towerUpgradeConfirmButton.setPrefHeight(18);
        towerUpgradeConfirmButton.setStyle("-fx-text-fill: #3a3a3a; "
                + "-fx-background-color: transparent; -fx-border-color: #3a3a3a; -fx-border-radius: 4;");
        towerUpgradeConfirmButton.setOnMouseClicked(e -> {
            onClickConfirmUpgrade();
        });
        // close button
        Button towerUpgradeCloseButton = new Button("No");
        towerUpgradeCloseButton.setPrefWidth(52);
        towerUpgradeCloseButton.setPrefHeight(18);
        towerUpgradeCloseButton.setStyle("-fx-text-fill: #3a3a3a; "
                + "-fx-background-color: transparent; -fx-border-color: #3a3a3a; -fx-border-radius: 4;");
        towerUpgradeCloseButton.setOnMouseClicked(e -> {
            setTowerUpgradeMenuVisible(false);
        });

        Rectangle bg = new Rectangle(160, 120);
        bg.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/com/game/bubble_01.png"))));

        towerUpgradeMenuButtonRow.getChildren().addAll(towerUpgradeConfirmButton, towerUpgradeCloseButton);

        towerUpgradeMenuContent.getChildren().addAll(towerUpgradeMenuText, towerUpgradeMenuButtonRow);
        towerUpgradeMenu.getChildren().addAll(bg, towerUpgradeMenuContent);
        Util.setAllAnchorD(towerUpgradeMenu, bg, 0.0);
        Util.setAllAnchorD(towerUpgradeMenu, towerUpgradeMenuContent, 24.0);
        root.getChildren().add(towerUpgradeMenu);
        setTowerUpgradeMenuVisible(false);

        initKeyController();
        initFrameLoop();
    }

    public void modalToastWrapper(String string) {
        modalToast(root, string);
    }

    private void initKeyController() {
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
                                new Rectangle(5, 5, Color.GHOSTWHITE)
                        );
                        projectiles.add(playerProjectile);
                        playerProjectile.get().relocate(x + player.getRadius(), y);
                        root.getChildren().add(playerProjectile.get());
                        isShooting = true;
                        break;
                    }
                case ENTER:
                    onPressEnter();
                    break;
                case ESCAPE:
                    isStopped = !isStopped;
                    break;
                default:
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
                default:
                    break;
            }
        });
    }

    private void handlePlayerShoot() {
        for (int p = 0; p < projectiles.size(); p++) {
            Shape entity = projectiles.get(p).get();
            entity.relocate(
                    entity.getLayoutX() + projectiles.get(p).getDx(),
                    (entity.getLayoutY() + projectiles.get(p).getDy())
            );
            projectiles.get(p).setRange(
                    projectiles.get(p).getRange() + projectiles.get(p).getDy());

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
//        int ey = (int) (Config.ENEMY_SPAWN_Y * spawnPosition);
        int ey = (int) (path.getBoundsInParent().getMinY() + path.getBoundsInParent().getMaxY()) / 2;

        if (counter % Config.BOSS_SPAWN_FRAME == 0) {
            modalToast(root, "Boss is spawned!");
            int enemyLevel = 10;
            boolean isBoss = true;
            enemy = new Enemy(eBaseWidth, eBaseHeight, enemyLevel, eBaseHealth, eBaseDamage, isBoss);
        } else if (counter % spawnTime == 0) {
            int min = 1;
            int max = 5;
            int enemyLevel = min + (int) (Math.random() * ((max - min) + 1));
            boolean isBoss = false;
            enemy = new Enemy(eBaseWidth, eBaseHeight, enemyLevel, eBaseHealth, eBaseDamage, isBoss);
        } else {
            return;
        }
        enemy.getStackPane().relocate(ex, ey);
        enemies.add(enemy);
        root.getChildren().add(enemy.getStackPane());
        StackPane wrapper = enemy.getStackPane();
        wrapper.setTranslateY(wrapper.getTranslateY() - enemy.getShape().getHeight());
    }

    public void moveEnemy(int delta) {
        for (Enemy enemy : enemies) {
            StackPane entity = enemy.getStackPane();
            entity.setTranslateX(entity.getTranslateX() + enemy.getDx());
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

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    private AnimationTimer timer;

    private void initFrameLoop() {
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
        counter++;
        if (counter % 60 == 0) {
            setSeconds(getSeconds() + 1);
        }
        handlePlayerMove();
        handlePlayerShoot();
        spawnEnemy();
        moveEnemy(enemySpeed);
        triggerTowerShot();
        handleGameMoney();
        handleProjectileHit();
    }

    private void handlePlayerMove() {
        int dx = 0;
        int dy = 0;
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
        int xf = x + dx;
        int yf = y + dy;
        if (isIntersectingWithObjects(xf, yf)) {
            modalToast(root, "Cannot move");
        } else {
            player.relocate(xf, yf);
            x = xf;
            y = yf;
        }
    }

    private void handleGameMoney() {
        if (counter % gameMoneyIncrementSpeed == 0) {
            setTotalGameMoneyGain(getTotalGameMoneyGain() + moneyIncrementAmount);
            setGameMoney(getGameMoney() + moneyIncrementAmount);
        }
    }

    private boolean isIntersectingWithObjects(Node target) {
        if (Util.isIntersecting(target, path)) {
            return true;
        }
        for (Tower wrapper : towers) {
            if (Util.isIntersecting(wrapper.get(), target)) {
                return true;
            }
        }
        return false;
    }

    private boolean isIntersectingWithObjects(int x, int y) {
        if (Util.isIntersecting(x, y, path)) {
            return true;
        }
        for (Tower wrapper : towers) {
            if (Util.isIntersecting(x, y, wrapper.get())) {
                return true;
            }
        }
        return false;
    }

    public void triggerTowerShot() {
        if (counter % 50 == 0) {
            for (Tower tower : towers) {
                ArrayList<Projectile> towerProjList = tower.getProjectileArrayList();
                for (Projectile proj : towerProjList) {
                    projectiles.add(proj);
                    proj.get().relocate(tower.get().getLayoutX(), tower.get().getLayoutY());
                    root.getChildren().add(proj.get());
                }
            }
        }
    }

    private void handleProjectileHit() {
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        Projectile p;
        Enemy e;
        // enemy hit
        while (projectileIterator.hasNext()) {
            p = projectileIterator.next();
            enemyIterator = enemies.iterator();
            if (p.get().getLayoutY() > Config.STAGE_HEIGHT) {
                root.getChildren().remove(p.get());
                projectileIterator.remove();
                System.out.println("REMOVE");
                continue;
            }
            while (enemyIterator.hasNext()) {
                e = enemyIterator.next();
                if (Util.isIntersecting(p.get(), e.getStackPane())) {
                    int dmg = p.getDamage();
                    setTotalDamage(getTotalDamage() + dmg);
                    int hpf = e.applyDamage(dmg);
                    root.getChildren().remove(p.get());
                    if (hpf <= 0) {
                        root.getChildren().remove(e.getStackPane());
                        enemyIterator.remove();
                        if (e.isBoss()) {
                            handleStageClear();
                        }
                    }
                    projectileIterator.remove();
                }
            }
        }

        // monument hit
        enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            e = enemyIterator.next();
            if (Util.isIntersecting(monument.get(), e.getStackPane())) {
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

    public void onPressEnter() {
        TowerData towerData = towerMenuComponent.getSelectedTowerData();
        if (towerData == null) {
            modalToast(root, "No selected Tower");
            return;
        } else if (isIntersectingWithObjects(player)) {
            modalToast(root, "cannot place the tower");
            return;
        } else if (getGameMoney() < towerData.getPrice()) {
            modalToast(root, "Not enough money");
            return;
        }
        Rectangle rectangle = new Rectangle(40, 40, Color.YELLOW);
        Tower tower = new Tower(rectangle, towerData);
        rectangle.setFill(towerData.getImagePattern());
        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            Rectangle c = (Rectangle) (t.getSource());
            c.toFront();
            setTowerUpgradeMenuVisible(false);
        });
        rectangle.setOnMouseDragged((t) -> {
            if (isIntersectingWithObjects((int) t.getSceneX(), (int) t.getSceneY())) {
                modalToast(root, "Cannot place the tower on the path");
                return;
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
            setTowerUpgradeMenuVisible(false);
        });
        rectangle.setOnMouseClicked((t) -> {
            if (tower.getLevel() >= 3) {
                modalToast(root, "Cannot upgrade the tower anymore.");
                return;
            }
            setUpgradeSelectedTower(tower);
            double x = t.getSceneX();
            double y = t.getSceneY();
            double h = rectangle.getHeight();
            setTowerUpgradeMenuVisible(true);
            towerUpgradeMenu.setTranslateX(x);
            towerUpgradeMenu.setTranslateY(y - h - 60);
            towerUpgradeMenuText.setText(
                    tower.getTowerData().getName()
                            + " lv." + tower.getLevel()
                            + "\n COST: "
                            + tower.getTowerData().getUpgradePrice()
            );
        });
        root.getChildren().add(tower.get());
        tower.get().relocate(x, y);
        towers.add(tower);
        setGameMoney(getGameMoney() - tower.getTowerData().getPrice());
    }

    public void onClickConfirmUpgrade() {
        Tower tower = getUpgradeSelectedTower();
        if (tower == null) {
            System.out.println("err");
        } else if (getGameMoney() < tower.getTowerData().getUpgradePrice()) {
            modalToast(root, "Not enough money");
        } else {
            tower.requestLevelUp();
            setGameMoney(getGameMoney() - tower.getTowerData().getUpgradePrice());
            modalToast(root, tower.getTowerData().getName() + "is upgraded to level " + tower.getLevel());
        }
        setTowerUpgradeMenuVisible(false);
        return;
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
        Util.setAllAnchorD(root, gameOverStackPane, 0.0);
    }

    public void handleStageClear() {
        isStopped = true;

        StackPane stageClearStackPane = new StackPane();
        VBox contents = new VBox();
        contents.setSpacing(12);
        stageClearStackPane.setBackground(
                new Background(
                        new BackgroundFill(Color.rgb(50, 50, 50, 0.7),
                                CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        Text stageClearText = new Text(
                "Stage Clear!\n"
        );
        stageClearText.setFont(Font.font("Verdana", 20));
        stageClearText.setFill(Color.GHOSTWHITE);

        Text gameStatText = new Text(
                "Play Time: " + seconds + "seconds"
                        + "\nTotal Damage: " + getTotalDamage()
                        + "\nTotal Money Gain: " + getTotalGameMoneyGain()
                        + "\nTotal Money Spent: " + getTotalGameMoneySpent()
                        + "\nTotal Tower Built: " + towers.size()
        );
        gameStatText.setFont(Font.font("Verdana", 14));
        gameStatText.setStyle("-fx-line-spacing: 8; -fx-padding: 5px;");
        gameStatText.setFill(Color.GHOSTWHITE);

        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        exitButton.getStyleClass().setAll("btn", "btn-default");
        exitButton.setStyle("-fx-text-fill: white; -fx-padding: 5px;"
                + "-fx-background-color: transparent; -fx-border-color: white;");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMaxWidth(280);

        Button newGameButton = new Button("New Game");
        exitButton.getStyleClass().setAll("btn", "btn-default");
        newGameButton.setStyle("-fx-text-fill: white; -fx-padding: 5px;"
                + "-fx-background-color: transparent; -fx-border-color: white;");
        newGameButton.setAlignment(Pos.CENTER);
        newGameButton.setMaxWidth(280);
        newGameButton.setOnMouseClicked(mouseEvent -> {
            initWelcomeScene(stage, root);
        });
        contents.getChildren().addAll(
                stageClearText,
                gameStatText,
                exitButton,
                newGameButton
        );
        contents.setAlignment(Pos.CENTER);
        stageClearStackPane.getChildren().addAll(contents);
        root.getChildren().add(stageClearStackPane);
        Util.setAllAnchorD(root, stageClearStackPane, 0.0);
    }
}