package levels;

import levels.backgrounds.DirectHitBackground;
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
 * First level in the game.
 */
public class DirectHitLevel implements LevelInformation {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 5;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<>();
        list.add(new Velocity(0, 5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(400, 200), 30, 30), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Point paddleLocation() {
        return new Point(WIDTH / (double) 2 - 25, HEIGHT - PADDLE_HEIGHT);
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> ballArrayList = new ArrayList<>(this.numberOfBalls());
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballArrayList.add(new Ball(new Point(paddleLocation().getX() + paddleWidth() / (double) 2, 500),
                    BALL_RADIUS));
        }
        return ballArrayList;
    }

    @Override
    public Sprite getDrawBackground() {
        return new DirectHitBackground();
    }
}