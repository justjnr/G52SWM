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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuManager {

    public Button start;
    public Button settings;
    public Button highScore;
    public Button exit;

    HBox buttonBox = new HBox();
    HBox imageBox = new HBox();

    Image menuImage = new Image("file:resc/img/menu.png",false);
    ImageView menuImageView = new ImageView(menuImage);
    /**
     * Initialise the menu scene
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
     * @param scene
     * @param stage
     */
    public void initEventHandlers(Scene scene, Scene settingsScene, Stage stage, SettingsManager settingsManager, StartManager startManager, Scene gameScene, Group gameGroup, HighscoreManager highscoreManager, Scene highscoreScene){
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

        start.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(start, scene));
        start.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(start, scene));
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            startManager.initView(stage, gameScene, gameGroup, settingsManager);
        });

        exit.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(exit, scene));
        exit.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(exit, scene));
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
}
