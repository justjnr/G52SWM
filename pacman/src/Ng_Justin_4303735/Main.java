package Ng_Justin_4303735;



import Ng_Justin_4303735.Managers.MenuManager;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start method initialises and generates the Scene and also initialises the event handlers for key inputs to move the pacman
     *
     * @param theStage - The Stage to be used for the game
     */
    //Stage stage;


    @Override
    public void start(Stage stage) throws Exception{
//      Parent rootGroup = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        menuInit(stage);
        //gameInit(theStage);
        //stage = theStage;
        stage.sizeToScene();
        render(stage);
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    /**
     * Initialise the menu scene
     */
    public void menuInit(Stage stage){
        new MenuManager(stage);
    }

    public void render(Stage stage){
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}