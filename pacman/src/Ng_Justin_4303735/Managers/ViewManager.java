package Ng_Justin_4303735.Managers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewManager {

    /**
     * Load custom fonts
     */
    Font menuFont = Font.loadFont("file:resc/font/snnn.ttf", 16);
    Font titleFont = Font.loadFont("file:resc/font/snnn.ttf", 72);

    /**
     * Create new groups and scenes for each manager
     */
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

    /**
     * Constructor to start initialising all scenes
     * @author Justin Ng
     *
     * @param stage - current stage to be passed through as a param
     */
    public ViewManager(Stage stage){
        settingsInit(stage);
        highscoreInit(stage);
        startInit();
        menuInit(stage);
    }
    /**
     * Initialise the menu scene first by creating a new instance of MenuManager and passing through all
     * other parameters necessary to go to and from the menu scene
     * @author Justin Ng
     *
     * @param stage - current stage to be passed through and acted on to show the stage
     */
    private void menuInit(Stage stage){
        new MenuManager(stage, menuGroup, menuScene, settingsScene, menuFont, canvas, settingsManager, startManager, gameScene, gameGroup, highscoreManager, highscoreScene);
        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    /**
     * Initialise the game by creating a new instance of StartManager and passing the gameGroup and
     * canvas to it to be acted on in that class
     * @author Justin Ng
     */
    private void startInit(){
        startManager = new StartManager(gameGroup, canvas);
    }

    /**
     * Initialise the settings scene by creating a new instance of SettingsManager
     * and passing through all the relevant scenes, fonts and groups to be used
     * in the SettingsManager class
     * @author Justin Ng
     *
     * @param stage - current stage to be passed through and acted on
     */
    private void settingsInit(Stage stage){
        settingsManager = new SettingsManager(stage, settingsScene, menuScene, settingsGroup, canvas, titleFont, menuFont);
    }

    /**
     * Initialise the high score scene by creating a new instance of HighscoreManager
     * and passing through the relevant scenes and fonts to be used in that class
     * @author Justin Ng
     *
     * @param stage - current stage to be passed through and acted on
     */
    private void highscoreInit(Stage stage){
        highscoreManager = new HighscoreManager(stage, highscoreScene, menuScene, highscoreGroup, canvas, titleFont, menuFont);
    }
}
