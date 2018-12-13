package Ng_Justin_4303735.Sprites;

import Ng_Justin_4303735.Managers.GameManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {

    /** Load pacman sprite files
     *
     */
    public final Image pacmanRight = new Image("file:resc/img/pacman_right.png",false);
    public final Image pacmanLeft = new Image("file:resc/img/pacman_left.png",false);
    public final Image pacmanUp = new Image("file:resc/img/pacman_up.png",false);
    public final Image pacmanDown = new Image("file:resc/img/pacman_down.png",false);

    public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        this.setFill(Color.YELLOW);
        this.setFill(new ImagePattern(pacmanRight));
    }

    /**
     * Moves the pacman
     * @param event - On key down event passed with information about the event
     */
    public void movePacman(KeyEvent event, GameManager gameManager) {
        for (Ghost ghost : gameManager.getGhosts()) {
            ghost.run();
        }
        switch(event.getCode()) {
            case RIGHT:
                gameManager.startPacmanAnimation("right");
                break;
            case LEFT:
                gameManager.startPacmanAnimation("left");
                break;
            case UP:
                gameManager.startPacmanAnimation("up");
                break;
            case DOWN:
                gameManager.startPacmanAnimation("down");
                break;
        }
    }

    /**
     * Stops the pacman
     * @param event - On key down event passed with information about the event
     */
    public void stopPacman(KeyEvent event, GameManager gameManager) {
        switch(event.getCode()) {
            case RIGHT:
                gameManager.stopPacmanAnimation("right");
                break;
            case LEFT:
                gameManager.stopPacmanAnimation("left");
                break;
            case UP:
                gameManager.stopPacmanAnimation("up");
                break;
            case DOWN:
                gameManager.stopPacmanAnimation("down");
                break;
        }
    }
}
