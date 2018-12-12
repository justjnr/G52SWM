package Ng_Justin_4303735;



import Ng_Justin_4303735.Board.BarObstacle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score {

    public Text score;
    public Text lifes;
    Font menuFont = Font.loadFont("file:resc/font/snnn.ttf", 16);

    public Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 20, BarObstacle.THICKNESS * 28,"Lifes: 3");
        score.setFill(Color.WHITE);
        score.setFont(menuFont);

        lifes.setFill(Color.WHITE);
        lifes.setFont(menuFont);

        root.getChildren().add(score);
        root.getChildren().add(lifes);
    }
}
