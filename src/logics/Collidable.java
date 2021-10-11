// ID: 318758778
package logics;

import objects.Ball;
import objects.Point;
import objects.Rectangle;

/**
 * @author Eden Cohen
 * things that can be collided with.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint of the object with the collidable.
     * @param currentVelocity of the object before the hit.
     * @param hitter the ball that hit.
     * @return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Check if the ball get inside some collidable.
     * @param b ball.
     * @return true if the ball in the collidable and false if not.
     */
    boolean isBallInCollidable(Ball b);
}