// ID: 318758778
package objects;

import biuoop.DrawSurface;
import listener.HitListener;
import listener.HitNotifier;
import logics.GameEnvironment;
import logics.Sprite;
import logics.Velocity;
import game.GameLevel;
import logics.CollisionInfo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Objects.Ball creates a ball with location, color, velocity and size.
 *
 * @author Eden Cohen
 */
public class Ball implements Sprite, HitNotifier {

    private static final int HEIGHT = 600;

    // Fields:
    private int size;
    private final Color color;
    private Point location;
    private Velocity velocity;
    private GameEnvironment env;
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructor1.
     *
     * @param center - the location of the ball.
     * @param r      - the radius (size).
     */
    public Ball(Point center, int r) {
        this.location = center;
        this.size = r;
        this.color = Color.WHITE;
        this.env = new GameEnvironment();
        this.hitListeners = new ArrayList<>();

        // Default velocity until changes.
        this.velocity = new Velocity();
    }

    /**
     * Constructor2.
     *
     * @param x     - of the location of the ball.
     * @param y     - of the location of the ball.
     * @param r     - the radius (size).
     * @param color - color of the ball.
     */
    public Ball(double x, double y, int r, Color color) {
        this.location = new Point(x, y);
        this.size = r;
        this.color = color;
        this.env = new GameEnvironment();
        this.hitListeners = new ArrayList<>();

        // Default velocity until changes.
        this.velocity = new Velocity();
    }

    /**
     * x coordinate of the location point.
     *
     * @return x
     */
    public int getX() {

        // Casting the location point from double to int.
        return (int) Math.round(this.location.getX());
    }

    /**
     * y coordinate of the location point.
     *
     * @return y
     */
    public int getY() {

        // Casting the location point from double to int.
        return (int) Math.round(this.location.getY());
    }

    /**
     * @return the size of the ball - radius.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Change the size.
     *
     * @param newSize of the ball
     */
    private void setSize(int newSize) {
        this.size = newSize;
    }

    /**
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set the velocity of the ball.
     *
     * @param dx coordinate of the velocity.
     * @param dy coordinate of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return the location of the ball.
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Move one step depend on the velocity and the obstacles.
     */
    public void moveOneStep() {

        // compute the ball trajectory
        Line trajectory = new Line(this.location, new Point(this.location.getX() + this.velocity.getDx(),
                this.location.getY() + this.velocity.getDy()));

        // Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo info = this.env.getClosestCollision(trajectory);

        // If there is going to be a hit.
        if (info != null) {

            // Move the ball to "almost" the hit point.
            this.location = new Point(info.collisionPoint().getX() - this.velocity.getDx(),
                    info.collisionPoint().getY() - this.velocity.getDy());

            // Update the velocity to the new velocity returned by the hit() method.
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
        }

        // Move the ball to the end of the trajectory.
        this.location = this.getVelocity().applyToPoint(this.location);
    }

    /**
     * Set the game environment with the given one.
     *
     * @param gameEnv - game environment of the ball.
     */
    public void setEnv(GameEnvironment gameEnv) {
        this.env = gameEnv;
    }

    /**
     * Add this ball to the game.
     * @param game to add the ball to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Will be called whenever a hit() occurs, notify all of the registered listener.HitListener objects by
     * calling their hitEvent method.
     * @param hit the block that the ball hit.
     */
    public void notifyHit(Block hit) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {

            // If the hit is in the bottom of the screen- remove the ball, if there is other- do nothing.
            if (hit.getShape().getUpperLeft().getY() == HEIGHT - 1) {
                hl.hitEvent(hit, this);
            }
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

    /**
     * @param game to remove the ball from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

