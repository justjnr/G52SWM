package Ng_Justin_4303735.Board;

import Ng_Justin_4303735.Managers.SettingsManager;
import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

public class Maze {

    public Set<BarObstacle> obstacles;
    public SettingsManager settingsManager;
    public Maze(SettingsManager a) {
        obstacles = new HashSet<>();
        settingsManager = a;
    }

    /**
     * Checks if point is touching obstacles
     *
     * @param x - x position of obstacle
     * @param y - y position of obstacle
     * @param padding - padding of the entity passed through
     * @return - returns boolean as to whether its touching
     */
    public Boolean isTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:obstacles) {
            if (
                x >= barObstacle.getX() - padding &&
                x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                y >= barObstacle.getY() - padding &&
                y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * lets you know if there's an obstacle in the current coordinate
     *
     * @param fromX - previous X value
     * @param toX - next X value
     * @param fromY - previous Y value
     * @param toY - next Y value
     * @return - returns boolean as to whether its touching
     */
    public Boolean hasObstacle(double fromX,  double toX, double fromY, double toY) {
        boolean isTouching = false;
        for (double i = fromX; i < toX; i++) {
            for (double j = fromY; j < toY; j++) {
                if (this.isTouching(i, j, 0)) isTouching = true;
            }
        }
        return isTouching;
    }

    /**
     * Draws the maze
     *
     * @param root - current group to add all obstacles to
     */
    public void CreateMaze(Group root) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // top
        this.obstacles.add(new BarObstacle(0, 0, "horizontal", 48, settingsManager));
        // bottom
        this.obstacles.add(new BarObstacle(0, 600, "horizontal", 48, settingsManager));
        // left
        this.obstacles.add(new BarObstacle(0, 0, "vertical", 11, settingsManager));
        this.obstacles.add(new BarObstacle(0, 350, "vertical", 10, settingsManager));
        this.obstacles.add(new BarObstacle(-25, 250, "horizontal", 2, settingsManager));
        this.obstacles.add(new BarObstacle(-25, 350, "horizontal", 2, settingsManager));
        // right
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 0, "vertical", 11, settingsManager));
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 350, "vertical", 11, settingsManager));
        this.obstacles.add(new BarObstacle(1225, 250, "horizontal", 2, settingsManager));
        this.obstacles.add(new BarObstacle(1225, 350, "horizontal", 2, settingsManager));

        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // obsTopLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4, settingsManager));
        // obsTopRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4, settingsManager));
        // obsBottomLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4, settingsManager));
        // obsBottomRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4, settingsManager));
        // obsTopMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 17, settingsManager));
        // obsBottomMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 17, settingsManager));
        // obsLMTop
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // obsLMTop4
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // obsLMBottom
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // obsRMTop
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // obsRMTop2
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // obsRMBottom
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // LobsLeftTop1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // LobsLeftTop2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6, settingsManager));
        // LobsLeftBottom1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // LobsLeftBottom2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6, settingsManager));
        // LobsRightTop1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // LobsRightTop2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6, settingsManager));
        // LobsRightBottom1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // LobsRightBottom2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6, settingsManager));
        // cageBottom
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 8 * BarObstacle.THICKNESS, "horizontal", 17, settingsManager));
        // cageRightV
        this.obstacles.add(new BarObstacle(32 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8, settingsManager));
        // cageLeftV
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8, settingsManager));
        // cateRightH
        this.obstacles.add(new BarObstacle(17 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));
        // cateLeftH
        this.obstacles.add(new BarObstacle(27 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5, settingsManager));

        root.getChildren().addAll(obstacles);
    }
}
