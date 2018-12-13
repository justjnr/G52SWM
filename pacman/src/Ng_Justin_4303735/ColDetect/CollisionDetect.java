package Ng_Justin_4303735.ColDetect;

import Ng_Justin_4303735.Sprites.Cookie;
import Ng_Justin_4303735.Sprites.Ghost;
import Ng_Justin_4303735.Sprites.Pacman;
import Ng_Justin_4303735.Managers.GameManager;

import java.util.Set;

public class CollisionDetect {
    /**
     * Checks if pacman is touching a ghost
     * @param pacman -
     * @param ghosts -
     * @param gameManager -
     */
    public void checkGhostCoalition(Pacman pacman, Set<Ghost> ghosts, GameManager gameManager) {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    gameManager.setLifeLost();
                }
            }
        }
    }

    /**
     * Checks if Pacman is outside map through openings and is teleported to the opposite side of the map
     * @author Justin Ng
     *
     * @param pacman - pacman to be passed through
     * @param gameManager - game manager instance passed through
     */
    public void checkPacmanOutsideMap(Pacman pacman, GameManager gameManager) {
        double pacmanLeftEdge = pacman.getCenterX() - pacman.getRadius();
        double pacmanRightEdge = pacman.getCenterX() + pacman.getRadius();
        //System.out.print(pacmanRightEdge + "Right Edge\n");
        //System.out.print(pacmanLeftEdge + "Left Edge\n");

        if (pacmanLeftEdge > 1225){
            //System.out.print("Outside Map\n");
            pacman.setCenterX(0);
        }
        if (pacmanRightEdge < 0){
            //System.out.print("Outside Map\n");
            pacman.setCenterX(1225);
        }
    }

    /**
     * Checks if Ghost is outside map through openings and is teleported to the opposite side of the map
     * @author Justin Ng
     *
     * @param ghosts - ghosts to be passed through
     * @param gameManager - game manager instance passed through
     */
    public void checkGhostOutsideMap(Set<Ghost> ghosts, GameManager gameManager) {
        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            if (ghostLeftEdge > 1225){
                ghost.setX(0);
            }
            if (ghostRightEdge < 0){
                ghost.setX(1225);
            }
        }
    }

    /**
     * Checks if the Pacman touches cookies.
     * @param pacman - pacman to be passed through
     * @param axis
     */
    public void checkCookieCoalition(Pacman pacman, String axis, Set<Cookie> cookieSet, GameManager gameManager) {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
        for (Cookie cookie:cookieSet) {
            double cookieCenterX = cookie.getCenterX();
            double cookieCenterY = cookie.getCenterY();
            double cookieLeftEdge = cookieCenterX - cookie.getRadius();
            double cookieRightEdge = cookieCenterX + cookie.getRadius();
            double cookieTopEdge = cookieCenterY - cookie.getRadius();
            double cookieBottomEdge = cookieCenterY + cookie.getRadius();
            if (axis.equals("x")) {
                // pacman goes right
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        gameManager.incrScore(cookie.getValue());
                        gameManager.incrCookiesEaten();
                    }
                    cookie.hide();
                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        gameManager.incrScore(cookie.getValue());
                        gameManager.incrCookiesEaten();
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
                    if (cookie.isVisible()) {
                        gameManager.incrScore(cookie.getValue());
                        gameManager.incrCookiesEaten();
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
                    if (cookie.isVisible()) {
                        gameManager.incrScore(cookie.getValue());
                        gameManager.incrCookiesEaten();
                    }
                    cookie.hide();
                }
            }
            gameManager.getScoreBoard().scoreLabel.setText("Score: " + gameManager.getScore());
            if (gameManager.getCookiesEaten() == gameManager.getCookieSet().size()) {
                gameManager.endGame();
            }
        }
    }
}
