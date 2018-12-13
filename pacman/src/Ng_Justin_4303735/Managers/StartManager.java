package Ng_Justin_4303735.Managers;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartManager {

    /**
     * Constructor for this class
     * @author Justin Ng
     *
     * @param stage - passes the stage from MenuManager
     * @param scene - passes the scene created from MenuManager
     * @param group
     * @param canvas
     */
    public StartManager(Stage stage, Scene scene, Group group, Canvas canvas){
        GameManager gameManager = new GameManager(group);
        gameInit(stage, scene, group, canvas, gameManager);
        initEventHandlers(scene, gameManager, stage);
    }
    /**
     * Initialise the game scene
     * @author Justin Ng
     *
     * @param stage - passes the stage in as a parameter
     */
    public void gameInit(Stage stage, Scene scene, Group group, Canvas canvas, GameManager gameManager){
        stage.setTitle("Pacman");
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        stage.setHeight(800);
        stage.setWidth(1225);
        //stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        stage.setMaxWidth(stage.getWidth());
        stage.setMaxHeight(stage.getHeight());

        group.getChildren().add(canvas);
        gameManager.drawBoard();
    }

    /**
     * Initialises event listener to detect key press events
     * @author Justin Ng
     *
     * @param scene
     * @param stage
     */
    public void initEventHandlers(Scene scene, GameManager gameManager, Stage stage) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    }
}
