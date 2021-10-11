// ID: 318758778
package levels;

import logics.Sprite;
import logics.Velocity;
import objects.Ball;
import objects.Block;
import objects.Point;

import java.util.List;

/**
 * @author Eden Cohen
 */
public interface LevelInformation {

    /**
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /** the level name will be displayed at the top of the screen.
     * @return leve name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return the paddle location.
     */
    Point paddleLocation();

    /**
     * @return Arraylist of the balls that in the game.
     */
    List<Ball> balls();

    /**
     * @return the Drawing Background of the level.
     */
    Sprite getDrawBackground();
}