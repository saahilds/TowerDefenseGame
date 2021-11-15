package com.test;

import com.main.MainApplication;
import com.main.game.DataController;
import com.main.game.data.GameSettingDataMap;
import com.main.model.GameScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WelcomeScreenTest extends ApplicationTest {
    private MainApplication app;
    private DataController dataController;
    private Pane mainNode;
    private Stage mainstage;

    @Override
    public void start(Stage stage) throws Exception {
        app = new MainApplication();
        mainNode = (Pane) FXMLLoader.load(
                MainApplication.class.getResource(
                        GameSettingDataMap.getFileName(GameScreenType.WELCOME_SCREEN)
                )
        );
        mainstage = stage;
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testWelcomeScreenInitialization() {
        assertNotNull(mainNode);
    }
    
    //    @Test
    //    public void testWelcomText() {
    //        Label welcomeLabel = from(mainNode).lookup("#welcomeLabel").query();
    //        assertEquals(welcomeLabel.getText(), "Tower Defense Game");
    //    }
    //
    //    @Test
    //    public void testConfigurationScreenButtonClickable() {
    //        Button goToInitialScreenBtn = from(mainNode).lookup("#goToInitialScreenBtn").query();
    //        clickOn(goToInitialScreenBtn);
    //    }
    //
    //    @Test
    //    public void testConfigurationScreenButtonText() {
    //        Button goToInitialScreenBtn = from(mainNode).lookup("#goToInitialScreenBtn").query();
    //        assertEquals(goToInitialScreenBtn.getText(), "Start");
    //    }
}
