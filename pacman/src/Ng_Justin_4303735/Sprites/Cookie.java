package Ng_Justin_4303735.Sprites;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cookie extends Circle {
    private int value;

    /**
     * Constructor for this class to initialise cookie values such as
     * position, value and radius
     * @param x - x position value for cookie
     * @param y - y position value for cookie
     */
    public Cookie(double x, double y) {
        this.value = 5;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(12.5);
        this.setFill(Color.WHITE);
    }

    /**
     * Getter to return cookie value
     * @return - returns value as int
     */
    public int getValue() {
        return value;
    }

    /**
     * Method to hide the cookie
     * sets the visibility of the cookie to hidden
     */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * Method to show the cookie
     * sets the visibility of the cookie to visible
     */
    public void show() {
        this.setVisible(true);
    }

}