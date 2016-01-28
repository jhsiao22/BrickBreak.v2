package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import java.awt.Color;

/**
 * Paddle Class stores all relevant information about a Paddle: dx, dy, color, and the GRect object itself.
 * It has a constructor and getters and setters for all of the instance variables. As for the coordinates of
 * where the Paddle is at any given moment, I am storing those values inherently in GObject's x and y. A similar
 * thing is true of width and height.
 */
public class Paddle extends GCompound
{
    private double dx;
    private double dy;
    private Color color;
    private GRect paddle;

    /**
     * Paddle constructor
     * @param x upper left corner x coordinate of the paddle
     * @param y upper left corner y coordinate of the paddle
     * @param width width of the paddle
     * @param height height of the paddle
     * @param dx how much to move in the x direction
     * @param dy how much to move in the y direction
     * @param color color of the paddle
     */
    public Paddle(double x, double y, double width, double height, double dx, double dy, Color color)
    {
        setLocation(x, y);
        this.dx = dx;
        this.dy = dy;
        this.color = color;

        paddle = new GRect(0, 0, width, height);
        paddle.setFilled(true);
        paddle.setFillColor(color);
        paddle.setColor(color);
        add(paddle);
    }

    //Getters and setters for dx, dy, color, and paddle
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GRect getPaddle() {
        return paddle;
    }

    public void setPaddle(GRect paddle) {
        this.paddle = paddle;
    }

    /**
     * toString for Paddle. It just prints out where the paddle is on the screen.
     * @return String representing the paddle's location
     */
    @Override
    public String toString()
    {
        return "Paddle's position is: " + getX() + ", " + getY();
    }
}
