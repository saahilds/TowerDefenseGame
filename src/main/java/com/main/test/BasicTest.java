package com.main.test;
import com.main.MainApplication;
import com.main.game.DataController;
import com.main.model.GameLevelType;
//import com.main.InitialScreenController;
//import com.main.MainController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;

public class BasicTest {

    @Test
    public void testInitializeApp() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new MainApplication(); // Initializes the JavaFx Platform
            }
        });
        thread.start(); // Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

    @Test
    public void testDataControllerInitialValue() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication mainApplication = new MainApplication();
                DataController dataController = mainApplication.getDataController();
                assertEquals(dataController.getGameLevel(), null);
                assertEquals(dataController.getPlayerName(), null);
                assertEquals(dataController.getGameMoney(), null);
            }
        });
        thread.start(); // Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

    @Test
    public void testDataControllerInputValue() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication mainApplication = new MainApplication();
                DataController dataController = mainApplication.getDataController();

                dataController.setPlayerName(" "); //test invalid player name input
                assertEquals(dataController.isPlayerNameValid(dataController.getPlayerName()),
                        false);
                dataController.setPlayerName("James Bond"); //test valid player name input
                assertEquals(dataController.getPlayerName(), "James Bond");
                assertEquals(dataController.isPlayerNameValid(dataController.getPlayerName()),
                        true);

                assertEquals(dataController.isGameLevelValid(dataController.getGameLevel()),
                        false);
                dataController.setGameLevel(GameLevelType.EASY);
                assertEquals(dataController.getGameLevel(), GameLevelType.EASY);
                assertEquals(dataController.isGameLevelValid(dataController.getGameLevel()),
                        true);
            }
        });
        thread.start(); // Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

    @Test
    public void testInitialtoGameScreen() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication mainApplication = new MainApplication();
                DataController dataController = mainApplication.getDataController();

                assertEquals(dataController.isAbleToGoGameScreen(), false);
                dataController.setPlayerName("James Bond");
                dataController.setGameLevel(GameLevelType.EASY);
                assertEquals(dataController.isAbleToGoGameScreen(), true);

            }
        });
        thread.start(); // Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

    @Test
    public void testInitialtoGameScreenMove() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication mainApplication = new MainApplication();
                DataController dataController = mainApplication.getDataController();

                dataController.setPlayerName("James Bond"); //test valid player name input
                assertEquals(dataController.getPlayerName(), "James Bond");
                dataController.setGameLevel(GameLevelType.EASY);
                assertEquals(dataController.isAbleToGoGameScreen(), true);
            }
        });
        thread.start(); // Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }



}
