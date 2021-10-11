// ID: 318758778
package listener;

/**
 * @author Eden Cohen
 * Object that notify when there is a hit - like Objects.Ball and Objects.Block.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl hitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl hitListener.
     */
    void removeHitListener(HitListener hl);
}