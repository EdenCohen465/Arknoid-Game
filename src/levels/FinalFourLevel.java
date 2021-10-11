package levels;

import levels.backgrounds.FinalFourBackground;
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
 * @author Eden Cohen.
 * Level 4
 */
public class FinalFourLevel implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 7;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(new Velocity(-4, -4));
        list.add(new Velocity(4, -4));
        list.add(new Velocity(-4, 4));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(180, 135, 255));
    }

    @Override
    public List<Block> blocks() {

        // Create the list of balls.
        ArrayList<Block> blockArrayList = new ArrayList<>(this.numberOfBlocksToRemove());

        // Variables:
        int blockWidth = 55, blockHeight = 25, amountOfBlocks = 14, rows = 7;
        double rightStartX = WIDTH - blockWidth - 15;
        double rightStartY = 100;

        // Array of colors for the blocks
        Color[] colors = {new Color(69, 3, 46), new Color(116, 3, 86), new Color(149, 2, 134),
                new Color(229, 2, 225), new Color(246, 110, 217),
                new Color(249, 170, 250), new Color(241, 207, 235) };

        // Create colorful blocks and add them to the game.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < amountOfBlocks; j++) {
                blockArrayList.add(new Block(new Rectangle(new Point(rightStartX - blockWidth * j,
                        rightStartY + blockHeight * i), blockWidth, blockHeight), colors[i]));
            }
        }
        return blockArrayList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 98;
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
                    this.paddleLocation().getY() - 25), BALL_RADIUS));
        }
        return ballArrayList;
    }

    @Override
    public Sprite getDrawBackground() {
        return new FinalFourBackground();
    }
}
