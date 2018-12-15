package Ng_Justin_4303735.Managers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuManager {

    private Button start;
    private Button settings;
    private Button highScore;
    private Button exit;

    private HBox buttonBox = new HBox();
    private HBox imageBox = new HBox();

    private Image menuImage = new Image("file:resc/img/menu.png",false);
    private ImageView menuImageView = new ImageView(menuImage);

    /**
     * Constructor that initialises the menu scene
     * @author Justin Ng
     *
     * @param stage - current active stage to be acted upon
     * @param menuGroup - menu group to add elements to
     * @param menuScene - menu scene to set stage
     * @param settingsScene - settings scene to be passed to initEventHandlers
     * @param menuFont - menu font to be used for ui elements
     * @param canvas - canvas to be added to menu group
     * @param settingsManager - settings manager to be passed to initEventHandlers
     * @param startManager - start manager to be passed to initEventHandlers
     * @param gameScene - game scene to be passed to initEventHandlers
     * @param gameGroup - game group to be passed to initEventHandlers
     * @param highscoreManager - highscore manager to be passed to initEventHandlers
     * @param highscoreScene - highscore scene to be passed to initEventHandlers
     */
    public MenuManager(Stage stage, Group menuGroup, Scene menuScene, Scene settingsScene, Font menuFont, Canvas canvas, SettingsManager settingsManager, StartManager startManager, Scene gameScene, Group gameGroup, HighscoreManager highscoreManager, Scene highscoreScene){
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

        initEventHandlers(menuScene, settingsScene, stage, settingsManager, startManager, gameScene, gameGroup, highscoreManager, highscoreScene);
    }

    /**
     * Initialises event listener to detect when window is resized and sets text position relative to window and detects button mouseover / mouseenter
     * @author Justin Ng
     *
     * @param scene - current scene to be used
     * @param settingsScene - sets scene to settings scene when button pressed
     * @param stage - current stage to be used
     * @param settingsManager - instance of settings manager to initialise view when button pressed
     * @param startManager - instance of start manager to intialise view when button pressed
     * @param gameScene - game scene to be passed through startManager
     * @param gameGroup - game group to be passed through gameManager
     * @param highscoreManager - instance of highscore manager to initialise view when button pressed
     * @param highscoreScene - highscore scene to be passed through highscoreManager
     */
    private void initEventHandlers(Scene scene, Scene settingsScene, Stage stage, SettingsManager settingsManager, StartManager startManager, Scene gameScene, Group gameGroup, HighscoreManager highscoreManager, Scene highscoreScene){
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                buttonBox.setMinSize(scene.getWidth(), 0);
                imageBox.setPrefSize(scene.getWidth(), scene.getHeight());
            }
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                buttonBox.setLayoutY(scene.getHeight() * 0.9 - 150);
                imageBox.setPrefSize(scene.getWidth(), scene.getHeight());
            }
        });

        /**
         * Mouseover event handlers for each button
         */
        start.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(start, scene));
        start.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(start, scene));
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            startManager.initView(stage, gameScene, gameGroup, settingsManager);
        });

        exit.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(exit, scene));
        exit.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(exit, scene));
        /**
         * Closes stage if exit button is pressed
         */
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.close();
        });

        settings.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(settings, scene));
        settings.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(settings, scene));
        settings.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            settingsManager.initView(stage, settingsScene);
        });

        highScore.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(highScore, scene));
        highScore.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(highScore, scene));
        highScore.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            highscoreManager.initView(stage, highscoreScene);
        });
    }

    /**
     * Methods to scale button and change mouse cursor on mouse enter / leave
     * @author Justin Ng
     *
     * @param button - button element to act on
     * @param scene - scene to change cursor for
     */
    private void onMouseEnterButton(Button button, Scene scene){
        button.setScaleX(1.25);
        button.setScaleY(1.25);
        scene.setCursor(Cursor.HAND);
    }

    /**
     * Methods to scale button and change mouse cursor on mouse enter / leave
     * @author Justin Ng
     *
     * @param button - button element to act on
     * @param scene - scene to change cursor for
     */
    private void onMouseExitButton(Button button, Scene scene){
        button.setScaleX(1);
        button.setScaleY(1);
        scene.setCursor(Cursor.DEFAULT);
    }
}
