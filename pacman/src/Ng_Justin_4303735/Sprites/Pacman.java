package Ng_Justin_4303735.Sprites;



import Ng_Justin_4303735.Board.Maze;
import Ng_Justin_4303735.Managers.GameManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {

    /** Load pacman sprite files
     *
     */
    Image pacmanRight = new Image("file:resc/img/pacman_right.png",false);
    Image pacmanLeft = new Image("file:resc/img/pacman_left.png",false);
    Image pacmanUp = new Image("file:resc/img/pacman_up.png",false);
    Image pacmanDown = new Image("file:resc/img/pacman_down.png",false);

    public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        this.setFill(Color.YELLOW);
        this.setFill(new ImagePattern(pacmanRight));
    }

    /**
     * Creates an animation of the movement.
     * @param direction
     * @return
     */
    public AnimationTimer createAnimation(String direction, Maze maze, Pacman pacman, GameManager gameManager) {
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() - step);
                            gameManager.checkCookieCoalition(pacman, "x");
                            gameManager.checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacmanLeft));
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() + step);
                            gameManager.checkCookieCoalition(pacman, "x");
                            gameManager.checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacmanRight));
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() - step);
                            gameManager.checkCookieCoalition(pacman, "y");
                            gameManager.checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacmanUp));
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() + step);
                            gameManager.checkCookieCoalition(pacman, "y");
                            gameManager.checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacmanDown));
                        }
                        break;
                }
            }
        };
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
