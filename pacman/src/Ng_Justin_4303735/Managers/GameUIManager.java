package Ng_Justin_4303735.Managers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameUIManager {
    public Label scoreLabel = new Label( "Score: 0");
    public Label lifesLabel = new Label("Lifes: 3");

    private Label returnLabel = new Label("RETURN");
    private HBox scoreBox = new HBox();
    private VBox popupBox = new VBox();
    private Font gameFont = Font.loadFont("file:resc/font/snnn.ttf", 16);
    private Font popupFont = Font.loadFont("file:resc/font/snnn.ttf", 60);

    /**
     * Constructor responsible for generating UI elements on the game scene
     * @author Justin Ng
     *
     * @param root - group to be passed through to add elements to
     */
    public GameUIManager(Group root) {
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(gameFont);
        scoreLabel.setPadding(new Insets(0,20,0,0));

        lifesLabel.setTextFill(Color.WHITE);
        lifesLabel.setFont(gameFont);
        lifesLabel.setPadding(new Insets(0,20,0,0));

        returnLabel.setTextFill(Color.WHITE);
        returnLabel.setFont(gameFont);
        returnLabel.setTextAlignment(TextAlignment.CENTER);
        returnLabel.setStyle("-fx-background-color: Transparent");

        scoreBox.getChildren().add(scoreLabel);
        scoreBox.getChildren().add(lifesLabel);
        //scoreBox.getChildren().add(returnLabel);

        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setMinSize(1280, 0);
        scoreBox.setTranslateY(650);
        root.getChildren().add(scoreBox);
    }

    /**
     * Method to be called when game ends to show the popup box
     * and generates UI elements for it
     * @author Justin Ng
     *
     * @param score - the score to be used to display onto the popup
     * @param root - the group to be used to add popup to
     */
    public void generatePopupBox(int score, Group root){
        Label yourScoreLabel = new Label("YOUR SCORE");
        Label scoreLabel = new Label(Integer.toString(score));
        Label endGameLabel = new Label("GAME   OVER. PRESS   ESC   TO   RESTART");

        yourScoreLabel.setTextFill(Color.WHITE);
        yourScoreLabel.setFont(gameFont);
        yourScoreLabel.setPadding(new Insets(0,0,10,0));

        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(popupFont);
        scoreLabel.setPadding(new Insets(0,0,50,0));

        endGameLabel.setTextFill(Color.WHITE);
        endGameLabel.setFont(gameFont);
        endGameLabel.setPadding(new Insets(0,0,10,0));

        popupBox.getChildren().add(yourScoreLabel);
        popupBox.getChildren().add(scoreLabel);
        popupBox.getChildren().add(endGameLabel);

        popupBox.setStyle("-fx-background-color: #000;");
        popupBox.setPrefSize(1280, 800);
        popupBox.setAlignment(Pos.CENTER);
        root.getChildren().add(popupBox);
    }

    /**
     * Hides popup box from view
     * @author Justin Ng
     *
     * @param root - the group to be used to remove from view
     */
    public void removePopupBox(Group root){
        root.getChildren().remove(popupBox);
    }
}
