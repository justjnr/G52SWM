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
    /**
     * Declarations for all the UI elements
     */
    private Label settingsLabel = new Label("SETTINGS");

    private Label difficultyLabel = new Label("DIFFICULTY");
    private String[] difficultyArr = new String[]{"EASY", "NORMAL", "HARD", "WTF"};
    private int difficultyIndex = 1;
    private Button difficultyButton = new Button(difficultyArr[difficultyIndex]);
    private HBox difficultyBox = new HBox();

    private Label bgCLabel = new Label("BOARD COLOUR");
    private String[] bgCArr = new String[]{"WHITE", "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "BLACK"};
    private int bgCIndex = 7;
    private Button bgCButton = new Button(bgCArr[bgCIndex]);
    private HBox bgCBox = new HBox();

    private Label fgCLabel = new Label("WALL COLOUR");
    private String[] fgCArr = new String[]{"WHITE", "RED", "ORANGE", "YELLOW", "GREEN", "DARKBLUE", "PURPLE", "BLACK"};
    private int fgCIndex = 5;
    private Button fgCButton = new Button(fgCArr[fgCIndex]);
    private HBox fgCBox = new HBox();
    private Button returnButton = new Button("RETURN");
    private VBox settingsBox = new VBox();

    /**
     * Constructor to initialise the settings scene by calling initSettings
     * @author Justin Ng
     *
     * @param stage - current stage to be passed through
     * @param settingsScene - settings scene passed through from ViewManager
     * @param menuScene - menu scene passed through from viewManager
     * @param group - group passed from viewManager
     * @param canvas - canvas passed from viewManager
     * @param titleFont - Passes the font to be used for initSettings
     * @param menuFont - Passes the menu font to be used for initSettings
     */
    public SettingsManager(Stage stage, Scene settingsScene, Scene menuScene, Group group, Canvas canvas, Font titleFont, Font menuFont){
        initSettings(stage, settingsScene, menuScene, group, canvas, titleFont, menuFont);
    }

    /**
     * Initialises the settings scene by formatting UI labels and adding to HBox / VBox
     * @author Justin Ng
     *
     * @param stage - stage to be acted upon
     * @param settingsScene - settings scene to be passed through
     * @param menuScene - menu scene to be passed through
     * @param group - current group to add elements to
     * @param canvas - canvas to be added to group
     * @param titleFont - title font to be used for a label
     * @param menuFont - menu font to be used for smaller labels
     */
    private void initSettings(Stage stage, Scene settingsScene, Scene menuScene, Group group, Canvas canvas, Font titleFont, Font menuFont){
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
        initEventHandlers(settingsScene, menuScene, stage);
    }

    /**
     * Initialises the view when switching to the settings scene
     * Sets title, colour and sizes scene
     * @author Justin Ng
     *
     * @param stage - current stage to be acted on
     * @param settingsScene - scene to set for stage
     */
    public void initView(Stage stage, Scene settingsScene){
        stage.setTitle("Pacman - Settings");
        settingsScene.setFill(Color.BLACK);
        stage.setScene(settingsScene);

        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        settingsBox.setPrefSize(1280, 720);
        //settingsBox.setMinSize(stage.getWidth(), 0);
    }

    /**
     * Initialises event listener to detect when window is resized and sets text position relative to window and detects button mouseover / mouseenter
     * @author Justin Ng
     *
     * @param menuScene - menu scene to be set if return button is clicked
     * @param scene - current settings scene to add mouse event listeners to
     * @param stage - current stage to be acted on
     */
    private void initEventHandlers(Scene scene, Scene menuScene, Stage stage) {
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
            //System.out.print(bgCButton.getText());
        });

        difficultyButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(difficultyButton, scene));
        difficultyButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(difficultyButton, scene));
        difficultyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.onSettingClick(difficultyButton));

        bgCButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(bgCButton, scene));
        bgCButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(bgCButton, scene));
        bgCButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.onSettingClick(bgCButton));

        fgCButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.onMouseEnterButton(fgCButton, scene));
        fgCButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.onMouseExitButton(fgCButton, scene));
        fgCButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.onSettingClick(fgCButton));
    }

    /**
     * Event handler to increment index and set text to new text in array
     * @author Justin Ng
     *
     * @param button - checks which buttons is sent as a parameter
     */
    private void onSettingClick(Button button){
        if (button == difficultyButton){
            incrementIndex(difficultyIndex, difficultyArr, "difficulty");
            button.setText(difficultyArr[difficultyIndex]);
        }
        if (button == bgCButton){
            incrementIndex(bgCIndex, bgCArr, "bgC");
            button.setText(bgCArr[bgCIndex]);
        }
        if (button == fgCButton){
            incrementIndex(fgCIndex, fgCArr, "fgC");
            button.setText(fgCArr[fgCIndex]);
        }

    }
    private void onMouseEnterButton(Button button, Scene scene){
        button.setScaleX(1.25);
        button.setScaleY(1.25);
        scene.setCursor(Cursor.HAND);
    }
    private void onMouseExitButton(Button button, Scene scene){
        button.setScaleX(1);
        button.setScaleY(1);
        scene.setCursor(Cursor.DEFAULT);
    }

    /**
     * Increments index of array passed
     * @author Justin Ng
     *
     * @param arr - Array passed through used to check if index is less than total length of the array
     * @param i - Current value of the index
     * @param type - Name of value to be changed
     */
    private void incrementIndex(int i, String[] arr, String type){
        if (i >= arr.length - 1){
            if (type == "difficulty"){
                difficultyIndex = 0;
            }
            if (type == "bgC"){
                bgCIndex = 0;
            }
            if (type == "fgC"){
                fgCIndex = 0;
            }
        } else {
            if (type == "difficulty"){
                difficultyIndex++;
            }
            if (type == "bgC"){
                bgCIndex++;
            }
            if (type == "fgC"){
                fgCIndex++;
            }
        }
    }

    /**
     * Getter to return setting value as string
     *
     * @return - returns a string containing the difficulty setting
     */
    public String getDifficultySettings(){
        return difficultyButton.getText();
    }
    /**
     * Getter to return setting value as string
     *
     * @return - returns a string containing the background board colour setting
     */
    public String getbgCSetting(){
        return bgCButton.getText();
    }
    /**
     * Getter to return setting value as string
     *
     * @return - returns a string containing the foreground wall colour setting
     */
    public String getfgCSetting(){
        return fgCButton.getText();
    }
}
