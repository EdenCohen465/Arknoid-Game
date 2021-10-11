package levels;

import levels.backgrounds.WideEasyBackground;
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
 * Level 2.
 */
public class WideEasyLevel implements LevelInformation {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 5;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        // Create new array of velocities
        ArrayList<Velocity> list = new ArrayList<>(this.numberOfBalls());
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            list.add(new Velocity(0, 5));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        Color[] colors = {new Color(153, 0, 0), new Color(255, 102, 0),
                new Color(255, 204, 0), new Color(102, 255, 102),
                new Color(51, 153, 255), new Color(110, 0, 250)};
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            list.add(new Block(new Rectangle(new Point((800 - 15 - 55) - i * 55, 200), 55, 20),
                    colors[i % 6]));
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }

    @Override
    public Point paddleLocation() {
        return new Point(WIDTH / (double) 6, HEIGHT - PADDLE_HEIGHT);
    }

    @Override
    public List<Ball> balls() {

        // Create new Array
        ArrayList<Ball> ballArrayList = new ArrayList<>(this.numberOfBalls());

        // Create the balls and add them to the array.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballArrayList.add(new Ball(new Point(HEIGHT / (double) 3 + i * 30, WIDTH / (double) 2), BALL_RADIUS));
        }
        return ballArrayList;
    }

    @Override
    public Sprite getDrawBackground() {
        return new WideEasyBackground();
    }
}
