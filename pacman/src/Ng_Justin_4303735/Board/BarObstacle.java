package Ng_Justin_4303735.Board;

import Ng_Justin_4303735.Managers.SettingsManager;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarObstacle extends Rectangle {

    public static double THICKNESS = 25;
    /**
     * BarObstacle method generates and sets the bars for the maze with the parameters passed to it
     *
     * @param x - Position on the X axis
     * @param y - Position on the Y axis
     * @param orientation - horizontal or vertical
     * @param length - the length of the bar (1 == 100px)
     * @param settingsManager - instance of SettingsManager passed through
     */
    public BarObstacle(double x, double y, String orientation, double length, SettingsManager settingsManager) {
        this.setX(x);
        this.setY(y);
        if (orientation.equals("horizontal")) {
            this.setHeight(BarObstacle.THICKNESS);
            this.setWidth(length * BarObstacle.THICKNESS);
        } else {
            this.setHeight(length * BarObstacle.THICKNESS);
            this.setWidth(BarObstacle.THICKNESS);
        }
        String c = settingsManager.getfgCSetting();
        Color colourSetting = Color.web(c);
        this.setFill(colourSetting);

        //this.setIconImage(Toolkit.getDefaultToolkit().getImage("/ghost1.png"));
        this.setStrokeWidth(3);
    }
}
