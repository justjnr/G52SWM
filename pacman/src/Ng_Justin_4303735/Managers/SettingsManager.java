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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SettingsManager {

    //Image settingsImage = new Image("file:resc/img/menu.png",false);
    //ImageView settingsImageView = new ImageView(menuImage);

    Label settingsLabel = new Label("SETTINGS");

    Label difficultyLabel = new Label("DIFFICULTY");
    String[] difficultyArr = new String[]{"EASY", "NORMAL", "HARD", "WTF"};
    Button difficultyButton = new Button(difficultyArr[1]);
    HBox difficultyBox = new HBox();

    Label bgCLabel = new Label("BOARD COLOUR");
    String[] bgCArr = new String[]{"WHITE", "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "BLACK"};
    Button bgCButton = new Button(bgCArr[7]);
    HBox bgCBox = new HBox();

    Label fgCLabel = new Label("WALL COLOUR");
    String[] fgCArr = new String[]{"WHITE", "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "BLACK"};
    Button fgCButton = new Button(fgCArr[0]);
    HBox fgCBox = new HBox();

    Button returnButton = new Button("RETURN");

    VBox settingsBox = new VBox();

    MenuManager menuManager;
    /**
     * Constructor for this class
     * @author Justin Ng
     *
     * @param stage
     * @param scene
     * @param group
     * @param canvas
     */
    public SettingsManager(Stage stage, Scene scene, Scene menuScene, Group group, Canvas canvas, Font titleFont, Font menuFont){
        stage.setTitle("Pacman - Settings");
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        settingsLabel.setTextFill(Color.SKYBLUE);
        settingsLabel.setFont(titleFont);
        settingsLabel.setTextAlignment(TextAlignment.CENTER);
        settingsLabel.setPadding(new Insets(0, 0, 50, 0));

        difficultyLabel.setTextFill(Color.LIGHTGRAY);
        difficultyLabel.setFont(menuFont);
        //difficultyLabel.setTextAlignment(TextAlignment.CENTER);
        difficultyLabel.setPadding(new Insets(0, 40, 0, 0));

        difficultyButton.setTextFill(Color.WHITE);
        difficultyButton.setFont(menuFont);
        difficultyButton.setTextAlignment(TextAlignment.CENTER);
        difficultyButton.setStyle("-fx-background-color: Transparent");

        difficultyBox.getChildren().add(difficultyLabel);
        difficultyBox.getChildren().add(difficultyButton);
        difficultyBox.setAlignment(Pos.CENTER);

        bgCLabel.setTextFill(Color.LIGHTGRAY);
        bgCLabel.setFont(menuFont);
        //bgCLabel.setTextAlignment(TextAlignment.CENTER);
        bgCLabel.setPadding(new Insets(0, 40, 0, 0));

        bgCButton.setTextFill(Color.WHITE);
        bgCButton.setFont(menuFont);
        bgCButton.setTextAlignment(TextAlignment.CENTER);
        bgCButton.setStyle("-fx-background-color: Transparent");

        bgCBox.getChildren().add(bgCLabel);
        bgCBox.getChildren().add(bgCButton);
        bgCBox.setAlignment(Pos.CENTER);

        fgCLabel.setTextFill(Color.LIGHTGRAY);
        fgCLabel.setFont(menuFont);
        fgCLabel.setTextAlignment(TextAlignment.CENTER);
        fgCLabel.setPadding(new Insets(0, 40, 0, 0));

        fgCButton.setTextFill(Color.WHITE);
        fgCButton.setFont(menuFont);
        //fgCButton.setTextAlignment(TextAlignment.CENTER);
        fgCButton.setStyle("-fx-background-color: Transparent");

        fgCBox.getChildren().add(fgCLabel);
        fgCBox.getChildren().add(fgCButton);
        fgCBox.setAlignment(Pos.CENTER);
        fgCBox.setPadding(new Insets(0, 0, 50, 0));

        returnButton.setTextFill(Color.WHITE);
        returnButton.setFont(menuFont);
        returnButton.setTextAlignment(TextAlignment.CENTER);
        returnButton.setStyle("-fx-background-color: Transparent");

        settingsBox.getChildren().add(settingsLabel);
        settingsBox.getChildren().add(difficultyBox);
        settingsBox.getChildren().add(bgCBox);
        settingsBox.getChildren().add(fgCBox);
        settingsBox.getChildren().add(returnButton);

        settingsBox.setAlignment(Pos.CENTER);

        group.getChildren().add(canvas);
        group.getChildren().add(settingsBox);
        //group.getChildren().add(buttonBox);

        initEventHandlers(scene, menuScene, stage);

        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        settingsBox.setPrefSize(scene.getWidth(), scene.getHeight());
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
                settingsBox.setPrefSize(scene.getWidth(), scene.getHeight());
                settingsBox.setMinSize(scene.getWidth(), 0);
            }
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal != newVal){
                settingsBox.setPrefSize(scene.getWidth(), scene.getHeight());
            }
        });

        returnButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(returnButton, scene));
        returnButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(returnButton, scene));

        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setTitle("Pacman");
            stage.setScene(menuScene);
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
