// ID: 318758778
package listener;

import objects.Ball;
import objects.Block;

/**
 * @author Eden Cohen
 * Object that should react when there was a hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit. The hitter parameter is the Objects.Ball that's
     * doing the hitting.
     * @param beingHit the block who is hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}