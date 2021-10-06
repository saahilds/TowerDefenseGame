package com.main.test;

import com.main.MainApplication;
import com.main.game.DataController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BasicTest {

    @Test
    public void testInitializeApp() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new MainApplication(); // Initializes the JavaFx Platform
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

    @Test
    public void testDataControllerInitialValue() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainApplication mainApplication = new MainApplication(); // Initializes the JavaFx Platform
                DataController dataController = mainApplication.getDataController();
                assertEquals(dataController.getGameLevel(), null);
                assertEquals(dataController.getPlayerName(), null);
                assertEquals(dataController.getGameMoney(), null);
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(1000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

}
