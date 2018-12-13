package Ng_Justin_4303735.Managers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HighscoreManager {
    private Label highscoresLabel = new Label("HIGHSCORES");

    private VBox highscoresBox = new VBox();

    private Button returnButton = new Button("RETURN");

    /**
     *
     * @param stage
     * @param highscoreScene
     * @param highscoreGroup
     * @param canvas
     * @param titleFont
     * @param menuFont
     */
    public HighscoreManager(Stage stage, Scene highscoreScene, Scene menuScene, Group highscoreGroup, Canvas canvas, Font titleFont, Font menuFont){
        initHighscore(stage, highscoreScene, menuScene, highscoreGroup, canvas, menuFont, titleFont);
    }

    public void initHighscore(Stage stage, Scene highscoreScene, Scene menuScene, Group group, Canvas canvas, Font menuFont, Font titleFont){
        highscoresLabel.setTextFill(Color.CORAL);
        highscoresLabel.setFont(titleFont);
        highscoresLabel.setTextAlignment(TextAlignment.CENTER);
        highscoresLabel.setPadding(new Insets(0, 0, 50, 0));

        returnButton.setTextFill(Color.WHITE);
        returnButton.setFont(menuFont);
        returnButton.setTextAlignment(TextAlignment.CENTER);
        returnButton.setStyle("-fx-background-color: Transparent");

        highscoresBox.getChildren().add(highscoresLabel);
        highscoresBox.getChildren().add(returnButton);
        highscoresBox.setAlignment(Pos.CENTER);

        group.getChildren().add(canvas);
        group.getChildren().add(highscoresBox);

        initEventHandlers(highscoreScene, menuScene, stage);
    }

    public void initView(Stage stage, Scene highscoreScene){
        stage.setTitle("Pacman - Highscores");
        highscoreScene.setFill(Color.BLACK);
        stage.setScene(highscoreScene);

        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        highscoresBox.setPrefSize(1280, 720);
    }

    /**
     * Initialises event listener to detect when window is resized and sets text position relative to window and detects button mouseover / mouseenter
     * @author Justin Ng
     *
     * @param menuScene
     * @param scene
     * @param stage
     */
    public void initEventHandlers(Scene scene, Scene menuScene, Stage stage) {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                highscoresBox.setPrefSize(scene.getWidth(), scene.getHeight());
                highscoresBox.setMinSize(scene.getWidth(), 0);
            }
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                highscoresBox.setPrefSize(scene.getWidth(), scene.getHeight());
            }
        });

        returnButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(returnButton, scene));
        returnButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(returnButton, scene));
        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setTitle("Pacman");
            stage.setScene(menuScene);
            //System.out.print(bgCButton.getText());
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
