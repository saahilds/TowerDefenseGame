package com.main;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Concentration 64
 */
public class InitialScreenController implements Initializable, ControlledScreen {

    @FXML
    private Text nameOut;

    @FXML
    private TextField nameIn;

    @FXML
    private Text errorText;

    @FXML
    private Text levelOut;

    private String level;

    private String name;
  //  private String level = levelOut.getText();

    private ScreensController screensController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void setScreenParent(ScreensController screenParent){
        screensController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event){
        screensController.setScreen(MainApplication.mainID);
    }

    @FXML
    private void goToGameScreen(ActionEvent event){
        screensController.setScreen(MainApplication.gameScreenID);
    }
    @FXML
    public void saveData(MouseEvent mouseEvent) {
        if (nameIn.getText() == null || Objects.equals(nameIn.getText(), "") || nameIn.getText().contains(" ")) {
            errorText.setText("Invalid Name Input");
            nameOut.setText("");
        } else {
            errorText.setText("");
            nameOut.setText("Welcome, " + nameIn.getText());
            name = nameIn.getText();
        }
    }

    @FXML
    void playEasy(MouseEvent event) {
        levelOut.setText("Level: Easy");
        level = "easy";
    }

    @FXML
    void playNormal(MouseEvent event) {
        levelOut.setText("Level: Normal");
        level = "normal";
    }

    @FXML
    void playHard(MouseEvent event) {
        levelOut.setText("Level: Hard");
        level = "hard";
    }


}