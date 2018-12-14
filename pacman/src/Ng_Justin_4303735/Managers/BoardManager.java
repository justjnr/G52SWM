package Ng_Justin_4303735.Managers;

import Ng_Justin_4303735.Board.BarObstacle;
import Ng_Justin_4303735.Sprites.Cookie;
import Ng_Justin_4303735.Sprites.Ghost;
import Ng_Justin_4303735.Sprites.Pacman;
import javafx.scene.Group;

import java.util.Arrays;
import java.util.Set;

public class BoardManager {
    /**
     * Draws the board of the game with the cookies and the Pacman
     *
     * @param root - current group to be used
     * @param ghosts - ghosts added to group
     * @param pacman - pacman added to group
     * @param gameManager - instance of game manager to access methods from
     */
    public void drawBoard(Group root, Set<Ghost> ghosts, Pacman pacman, GameManager gameManager) {
        gameManager.getMaze().CreateMaze(root);
        // 1st line
        Integer skip[] = {5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 2nd line
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 4.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 3rd line
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 6.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 4th line
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 8.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 5th line
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 10.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 6th line
        skip = new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 7th line
        skip = new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 14.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 8th line
        skip = new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 16.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 9th line
        skip = new Integer[]{1, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 18.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 10th line
        skip = new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, 20.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }

        // 11th line
        skip = new Integer[]{5, 17};
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2 * i) + 2.5) * BarObstacle.THICKNESS, 22.5 * BarObstacle.THICKNESS);
                gameManager.getCookieSet().add(cookie);
                root.getChildren().add(cookie);
            }
        }
        root.getChildren().add(pacman);
        //gameManager.generateGhosts();
        root.getChildren().addAll(ghosts);
        gameManager.createNewScore();
    }
}
