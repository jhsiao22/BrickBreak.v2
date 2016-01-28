package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import java.awt.Color;

/**
 * Brick Class stores all relevant information about a Brick: whether or not it is alive, what color
 * it is, and the GRect itself. It has a constructor and getters and setters for the variables
 * listed above. The other important things about a brick like its x and y coordinates
 * and the width and height are stored in GObject's default space.
 */
public class Brick extends GCompound
{
    private Color color;
    private GRect brick;

    /**
     * Brick constructor
     * @param x upper left corner x coordinate of the brick
     * @param y upper left corner y coordinate of the brick
     * @param width width of the brick
     * @param height height of the brick
     * @param color color of the brick
     */
    public Brick(double x, double y, double width, double height, Color color)
    {
        setLocation(x , y);
        this.color = color;

        brick = new GRect(0, 0, width, height);
        brick.setFilled(true);
        brick.setFillColor(color);
        brick.setColor(Color.WHITE);
        add(brick);
    }

    //Getters and setters for color and brick
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GRect getBrick() {
        return brick;
    }

    public void setBrick(GRect brick) {
        this.brick = brick;
    }

    /**
     * toString for Brick. It just prints out where the brick is on the screen.
     * @return String representing the brick's location
     */
    @Override
    public String toString()
    {
        return "Brick's position is: " + getX() + ", " + getY();
    }
}
