// ID: 318758778
package logics;

import objects.Point;

/**
 * @author Eden Cohen
 * informaion about the collision- point and object.
 */
public class CollisionInfo {

    // Members:
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor.
     * @param collision point.
     * @param collisionObject that the collision happened with.
     */
    public CollisionInfo(Point collision, Collidable collisionObject) {
        this.collisionPoint = collision;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}