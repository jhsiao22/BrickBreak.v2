package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import java.awt.Color;

/**
 * Ball Class stores all the relevant information about a ball: dx, dy, color, whether or not it is moving,
 * whether or not it is touching a brick, and the GOval itself. There is a constructor, a bounce method, a
 * touchingBrick method to test to see if the ball is
 * As for the x, y coordinates and the radius/
 * width/height of the ball, that is stored in GObject's default place.
 */
public class Ball extends GCompound
{
    private double dx;
    private double dy;
    private Color color;
    private boolean isMoving;
    private GOval ball;

    /**
     * Ball constructor
     * @param x upper left corner x coordinate of the ball
     * @param y upper left corner y coordinate of the ball
     * @param width width of the ball
     * @param height height of the ball
     * @param dx how much the ball will move in the x direction
     * @param dy how much the ball will move in the y direction
     * @param color color of the ball
     * @param isMoving boolean representing whether or not the ball is moving
     */
    public Ball(double x, double y, double width, double height, double dx, double dy, Color color, boolean isMoving)
    {
        setLocation(x, y);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.isMoving = isMoving;

        ball = new GOval(0, 0, width, height);
        ball.setFillColor(color);
        ball.setFilled(true);
        ball.setColor(color);
        add(ball);
    }

    /**
     * bounce changes the ball's velocity to make it seem like it is bouncing off of the walls.
     * It will bounce off the the top, left, and right walls. It doesn't need to bounce off of
     * the bottom wall.
     * @param xLeftWall x coordinate of the left side of the window
     * @param xRightWall x coordinate of the right side of the window
     * @param yTopWall y coordinate of the top of the window
     */
    public void bounce(double xLeftWall, double xRightWall, double yTopWall)
    {
        if ((getX() - getWidth() <= xLeftWall && dx < 0) || (getX() + getWidth() >= xRightWall && dx > 0))
        {
            dx = -dx;
        }
        if ((getY() - getHeight() <= yTopWall && dy < 0))
        {
            dy = -dy;
        }
    }

    /**
     * needNewBall figures out if the game needs a new ball
     * @param yBottomWall y coordinate of the lower wall boundary
     * @return boolean representing whether or not we need a new ball
     */
    public boolean needNewBall(double yBottomWall)
    {
        boolean needNewBall = false;
        if (getY() + getHeight() >= yBottomWall && dy > 0)
        {
            needNewBall = true;
        }
        return needNewBall;
    }

    //Getters and setters for dx, dy, color, isMoving, and ball
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

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public GOval getBall() {
        return ball;
    }

    public void setBall(GOval ball) {
        this.ball = ball;
    }

    /**
     * toString for Ball. It just prints out where the ball is on the screen.
     * @return String representing the ball's location
     */
    @Override
    public String toString()
    {
        return "Ball's position is: " + getX() + ", " + getY();
    }
}
