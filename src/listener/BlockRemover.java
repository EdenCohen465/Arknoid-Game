// ID: 318758778
package listener;

import objects.Ball;
import objects.Block;
import game.GameLevel;

/**
 * @author Eden Cohen.
 * a listener.BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number
 * of blocks that remain in the game.
 */
public class BlockRemover implements HitListener {

    // Members:
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     * @param game to remove the blocks from.
     * @param remainingBlocks to remove from the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit the block to remove.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        // Remove the ball from the game.
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        hitter.removeHitListener(this);

        // Decrease the amount of blocks that remained in the game.
        this.remainingBlocks.decrease(1);

    }
}