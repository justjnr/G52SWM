package Ng_Justin_4303735;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Score {
    public Label scoreLabel = new Label( "Score: 0");
    public Label lifesLabel = new Label("Lifes: 3");
    public Label returnLabel = new Label("RETURN");
    public HBox scoreBox = new HBox();
    Font gameFont = Font.loadFont("file:resc/font/snnn.ttf", 16);

    public Score(Group root) {
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
        scoreBox.getChildren().add(returnLabel);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setMinSize(1280, 0);
        root.getChildren().add(scoreBox);
    }
}
