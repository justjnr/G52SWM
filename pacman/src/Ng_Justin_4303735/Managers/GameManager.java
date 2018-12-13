package Ng_Justin_4303735.Managers;

import Ng_Justin_4303735.Board.BarObstacle;
import Ng_Justin_4303735.Board.Maze;
import Ng_Justin_4303735.Score;
import Ng_Justin_4303735.Sprites.Cookie;
import Ng_Justin_4303735.Sprites.Ghost;
import Ng_Justin_4303735.Sprites.Pacman;
import Ng_Justin_4303735.ColDetect.CollisionDetect;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class GameManager {
    private Pacman pacman;
    private Group root;
    private Maze maze;
    private Score scoreBoard;
    private Set<Cookie> cookieSet;
    private Set<Ghost> ghosts;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
    private int lifes;
    private int score;
    private boolean gameEnded;
    private int cookiesEaten;

    CollisionDetect collisionDetect = new CollisionDetect();
    BoardManager boardManager = new BoardManager();

    /**
     * Constructor
     */
    public GameManager(Group root, SettingsManager settingsManager) {
        this.root = root;
        this.maze = new Maze(settingsManager);
        this.pacman = new Pacman(2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
        this.lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
    }

    /**
     * Set one life less
     */
    private void lifeLost() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.getAnimation().stop();
        }
        this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
        this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
        lifes--;
        score -= 10;
        this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
        this.scoreBoard.score.setText("Score: " + this.score);
        if (lifes == 0) {
            this.endGame();
        }
    }

    public void setLifeLost(){
        lifeLost();
    }

    /**
     * Ends the game
     */
    public void endGame() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
        endGame.setX(BarObstacle.THICKNESS * 3);
        endGame.setY(BarObstacle.THICKNESS * 28);
        endGame.setFont(Font.font("Arial", 40));
        endGame.setFill(Color.ROYALBLUE);
        root.getChildren().remove(this.scoreBoard.score);
        root.getChildren().remove(this.scoreBoard.lifes);
        root.getChildren().add(endGame);

        outHighscore();
    }

    public void outHighscore(){
        try(FileWriter fWriter = new FileWriter("highscores.txt", true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            PrintWriter pWriter = new PrintWriter(bWriter))
        {
            pWriter.println(score);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    /**
     * Restart the game
     * @param event
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            this.drawBoard();
            this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
            this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
            this.lifes = 3;
            this.score = 0;
            this.cookiesEaten = 0;
            gameEnded = false;
        }
    }

    /**
     * Returns maze from the GameManager Class
     *
     * @return - returns maze as type Maze
     */
    public Maze getMaze() {
        return this.maze;
    }

    /**
     * Calls drawBoard method in boardManager to draw game board
     */
    public void drawBoard() {
        boardManager.drawBoard(root, ghosts, pacman, scoreBoard, this);
    }

    /**
     * Generates the ghosts for the pacman!
     */
    public void generateGhosts() {
        this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
        this.ghosts.add(new Ghost(23.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
        this.ghosts.add(new Ghost(27.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
        this.ghosts.add(new Ghost(21.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
        this.ghosts.add(new Ghost(25.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.GREEN, maze, this));
    }

    /**
     * Moves the pacman
     * @param event - passes the key input event to the method
     */
    public void movePacman(KeyEvent event) {
        pacman.movePacman(event, this);
    }

    /**
     * Stops the pacman
     * @param event - passes the key input event to the method
     */
    public void stopPacman(KeyEvent event) {
        pacman.stopPacman(event, this);
    }

    /**
     * Animates the pacman in whichever direction its going in
     * @param direction - passes the direction through to the method
     */
    private AnimationTimer createAnimation(String direction) {
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() - step);
                            checkCookieCoalition(pacman, "x");
                            checkGhostCoalition();
                            checkSpriteOutsideMap();
                            pacman.setFill(new ImagePattern(pacman.pacmanLeft));
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() + step);
                            checkCookieCoalition(pacman, "x");
                            checkGhostCoalition();
                            checkSpriteOutsideMap();
                            pacman.setFill(new ImagePattern(pacman.pacmanRight));
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() - step);
                            checkCookieCoalition(pacman, "y");
                            checkGhostCoalition();
                            checkSpriteOutsideMap();
                            pacman.setFill(new ImagePattern(pacman.pacmanUp));
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() + step);
                            checkCookieCoalition(pacman, "y");
                            checkGhostCoalition();
                            checkSpriteOutsideMap();
                            pacman.setFill(new ImagePattern(pacman.pacmanDown));
                        }
                        break;
                }
            }
        };
    }

    /**
     * Stops Pacman animation
     *
     * @return - returns cookiesEaten variable as an integer
     */
    public void stopPacmanAnimation(String p){
        if (p == "right"){
            this.rightPacmanAnimation.stop();
        }
        if (p == "left"){
            this.leftPacmanAnimation.stop();
        }
        if (p == "up"){
            this.upPacmanAnimation.stop();
        }
        if (p == "down"){
            this.downPacmanAnimation.stop();
        }
    }

    /**
     * Starts Pacman animation
     *
     * @return - returns cookiesEaten variable as an integer
     */
    public void startPacmanAnimation(String p){
        if (p == "right"){
            this.rightPacmanAnimation.start();
        }
        if (p == "left"){
            this.leftPacmanAnimation.start();
        }
        if (p == "up"){
            this.upPacmanAnimation.start();
        }
        if (p == "down"){
            this.downPacmanAnimation.start();
        }
    }

    /**
     * Setter to increment cookiesEaten as it is a private variable
     */
    public void incrCookiesEaten(){
        this.cookiesEaten++;
    }

    /**
     * Getter to get cookiesEaten as it is a private variable
     *
     * @return - returns cookiesEaten variable as an integer
     */
    public int getCookiesEaten(){
        return this.cookiesEaten;
    }

    /**
     * Getter to get cookieSet as it is a private variable
     *
     * @return - returns cookieSet as Set<Cookie> type
     */
    public Set<Cookie> getCookieSet(){
        return this.cookieSet;
    }

    /**
     * Getter to get ghosts as it is a private variable
     *
     * @return - returns ghosts as Set<Ghost> type
     */
    public Set<Ghost> getGhosts(){
        return this.ghosts;
    }

    /**
     * Setter to increment score by amount p
     *
     * @param p - integer value passed to increment score with
     */
    public void incrScore(int p){
        this.score += p;
    }

    /**
     * Getter to get scoreBoard as it is a private variable
     *
     * @return - returns scoreBoard variable as type Score
     */
    public Score getScoreBoard(){
        return this.scoreBoard;
    }

    /**
     * Creates a new score in scoreBoard
     */
    public void createNewScore(){
         this.scoreBoard = new Score(root);
    }

    /**
     * Getter to get score as it is a private variable
     *
     * @return - returns score variable as integer
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Calls checkCookieCoalition in CollisionDetect class
     */
    public void checkCookieCoalition(Pacman pacman, String axis) {
        collisionDetect.checkCookieCoalition(pacman, axis, cookieSet, this);
    }

    /**
     * Calls checkGhostCoalition in CollisionDetect class
     */
    public void checkGhostCoalition() {
        collisionDetect.checkGhostCoalition(pacman, ghosts, this);
    }

    public void checkSpriteOutsideMap() {
        collisionDetect.checkPacmanOutsideMap(pacman, this);
        collisionDetect.checkGhostOutsideMap(ghosts, this);
    }
}
