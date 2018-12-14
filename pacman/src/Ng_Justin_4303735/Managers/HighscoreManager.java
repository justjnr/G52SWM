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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class HighscoreManager {
    private Label highscoresLabel = new Label("HIGHSCORES");
    private VBox highscoresBox = new VBox();
    private Button returnButton = new Button("RETURN");
    private Font menuFontTemp;
    private int maxHighscores;

    /**
     * Constructor to initialise highscore including calling the init method
     * @author Justin Ng
     *
     * @param stage - active stage to be used
     * @param highscoreScene - high score scene to pass to initHighscore
     * @param menuScene - menu scene to pass through to initHighscore
     * @param highscoreGroup - high score group to pass to initHighscore
     * @param canvas - canvas element to pass to initHighScore
     * @param titleFont - larger titlefont used for the heading
     * @param menuFont - current menufont used by some ui elements
     */
    public HighscoreManager(Stage stage, Scene highscoreScene, Scene menuScene, Group highscoreGroup, Canvas canvas, Font titleFont, Font menuFont){
        menuFontTemp = menuFont;
        initHighscore(stage, highscoreScene, menuScene, highscoreGroup, canvas, menuFont, titleFont);
    }

    /**
     * Initialises high score screen to create UI
     * @author Justin Ng
     *
     * @param stage - active stage to be used
     * @param highscoreScene - high score scene to pass to initEventHandlers
     * @param menuScene - menu scene to pass to initEventHandlers
     * @param group - active group to add ui elements to
     * @param canvas - canvas element to add to group
     * @param menuFont - current menufont used by some ui elements
     * @param titleFont - larger titlefont used for the heading
     */
    private void initHighscore(Stage stage, Scene highscoreScene, Scene menuScene, Group group, Canvas canvas, Font menuFont, Font titleFont){
        highscoresLabel.setTextFill(Color.CORAL);
        highscoresLabel.setFont(titleFont);
        highscoresLabel.setTextAlignment(TextAlignment.CENTER);
        highscoresLabel.setPadding(new Insets(0, 0, 50, 0));

        returnButton.setTextFill(Color.WHITE);
        returnButton.setFont(menuFont);
        returnButton.setTextAlignment(TextAlignment.CENTER);
        returnButton.setStyle("-fx-background-color: Transparent");

        highscoresBox.getChildren().add(highscoresLabel);
        maxHighscores = 0;
        getHighscores(menuFontTemp);
        highscoresBox.getChildren().add(returnButton);
        //highscoresBox.setMaxHeight(500);
        highscoresBox.setAlignment(Pos.CENTER);

        group.getChildren().add(canvas);
        group.getChildren().add(highscoresBox);

        initEventHandlers(highscoreScene, menuScene, stage);
    }

    /**
     * Initialises the high score view by setting it to the stage and setting size of window
     * @author Justin Ng
     *
     * @param stage - active stage to be used
     * @param highscoreScene - high score scene to be set to stage
     */
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
     * @param menuScene - the menu scene to return back to from the highscore screen
     * @param scene - the current high score scene
     * @param stage - the active stage to attach scenes to
     */
    private void initEventHandlers(Scene scene, Scene menuScene, Stage stage) {
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

    /**
     * Retrieves high scores from text file and sorts them through reverse order. For every line in the  high scores file,
     * call generatehighscores function to print out labels
     * @author Justin Ng
     *
     * @param titleFont - Passes the font to be used for generateHighScores
     */
    private void getHighscores(Font titleFont){
        //for each line in text file, create HBox with pair of labels
        //make sure VBox containing can scroll if height greater than stage
        Path highscoreFile = Paths.get("highscores.txt");

        try (Stream<String> highscoreFilelines = Files.lines(highscoreFile)) {
            highscoreFilelines.sorted(Comparator.reverseOrder()).forEachOrdered(line -> this.generateHighscores(line, titleFont));
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    /**
     * Creates new labels for each line and adds to highscoresbox up to maxHighscores
     * @author Justin Ng
     *
     * @param titleFont - Passes the font to be used for generateHighScores
     * @param line - the line to generate label for
     */
    private void generateHighscores(String line, Font titleFont){
        if (maxHighscores < 5) {
            System.out.println(maxHighscores + 1 + "-   " + line);
            Label label = new Label(maxHighscores + 1 + "-   " + line);

            label.setTextFill(Color.WHITE);
            label.setFont(titleFont);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPadding(new Insets(0, 0, 20, 0));

            highscoresBox.getChildren().add(label);
            maxHighscores++;
        }
    }
}
