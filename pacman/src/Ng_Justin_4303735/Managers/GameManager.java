package Ng_Justin_4303735.Managers;

import Ng_Justin_4303735.Board.BarObstacle;
import Ng_Justin_4303735.Board.Maze;
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
    private GameUIManager gameUIManagerBoard;
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
    SettingsManager settingsManagerTemp;

    /**
     * Constructor to initialise the game
     *
     * @param root - current group to be used
     * @param settingsManager - instance of settingsManager passed through
     */
    public GameManager(Group root, SettingsManager settingsManager) {
        settingsManagerTemp = settingsManager;
        this.root = root;
        this.maze = new Maze(settingsManagerTemp);
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
        generateGhosts(settingsManagerTemp);
    }

    /**
     * Method that stops pacman animation and sets text in label
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
        if (score >= 10){
            score -= 10;
        }
        this.gameUIManagerBoard.lifesLabel.setText("Lifes: " + this.lifes);
        this.gameUIManagerBoard.scoreLabel.setText("Score: " + this.score);
        if (lifes == 0) {
            this.endGame();
        }
    }

    /**
     * Setter to set life lost
     */
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
        /*javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
        endGame.setX(BarObstacle.THICKNESS * 3);
        endGame.setY(BarObstacle.THICKNESS * 28);
        endGame.setFont(Font.font("Arial", 40));
        endGame.setFill(Color.ROYALBLUE);*/
        root.getChildren().remove(this.gameUIManagerBoard.scoreLabel);
        root.getChildren().remove(this.gameUIManagerBoard.lifesLabel);
        //root.getChildren().add(endGame);
        outHighscore();
        gameUIManagerBoard.generatePopupBox(score, root);
    }

    /**
     * Outputs high score to a text file
     * @author Justin Ng
     */
    public void outHighscore(){
        try(FileWriter fWriter = new FileWriter("highscores.txt", true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            PrintWriter pWriter = new PrintWriter(bWriter))
        {
            pWriter.println(score);

        } catch (IOException e) {
            System.out.print(e);
        }

        try(FileWriter fWriter = new FileWriter("highscorediff.txt", true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            PrintWriter pWriter = new PrintWriter(bWriter))
        {
            pWriter.println(settingsManagerTemp.getDifficultySettings());

        } catch (IOException e) {
            System.out.print(e);
        }
    }

    /**
     * Restart the game
     *
     * @param event - key event details passed to method
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            gameUIManagerBoard.removePopupBox(root);
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            generateGhosts(settingsManagerTemp);
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
     * getter to return maze from the GameManager Class
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
        boardManager.drawBoard(root, ghosts, pacman, this);
    }

    /**
     * Generates the ghosts for the pacman!
     *
     * @param settingsManager - instance of SettingsManager
     */
    public void generateGhosts(SettingsManager settingsManager) {
        if (settingsManager.getDifficultySettings() == "NORMAL"){
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
            this.ghosts.add(new Ghost(23.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
            this.ghosts.add(new Ghost(27.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
            this.ghosts.add(new Ghost(21.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
            this.ghosts.add(new Ghost(25.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.GREEN, maze, this));
        }
        if (settingsManager.getDifficultySettings() == "EASY"){
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
            this.ghosts.add(new Ghost(23.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
            this.ghosts.add(new Ghost(27.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
        }
        if (settingsManager.getDifficultySettings() == "HARD"){
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
            this.ghosts.add(new Ghost(23.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
            this.ghosts.add(new Ghost(27.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
            this.ghosts.add(new Ghost(23.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.GREEN, maze, this));
            this.ghosts.add(new Ghost(17.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
        }
        if (settingsManager.getDifficultySettings() == "WTF"){
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.GREEN, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.LIGHTSKYBLUE, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.RED, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.ORANGE, maze, this));
            this.ghosts.add(new Ghost(19.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.GREEN, maze, this));
        }
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
     *
     * @return - returns the new AnimationTimer
     */
    private AnimationTimer createAnimation(String direction) {
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                checkSpriteOutsideMap();
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() - step);
                            if (checkCookieCoalition(pacman, "x")){
                                this.stop();
                                endGame();
                            }
                            checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacman.pacmanLeft));
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setCenterX(pacman.getCenterX() + step);
                            if (checkCookieCoalition(pacman, "x")){
                                this.stop();
                                endGame();
                            }
                            checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacman.pacmanRight));
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() - step);
                            if (checkCookieCoalition(pacman, "y")){
                                this.stop();
                                endGame();
                            }
                            checkGhostCoalition();
                            pacman.setFill(new ImagePattern(pacman.pacmanUp));
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setCenterY(pacman.getCenterY() + step);
                            if (checkCookieCoalition(pacman, "y")){
                                this.stop();
                                endGame();
                            }
                            checkGhostCoalition();
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
     * @param p - direction passed
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
     * @param p - direction passed
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
     * @return - returns cookie set as Set type
     */
    public Set<Cookie> getCookieSet(){
        return this.cookieSet;
    }

    /**
     * Getter to get ghosts as it is a private variable
     *
     * @return - returns ghosts as Set type
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
     * Getter to get gameUIManagerBoard as it is a private variable
     *
     * @return - returns gameUIManagerBoard variable as type GameUIManager
     */
    public GameUIManager getGameUIManagerBoard(){
        return this.gameUIManagerBoard;
    }

    /**
     * Creates a new score in gameUIManagerBoard
     */
    public void createNewScore(){
         this.gameUIManagerBoard = new GameUIManager(root);
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
     *
     * @param axis - axis pacman is on
     * @param pacman - pacman to be passed through to check cookie coalition
     *
     * @return - returns true or false depending on whether it is touching
     */
    public boolean checkCookieCoalition(Pacman pacman, String axis) {
        if (collisionDetect.checkCookieCoalition(pacman, axis, cookieSet, this)){
            return true;
        }
        return false;
    }

    /**
     * Calls checkGhostCoalition in CollisionDetect class
     */
    public void checkGhostCoalition() {
        collisionDetect.checkGhostCoalition(pacman, ghosts, this);
    }

    /**
     * calls collision detect methods to check whether the sprites are outside map
     * @author Justin Ng
     */
    public void checkSpriteOutsideMap() {
        collisionDetect.checkPacmanOutsideMap(pacman, this);
        collisionDetect.checkGhostOutsideMap(ghosts, this);
    }
}
