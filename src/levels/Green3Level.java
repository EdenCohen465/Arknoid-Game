package levels;

import levels.backgrounds.Green3Background;
import logics.Sprite;
import logics.Velocity;
import objects.Ball;
import objects.Block;
import objects.Point;
import objects.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eden Cohen
 * Level 3.
 */
public class Green3Level implements LevelInformation {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 5;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(new Velocity(-4, -4));
        list.add(new Velocity(4, -4));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600),
                new Color(1, 43, 1));
    }

    @Override
    public List<Block> blocks() {

        // Create the list of balls.
        ArrayList<Block> blockArrayList = new ArrayList<>(this.numberOfBlocksToRemove());

        // Variables:
        int blockWidth = 70, blockHeight = 30, amountOfBlocks = 9, rows = 6;
        double rightStartX = WIDTH - blockWidth - 15;
        double rightStartY = 150;

        // Array of colors for the blocks
        Color[] colors = {new Color(153, 0, 0), new Color(255, 102, 0),
                new Color(255, 204, 0), new Color(102, 255, 102),
                new Color(51, 153, 255), new Color(110, 0, 250)};

        // Create colorful blocks and add them to the game.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < amountOfBlocks - i; j++) {
                blockArrayList.add(new Block(new Rectangle(new Point(rightStartX - blockWidth * j,
                        rightStartY + blockHeight * i), blockWidth, blockHeight), colors[i]));
            }
        }
        return blockArrayList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 39;
    }

    @Override
    public Point paddleLocation() {
        return new Point(WIDTH / (double) 2, HEIGHT - PADDLE_HEIGHT);
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> ballArrayList = new ArrayList<>(this.numberOfBalls());
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballArrayList.add(new Ball(new Point(this.paddleLocation().getX() + (this.paddleWidth() / (double) 2),
                    this.paddleLocation().getY() - 20), BALL_RADIUS));
        }
        return ballArrayList;
    }

    @Override
    public Sprite getDrawBackground() {
        return new Green3Background();
    }
}

