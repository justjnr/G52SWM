package Ng_Justin_4303735.Sprites;

import Ng_Justin_4303735.Managers.GameManager;
import Ng_Justin_4303735.Board.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Ghost extends Rectangle implements Runnable {

    String direction;
    GameManager gameManager;
    Maze maze;
    AnimationTimer animation;
    int timesWalked;

    /**
     * Load ghost sprite files for each direction
     */
    Image ghostGreenRight = new Image("file:resc/img/ghost1_right.png",false);
    Image ghostGreenLeft = new Image("file:resc/img/ghost1_left.png",false);
    Image ghostGreenUp = new Image("file:resc/img/ghost1_up.png",false);
    Image ghostGreenDown = new Image("file:resc/img/ghost1_down.png",false);

    Image ghostBlueRight = new Image("file:resc/img/ghost2_right.png",false);
    Image ghostBlueLeft = new Image("file:resc/img/ghost2_left.png",false);
    Image ghostBlueUp = new Image("file:resc/img/ghost2_up.png",false);
    Image ghostBlueDown = new Image("file:resc/img/ghost2_down.png",false);

    Image ghostPinkRight = new Image("file:resc/img/ghost3_right.png",false);
    Image ghostPinkLeft = new Image("file:resc/img/ghost3_left.png",false);
    Image ghostPinkUp = new Image("file:resc/img/ghost3_up.png",false);
    Image ghostPinkDown = new Image("file:resc/img/ghost3_down.png",false);

    Image ghostYellowRight = new Image("file:resc/img/ghost4_right.png",false);
    Image ghostYellowLeft = new Image("file:resc/img/ghost4_left.png",false);
    Image ghostYellowUp = new Image("file:resc/img/ghost4_up.png",false);
    Image ghostYellowDown = new Image("file:resc/img/ghost4_down.png",false);

    Image ghostPurpleRight = new Image("file:resc/img/ghost5_right.png",false);
    Image ghostPurpleLeft = new Image("file:resc/img/ghost5_left.png",false);
    Image ghostPurpleUp = new Image("file:resc/img/ghost5_up.png",false);
    Image ghostPurpleDown = new Image("file:resc/img/ghost5_down.png",false);

    /**
     * Constructor to initialise ghost properties
     *
     * @param x - x position of ghost
     * @param y - y position of ghost
     * @param color - colour to set the ghost to
     * @param maze - maze passed to be set
     * @param gameManager - instance of GameManager to call methods from
     */
    public Ghost(double x, double y, Color color, Maze maze, GameManager gameManager) {
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.gameManager = gameManager;
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(color);
        this.timesWalked = 0;
        this.direction = "down";
        this.createAnimation(color, this);
        if (color == Color.GREEN){
            this.setFill(new ImagePattern(ghostGreenUp));
        }
        if (color == Color.DEEPPINK){
            this.setFill(new ImagePattern(ghostPinkUp));
        }
        if (color == Color.LIGHTSKYBLUE){
            this.setFill(new ImagePattern(ghostBlueRight));
        }
        if (color == Color.RED){
            this.setFill(new ImagePattern(ghostPurpleRight));
        }
        if (color == Color.ORANGE){
            this.setFill(new ImagePattern(ghostYellowRight));
        }
    }

    /**
     * gets random direction which passes two parameters in
     *
     * @param exclude1 - excludes this direction parameter
     * @param exclude2 - excludes this direction parameter
     * @return - returns random direction
     */
    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    /**
     * Gets the animation for the ghost
     * @return - returns animation as animationTimer
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     * Checks if ghost has path to go to
     *
     * @param direction - direction in which the ghost is travelling
     */
    private void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }

                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
    }

    /**
     * Moves ghost until an object is detected
     *
     * @param whereToGo - direction in which the ghost is travelling
     * @param whereToChangeTo - new direction to change to
     * @param leftEdge - left edge of maze
     * @param topEdge - top edge of maze
     * @param rightEdge - right edge of maze
     * @param bottomEdge - bottom edge of maze
     * @param padding - padding of ghost
     */
    private void moveUntilYouCant(String whereToGo, String whereToChangeTo, double leftEdge, double topEdge, double rightEdge, double bottomEdge, double padding) {
        double step = 5;
        switch (whereToGo) {
            case "left":
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setX(leftEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setX(getX() + 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "right":
                if (!maze.isTouching(rightEdge, topEdge, padding)) {
                    setX(leftEdge + step);
                } else {
                    while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                        setX(getX() - 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "up":
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setY(topEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setY(getY() + 1);
                    }
                    direction = "left";
                }
                break;
            case "down":
                if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
                    setY(topEdge + step);
                } else {
                    while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                        setY(getY() - 1);
                    }
                    direction = "right";
                }
                break;
        }

    }

    /**
     * Creates an animation of the ghost
     *
     * @param color - ghost colour to be passed in
     * @param ghost - ghost to be passed in
     */
    public void createAnimation(Color color, Ghost ghost) {

        this.animation = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameManager.checkGhostCoalition();
                gameManager.checkSpriteOutsideMap();
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 12;
                timesWalked++;
                int walkAtLeast = 4;
                switch (direction) {
                    case "left":
                        moveUntilYouCant("left", "down", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkIftheresPathToGo(getRandomDirection("left", "right"));
                            timesWalked = 0;
                        }
                        changeGhostImage("left", color, ghost);
                        break;
                    case "right":
                        moveUntilYouCant("right", "up", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkIftheresPathToGo(getRandomDirection("left", "right"));
                             timesWalked = 0;
                        }
                        changeGhostImage("right", color, ghost);
                        break;
                    case "up":
                        moveUntilYouCant("up", "left", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkIftheresPathToGo(getRandomDirection("up", "down"));
                            timesWalked = 0;
                        }
                        changeGhostImage("up", color, ghost);
                        break;
                    case "down":
                        moveUntilYouCant("down", "right", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkIftheresPathToGo(getRandomDirection("up", "down"));
                            timesWalked = 0;
                        }
                        changeGhostImage("down", color, ghost);
                        break;
                }
            }
        };
    }

    /**
     * Change the ghost image depending on direction and colour of the ghost
     * @author Justin Ng
     *
     * @param direction - direction of the ghost
     * @param color - colour of the ghost
     * @param ghost - ghost passed through to be acted on
     */
    public void changeGhostImage(String direction, Color color, Ghost ghost){
        if (direction == "left"){
            if (color == Color.GREEN){
                ghost.setFill(new ImagePattern(ghostGreenLeft));
            }
            if (color == Color.DEEPPINK){
                ghost.setFill(new ImagePattern(ghostPinkLeft));
            }
            if (color == Color.LIGHTSKYBLUE){
                ghost.setFill(new ImagePattern(ghostBlueLeft));
            }
            if (color == Color.RED){
                ghost.setFill(new ImagePattern(ghostPurpleLeft));
            }
            if (color == Color.ORANGE){
                ghost.setFill(new ImagePattern(ghostYellowLeft));
            }
        }
        if (direction == "right"){
            if (color == Color.GREEN){
                ghost.setFill(new ImagePattern(ghostGreenRight));
            }
            if (color == Color.DEEPPINK){
                ghost.setFill(new ImagePattern(ghostPinkRight));
            }
            if (color == Color.LIGHTSKYBLUE){
                ghost.setFill(new ImagePattern(ghostBlueRight));
            }
            if (color == Color.RED){
                ghost.setFill(new ImagePattern(ghostPurpleRight));
            }
            if (color == Color.ORANGE){
                ghost.setFill(new ImagePattern(ghostYellowRight));
            }
        }
        if (direction == "up"){
            if (color == Color.GREEN){
                ghost.setFill(new ImagePattern(ghostGreenUp));
            }
            if (color == Color.DEEPPINK){
                ghost.setFill(new ImagePattern(ghostPinkUp));
            }
            if (color == Color.LIGHTSKYBLUE){
                ghost.setFill(new ImagePattern(ghostBlueUp));
            }
            if (color == Color.RED){
                ghost.setFill(new ImagePattern(ghostPurpleUp));
            }
            if (color == Color.ORANGE){
                ghost.setFill(new ImagePattern(ghostYellowUp));
            }
        }
        if (direction == "down"){
            if (color == Color.GREEN){
                ghost.setFill(new ImagePattern(ghostGreenDown));
            }
            if (color == Color.DEEPPINK){
                ghost.setFill(new ImagePattern(ghostPinkDown));
            }
            if (color == Color.LIGHTSKYBLUE){
                ghost.setFill(new ImagePattern(ghostBlueDown));
            }
            if (color == Color.RED){
                ghost.setFill(new ImagePattern(ghostPurpleDown));
            }
            if (color == Color.ORANGE){
                ghost.setFill(new ImagePattern(ghostYellowDown));
            }
        }
    }
    @Override
    public void run() {
        this.animation.start();
    }
}
