// ID: 318758778
package listener;

import objects.Ball;
import objects.Block;
import game.GameLevel;

/**
 * @author Eden Cohen.
 * a listener BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number
 * of blocks that remain in the game.
 */
public class BallRemover implements HitListener {

    // Members:
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     * @param game to remove the blocks from.
     * @param remainingBalls to remove from the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Objects.Ball that hit the bottom of the screen should be removed from the game.
     * @param deathRegion the block to remove.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block deathRegion, Ball hitter) {

        // Remove the ball from the game.
        hitter.removeFromGame(this.game);
        deathRegion.removeHitListener(this);
        hitter.removeHitListener(this);

        // Decrease the amount of balls the remained in the game.
        this.remainingBalls.decrease(1);
    }
}