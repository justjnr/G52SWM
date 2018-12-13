package Ng_Justin_4303735.Managers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewManager {

    Font menuFont = Font.loadFont("file:resc/font/snnn.ttf", 16);
    Font titleFont = Font.loadFont("file:resc/font/snnn.ttf", 72);

    Group menuGroup = new Group();
    Scene menuScene = new Scene(menuGroup);

    Group gameGroup = new Group();
    Scene gameScene = new Scene(gameGroup);

    Group settingsGroup = new Group();
    Scene settingsScene = new Scene(settingsGroup);

    Group highscoreGroup = new Group();
    Scene highscoreScene = new Scene(highscoreGroup);

    Canvas canvas = new Canvas(1280, 720);
    SettingsManager settingsManager;
    StartManager startManager;
    HighscoreManager highscoreManager;

    public ViewManager(Stage stage){
        settingsInit(stage);
        highscoreInit(stage);
        startInit(stage);
        menuInit(stage);
    }
    /**
     * Initialise the menu scene
     */
    public void menuInit(Stage stage){
        new MenuManager(stage, menuGroup, menuScene, settingsScene, menuFont, canvas, settingsManager, startManager, gameScene, gameGroup, highscoreManager, highscoreScene);
        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    public void startInit(Stage stage){
        startManager = new StartManager(stage, gameScene, gameGroup, canvas, settingsManager);
    }

    public void settingsInit(Stage stage){
        settingsManager = new SettingsManager(stage, settingsScene, menuScene, settingsGroup, canvas, titleFont, menuFont);
    }

    public void highscoreInit(Stage stage){
        highscoreManager = new HighscoreManager(stage, highscoreScene, menuScene, highscoreGroup, canvas, titleFont, menuFont);
    }
}
