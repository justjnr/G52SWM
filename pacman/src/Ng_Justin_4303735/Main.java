package Ng_Justin_4303735;



import Ng_Justin_4303735.Managers.GameManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start method initialises and generates the Scene and also initialises the event handlers for key inputs to move the pacman
     *
     * @param theStage - The Stage to be used for the game
     */
    Group rootGroup = new Group();
    Group menuGroup = new Group();
    HBox buttonBox = new HBox();
    HBox imageBox = new HBox();
    Scene gameScene = new Scene(rootGroup);
    Scene menuScene = new Scene(menuGroup);
    GameManager gameManager = new GameManager(rootGroup);
    Canvas canvas = new Canvas(1280, 720);
    //Stage stage;
    Image menuImage = new Image("file:resc/img/menu.png",false);
    ImageView menuImageView = new ImageView(menuImage);
    Font menuFont = Font.loadFont("file:resc/font/snnn.ttf", 16);

    public Button start;
    public Button settings;
    public Button highScore;
    public Button exit;

    @Override
    public void start(Stage stage) throws Exception{
//      Parent rootGroup = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        menuInit(stage);
        //gameInit(theStage);
        //stage = theStage;
        initMenuEventHandlers(menuScene, stage);
        stage.sizeToScene();
        render(stage);
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    /**
     * Initialise the menu scene
     */
    public void menuInit(Stage stage){
        stage.setTitle("Pacman");
        stage.setScene(menuScene);

        menuScene.setFill(Color.BLACK);
        menuImageView.setFitHeight(720);
        menuImageView.setFitWidth(1280);
        menuImageView.setPreserveRatio(true);
        //menuScene.setFill(menuImageView));
        Insets insets = new Insets(0, 20, 0, 20);
        this.start = new Button("START");
        start.setTextFill(Color.WHITE);
        start.setFont(menuFont);
        start.setPadding(insets);
        start.setStyle("-fx-background-color: Transparent");

        this.settings = new Button("SETTINGS");
        settings.setTextFill(Color.WHITE);
        settings.setFont(menuFont);
        settings.setPadding(insets);
        settings.setStyle("-fx-background-color: Transparent");

        this.highScore = new Button("HIGH SCORES");
        highScore.setTextFill(Color.WHITE);
        highScore.setFont(menuFont);
        highScore.setPadding(insets);
        highScore.setStyle("-fx-background-color: Transparent");

        this.exit = new Button("EXIT");
        exit.setTextFill(Color.WHITE);
        exit.setFont(menuFont);
        exit.setPadding(insets);
        exit.setStyle("-fx-background-color: Transparent");

        buttonBox.getChildren().add(start);
        buttonBox.getChildren().add(settings);
        buttonBox.getChildren().add(highScore);
        buttonBox.getChildren().add(exit);
        buttonBox.setAlignment(Pos.CENTER);

        imageBox.getChildren().add(menuImageView);
        imageBox.setAlignment(Pos.CENTER);

        menuGroup.getChildren().add(canvas);
        menuGroup.getChildren().add(imageBox);
        menuGroup.getChildren().add(buttonBox);
    }

    /**
     * Initialise the game scene
     * @param stage - passes the stage in as a parameter
     */
    public void gameInit(Stage stage){
        stage.setTitle("Pacman");
        stage.setScene(gameScene);
        gameScene.setFill(Color.BLACK);
        rootGroup.getChildren().add(canvas);
        gameManager.drawBoard();
    }

    public void initGameEventHandlers(Scene scene, GameManager gameManager, Stage stage) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    }

    public void initMenuEventHandlers(Scene scene, Stage stage){
        /**
         * Resize event listener to detect when window is resized and sets text position relative to window
         */
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                buttonBox.setMinSize(menuScene.getWidth(), 0);
                imageBox.setPrefSize(menuScene.getWidth(), menuScene.getHeight());
            }
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                buttonBox.setLayoutY(menuScene.getHeight() * 0.9 - 150);
                imageBox.setPrefSize(menuScene.getWidth(), menuScene.getHeight());
            }
        });

        start.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(start, scene));
        start.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(start, scene));

        start.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            gameInit(stage);
            initGameEventHandlers(gameScene, gameManager, stage);
            stage.setHeight(800);
            stage.setWidth(1225);
            //stage.sizeToScene();
            render(stage);
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
            stage.setMaxWidth(stage.getWidth());
            stage.setMaxHeight(stage.getHeight());
        });

        exit.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(exit, scene));
        exit.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(exit, scene));

        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
        });

        settings.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(settings, scene));
        settings.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(settings, scene));

        highScore.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(highScore, scene));
        highScore.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(highScore, scene));
    }

    public void onMouseEnterButton(Button button, Scene scene){
        button.setScaleX(1.25);
        button.setScaleY(1.25);
        scene.setCursor(Cursor.HAND);
    }
    public void onMouseExitButton(Button button, Scene scene){
        button.setScaleX(1);
        button.setScaleY(1);
        scene.setCursor(Cursor.DEFAULT);
    }

    public void render(Stage stage){
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}