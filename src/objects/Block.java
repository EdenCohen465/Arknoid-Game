// ID: 318758778
package objects;

import biuoop.DrawSurface;
import listener.HitListener;
import listener.HitNotifier;
import logics.Collidable;
import game.GameLevel;
import logics.Sprite;
import logics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eden Cohen
 * Class Objects.Block create an collidable object.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Constants:
    private static final double EPSILON = Math.pow(10, -15);

    // Members:
    private final Rectangle shape;
    private final Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param color of the block.
     * @param shape of the Objects.Block.
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor.
     * @param color of the block.
     * @param upperLeft point of the rectangle.
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the shape of the block.
     */
    public Rectangle getShape() {
        return this.shape;
    }


    /**
     * @return "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Check if two doubles are equals.
     *
     * @param x first double.
     * @param y second double.
     * @return true if equals and false if not.
     */
    private boolean isCoordinateEqual(double x, double y) {
        return Math.abs(x - y) <= EPSILON;
    }

    /**
     * Return the new velocity depend of where the collision point is.
     *
     * @param collisionPoint  of the object with the block.
     * @param currentVelocity of the object.
     * @param hitter ball.
     * @return new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /* If the there was collision with the block - the collisionPoint has a familiar coordinate depend of the
        collision location - lets divide to cases:
        1. Top- the y coordinate of the collisionPoint is the same of the upperLeft point of the shape.
        2. Bottom - the y coordinate of the collisionPoint is the same of the LowerRight point of the shape.
        3. Left- the x coordinate of the collisionPoint is the same of the upperLeft point of the shape.
        4. Right - the x coordinate of the collisionPoint is the same of the LowerRight point of the shape.
        5. Else - no collision. */
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // If the hit is in the top or the bottom of the block.
        if (isCoordinateEqual(y, this.shape.getUpperLeft().getY())
                || isCoordinateEqual(y, this.shape.getLowerRight().getY())) {

            // Notify the ball and the block that there was a hit.
            this.notifyHit(hitter);
            hitter.notifyHit(this);

            // Change the velocity
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }
        // If the hit is in the left or the right of the block.
        if (isCoordinateEqual(x, this.shape.getUpperLeft().getX())
                || isCoordinateEqual(x, this.shape.getLowerRight().getX())) {

            // Notify the ball and the block that there was a hit.
            this.notifyHit(hitter);
            hitter.notifyHit(this);

            // Change the velocity
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }

        // no hit - same velocity
        return currentVelocity;
    }

    @Override
    public boolean isBallInCollidable(Ball b) {

        // Check if the ball is in the block.
        if (b.getLocation().getY() < this.shape.getUpperLeft().getY()
                && b.getLocation().getY() > this.shape.getLowerRight().getY()
                && b.getLocation().getX() > this.shape.getUpperLeft().getX()
                && b.getLocation().getX() < this.shape.getLowerRight().getX()) {
            return true;
        }
        return false;
    }

    /**
     * Draw the block on the given surface.
     *
     * @param surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());

    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the block to the given game.
     * @param game to add the ball to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Remove the block from the game.
     * @param game to re ove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * will be called whenever a hit() occurs, notify all of the registered listener.HitListener objects by calling
     * their hitEvent method.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

