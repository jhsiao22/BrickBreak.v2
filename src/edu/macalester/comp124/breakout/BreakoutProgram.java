package edu.macalester.comp124.breakout;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram
{
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 800;

    private final int X_COORD_OF_BRICK_START = 0;
    private final int Y_COORD_OF_BRICK_START = 30;
    private final int BRICK_WIDTH = 50;
    private final int BRICK_HEIGHT = 20;
    private final int NUM_ROWS = 10;
    private final int NUM_COLS = WINDOW_WIDTH/BRICK_WIDTH;

    private int numBricksRemoved = 0;
    private int numLivesLost = 0;
    private boolean WIN_GAME = false;
    private final double NUM_LIVES = 3;

    private final double X_COORD_OF_PADDLE = WINDOW_WIDTH/2;
    private final double Y_COORD_OF_PADDLE = WINDOW_HEIGHT - 100;

    private Ball ball;
    private Paddle paddle;
    private Brick[][] brickArray = new Brick[NUM_ROWS][NUM_COLS];

    private RandomGenerator random = new RandomGenerator();

    /**
     * init sets the size of the window, adds key listeners, and initializes the
     * ball, bricks, and paddle objects needed for the game. It also adds the bricks
     * to the screen.
     */
    public void init()
    {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        addKeyListeners();
        initializeBall();
        initializePaddle();
        initializeBricks();
        addBricksToScreen();
    }

    /**
     * run runs the game until the user either wins the game by destroying
     * all the bricks or loses the game by losing 3 lives.
     */
    public void run()
    {
        while (!WIN_GAME && numLivesLost < NUM_LIVES)
        {
            displayLives();
            animateLoop();
            if (ball.needNewBall(Y_COORD_OF_PADDLE))
            {
                numLivesLost++;
                remove(ball);
                initializeBall();
            }
            if (numBricksRemoved == NUM_ROWS*NUM_COLS)
            {
                WIN_GAME = true;
            }
        }
        if (WIN_GAME)
        {
            displayYouWinMessage();
        }
        else
        {
            displayGameOverMessage();
            displayLives();
        }
    }

    /**
     * initializeBall initializes the a ball and adds it to the screen. It also sets the ball to
     * have a randomly generated speed.
     */
    public void initializeBall()
    {
        double dx = random.nextDouble(5.0, 20.0);
        double dy = random.nextDouble(5.0, 20.0);
        ball = new Ball(WINDOW_WIDTH/2.0, WINDOW_HEIGHT/2.0, 10, 10, dx, dy, Color.RED, true);
        add(ball);
    }

    /**
     * initializePaddle initializes a paddle and adds it to the screen
     */
    public void initializePaddle()
    {
        paddle = new Paddle(X_COORD_OF_PADDLE, Y_COORD_OF_PADDLE, 100, 10, 10, 0, Color.BLACK);
        add(paddle, paddle.getX() - paddle.getWidth()/2, paddle.getY());
    }

    /**
     * initializeBricks creates and initializes Bricks and sticks them into brickArrays
     */
    public void initializeBricks()
    {
        for (int row = 0; row < NUM_ROWS; row++)
        {
            int y = Y_COORD_OF_BRICK_START + row * BRICK_HEIGHT;
            for (int col = 0; col < NUM_COLS; col++)
            {
                int x = X_COORD_OF_BRICK_START + col * BRICK_WIDTH;
                if (row == 0 || row == 1)
                {
                    brickArray[row][col] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.RED);
                }
                else if (row == 2 || row == 3)
                {
                    brickArray[row][col] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.ORANGE);
                }
                else if (row == 4 || row == 5)
                {
                    brickArray[row][col] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.YELLOW);
                }
                else if (row == 6 || row == 7)
                {
                    brickArray[row][col] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.GREEN);
                }
                else if (row == 8 || row == 9)
                {
                    brickArray[row][col] = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.CYAN);
                }
            }
        }
    }

    /**
     * addBricksToScreen adds all of the Bricks in the brickArrays to the screen
     */
    public void addBricksToScreen()
    {
        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int col = 0; col < NUM_COLS; col++)
            {
                add(brickArray[row][col]);
            }
        }
    }

    /**
     * animateLoop moves the ball, pauses the screen, and makes sure that the ball
     * bounces off of the paddle and bricks if needed. Additionally, it'll get rid
     * of the bricks once they have been hit.
     */
    public void animateLoop()
    {
        if (ball.isMoving())
        {
            ball.move(ball.getDx(), ball.getDy());
            pause(60);
            ball.bounce(0, WINDOW_WIDTH, 0);
            bounceOffPaddle();
            bounceOffBrick();
        }
    }

    /**
     * displayLives displays the number of lives left the user has
     */
    public void displayLives()
    {
        GLabel lives = new GLabel("lives left: " + (int)(NUM_LIVES - numLivesLost));
        GRect fillerRect = new GRect(200, 30);
        fillerRect.setFillColor(Color.WHITE);
        fillerRect.setFilled(true);
        fillerRect.setColor(Color.WHITE);
        add(fillerRect, 0, WINDOW_HEIGHT - 50 - fillerRect.getHeight());
        lives.setFont(new Font("Arial", Font.BOLD, 20));
        add(lives, 0, WINDOW_HEIGHT - 50);
    }

    /**
     * displayYouWinMessage displays a game over message when the user loses.
     */
    public void displayYouWinMessage()
    {
        GLabel youLose = new GLabel("You win! :P");
        youLose.setFont(new Font("Arial", Font.BOLD, 70));
        add(youLose, WINDOW_WIDTH / 2 - 200, WINDOW_HEIGHT / 2);
    }

    /**
     * displayGameOverMessage displays a game over message when the user loses.
     */
    public void displayGameOverMessage()
    {
        GLabel youLose = new GLabel("Game over! :(");
        youLose.setFont(new Font("Arial", Font.BOLD, 70));
        add(youLose, WINDOW_WIDTH / 2 - 220, WINDOW_HEIGHT / 2);
    }

    /**
     * keyPressed moves the paddle according to keyboard input.
     * @param e KeyEvent representing keyboard input from the user
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (ball.isMoving())
        {
            if (keyCode == KeyEvent.VK_LEFT)
            {
                movePaddleLeft();
            }
            else if (keyCode == KeyEvent.VK_RIGHT)
            {
                movePaddleRight();
            }
        }
    }

    /**
     * movePaddleLeft moves the paddle to the left and makes sure the paddle
     * doesn't go off the screen on the left side.
     */
    private void movePaddleLeft()
    {
        if (paddle.getX() - paddle.getDx() < 0)
        {
            paddle.setLocation(0, Y_COORD_OF_PADDLE);
        }
        else
        {
            paddle.move(-paddle.getDx(), paddle.getDy());
        }
    }

    /**
     * movePaddleRight moves the paddle to the right and makes sure the paddle
     * doesn't go off of the screen on the right side.
     */
    private void movePaddleRight()
    {
        if (paddle.getX() + paddle.getDx() + paddle.getWidth() > WINDOW_WIDTH)
        {
            paddle.setLocation(WINDOW_WIDTH - paddle.getWidth(), Y_COORD_OF_PADDLE);
        }
        else
        {
            paddle.move(paddle.getDx(), paddle.getDy());
        }
    }

    /**
     * bounceOffPaddle makes sure that the ball bounces off of the paddle if it comes across
     * the paddle at the bottom of the screen. It only tests the dy cases because I didn't
     * want to deal with ball bouncing off of the side of the paddle. Also, I don't think
     * that case happens very often and also wanted to simplify the game slightly.
     */
    public void bounceOffPaddle()
    {
        if (getElementAt(ball.getX(), ball.getY() + ball.getHeight()) instanceof Paddle)
        {
            ball.setDy(-ball.getDy());
            ball.setLocation(ball.getX(), Y_COORD_OF_PADDLE - ball.getHeight());
        }
        else if (getElementAt(ball.getX(), ball.getY() - ball.getHeight()) instanceof Paddle)
        {
            ball.setDy(-ball.getDy());
            ball.setLocation(ball.getX(), Y_COORD_OF_PADDLE - ball.getHeight());
        }
    }

    /**
     * bounceOffBrick makes sure that the ball bounces off of a brick if comes across one.
     * Additionally, it calls another method to remove the brick from the screen and sets
     * the status of the brick to false.
     */
    public void bounceOffBrick()
    {
        if (getElementAt(ball.getX() + ball.getWidth(), ball.getY()) instanceof Brick)
        {
            ball.setDx(-ball.getDx());
            removeBrickFromScreenAndSetBrickToNotBeAlive(ball.getX() + ball.getWidth(), ball.getY());
        }
        else if (getElementAt(ball.getX() - ball.getWidth(), ball.getY()) instanceof Brick)
        {
            ball.setDx(-ball.getDx());
            removeBrickFromScreenAndSetBrickToNotBeAlive(ball.getX() - ball.getWidth(), ball.getY());
        }
        else if (getElementAt(ball.getX(), ball.getY() + ball.getHeight()) instanceof Brick)
        {
            ball.setDy(-ball.getDy());
            removeBrickFromScreenAndSetBrickToNotBeAlive(ball.getX(), ball.getY() + ball.getHeight());
        }
        else if (getElementAt(ball.getX(), ball.getY() - ball.getHeight()) instanceof Brick)
        {
            ball.setDy(-ball.getDy());
            removeBrickFromScreenAndSetBrickToNotBeAlive(ball.getX(), ball.getY() - ball.getHeight());
        }
    }

    /**
     * removeBrickFromScreenAndSetBrickToNotBeAlive removes the brick from the screen
     * and also increases the number of bricks removed counter up by one.
     * @param x x coordinate somewhere on or inside the brick
     * @param y y coordinate somewhere on or inside the brick
     */
    private void removeBrickFromScreenAndSetBrickToNotBeAlive(double x, double y)
    {
        Brick brickToRemove = (Brick)getElementAt(x, y);
        remove(brickToRemove);
        numBricksRemoved++;
    }
}