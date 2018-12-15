package Ng_Justin_4303735;

import Ng_Justin_4303735.Managers.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start method initialises and generates the Scene and also initialises the event handlers for key inputs to move the pacman
     *
     * @param stage - The Stage to be used for the game
     */
    @Override
    public void start(Stage stage) throws Exception{
//      Parent rootGroup = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        new ViewManager(stage);
        //gameInit(theStage);
        //stage = theStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}