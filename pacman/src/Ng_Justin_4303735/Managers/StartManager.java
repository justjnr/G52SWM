package Ng_Justin_4303735.Managers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartManager {
    /**
     * Constructor for this class which calls gameInit
     * @author Justin Ng
     *
     * @param group - group to be passed in
     * @param canvas group to be passed in
     */
    public StartManager(Group group, Canvas canvas){
        gameInit(group, canvas);
    }
    /**
     * Initialise the game scene
     * @author Justin Ng
     *
     * @param group - group to be passed in
     * @param canvas - group to be passed in
     */
    private void gameInit(Group group, Canvas canvas){
        group.getChildren().add(canvas);
    }

    /**
     * Initialises the view for the scene and sets it to the current stage
     * @author Justin Ng
     *
     * @param stage - current stage to be acted on
     * @param gameScene - game scene passed for event handling
     * @param group - group to be passed into the new instance of GameManager
     * @param settingsManager - instance of settingsManager to be passed into GameManager
     */
    public void initView(Stage stage, Scene gameScene, Group group, SettingsManager settingsManager){
        stage.setTitle("Pacman");
        stage.setScene(gameScene);
        stage.setHeight(800);
        stage.setWidth(1225);
        //stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        stage.setMaxWidth(stage.getWidth());
        stage.setMaxHeight(stage.getHeight());

        String c = settingsManager.getbgCSetting();
        Color colourSetting = Color.web(c);
        gameScene.setFill(colourSetting);

        GameManager gameManager = new GameManager(group, settingsManager);
        gameManager.drawBoard();

        initEventHandlers(gameScene, gameManager);
    }
    /**
     * Initialises event listener to detect key press events for the game
     * Binds methods from gameManager to the event handler
     * @author Justin Ng
     *
     * @param scene - scene to add event handler to
     * @param gameManager - instance of gameManager to use to invoke methods from that class
     */
    private void initEventHandlers(Scene scene, GameManager gameManager) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    }
}
