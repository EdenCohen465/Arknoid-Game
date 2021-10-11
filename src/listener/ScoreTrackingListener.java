// ID: 318758778
package listener;

import objects.Ball;
import objects.Block;

/**
 * @author Eden Cohen
 * Keeping track of the player score.
 */
public class ScoreTrackingListener implements HitListener {

    // Constant
    private static final int BLOCK_HIT_SCORE = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter player counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Add to the score when there was hit.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BLOCK_HIT_SCORE);
    }

    /**
     * Getter.
     * @return current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}