package Ng_Justin_4303735;



import Ng_Justin_4303735.Managers.GameManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start method initialises and generates the Scene and also initialises the event handlers for key inputs to move the pacman
     *
     * @param theStage - The Stage to be used for the game
     */
    Group root = new Group();
    Group menu = new Group();
    Scene gameScene = new Scene(root);
    Scene menuScene = new Scene(menu);
    GameManager gameManager = new GameManager(root);
    Canvas canvas = new Canvas(1225, 600 );

    @Override
    public void start(Stage theStage) throws Exception{
//      Parent root = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        init(theStage);
        initEventHandlers(gameScene, gameManager);
        render(theStage);
    }

    public void init(Stage stage){
        stage.setTitle("Pacman");
        stage.setScene(gameScene);
        gameScene.setFill(Color.BLACK);
        root.getChildren().add(canvas);
        gameManager.drawBoard();
    }

    public void initEventHandlers(Scene scene, GameManager gameManager){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    }

    public void render(Stage stage){
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}