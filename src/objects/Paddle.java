// ID: 318758778

package objects;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import logics.Collidable;
import game.GameLevel;
import logics.Sprite;
import logics.Velocity;

import java.awt.Color;

/**
 * @author Eden Cohen
 * The Objects.Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {

    // Constants:
    private static final int WIDTH = 800;
    private static final double EPSILON = Math.pow(10, -15);
    private static final int REGION = 5;
    private static final int BORDERS_WIDTH = 15;

    // Members.
    private KeyboardSensor keyboard;
    private final Rectangle shape;
    private final Color color;
    private int speed;

    /**
     * Constructor.
     * @param rec shape of the Objects.Paddle.
     * @param keyboard from the user.
     * @param speed of the paddle.
     */
    public Paddle(Rectangle rec, KeyboardSensor keyboard, int speed) {
        this.shape = rec;
        this.keyboard = keyboard;
        this.color = Color.ORANGE;
        this.speed = speed;
    }

    /**
     * Move Left.
     */
    public void moveLeft() {

        // Check if the paddle can move more left- if yes- move him.
        if (this.shape.getUpperLeft().getX() > BORDERS_WIDTH) {
            this.shape.moveUpperLeft(this.shape.getUpperLeft().getX() - this.speed);
        }

        // Check if the paddle not is not in the borders of the game.
        if (this.shape.getUpperLeft().getX() < BORDERS_WIDTH) {
            this.shape.moveUpperLeft(BORDERS_WIDTH);
        }
    }

    /**
     * Move Right.
     */
    public void moveRight() {

        // Check if the paddle can move more right- if yes- move him.
        if (this.shape.getLowerRight().getX() < WIDTH - BORDERS_WIDTH) {
            this.shape.moveUpperLeft(this.shape.getUpperLeft().getX() + this.speed);
        }

        // Check if the paddle not is not in the borders of the game.
        if (this.shape.getLowerRight().getX() > WIDTH - BORDERS_WIDTH) {
            this.shape.moveUpperLeft(WIDTH - BORDERS_WIDTH - this.shape.getWidth());
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    /**
     * Check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Check if two doubles are equals.
     * @param x first double.
     * @param y second double.
     * @return true if equals and false if not.
     */
    private boolean isCoordinateEqual(double x, double y) {
        return Math.abs(x - y) <= EPSILON;
    }

    /**
     * @param collisionPoint of the object with the collidable.
     * @param currentVelocity of the object before the hit.
     * @param hitter the ball that hit the paddle.
     * @return new velocity depand of the collision position.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double ballSpeed = Math.sqrt(dx * dx + dy * dy);

        // If the ball hits in the top of the block- divide to 5 regions.
        if (isCoordinateEqual(y, this.shape.getUpperLeft().getY())) {
            if (x < this.shape.getUpperLeft().getX() + (this.shape.getWidth() / REGION)) { // Part 1
                return Velocity.fromAngleAndSpeed(300, ballSpeed);
            } else if (x < this.shape.getUpperLeft().getX() + 2 * (this.shape.getWidth() / REGION)) { // Part 2
                return Velocity.fromAngleAndSpeed(330, ballSpeed);
            } else if (x < this.shape.getUpperLeft().getX() + 3 * (this.shape.getWidth() / REGION)) { // Part 3-middle.
                return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            } else if (x < this.shape.getUpperLeft().getX() + 4 * (this.shape.getWidth() / REGION)) { // Part 4
                return Velocity.fromAngleAndSpeed(30, ballSpeed);
            } else if (x <  this.shape.getUpperLeft().getX() + this.shape.getWidth()) { // Part 5
                return Velocity.fromAngleAndSpeed(60, ballSpeed);
            }
        }

        // If the hit is in the left or the right of the paddle.
        if (isCoordinateEqual(x, this.shape.getUpperLeft().getX())
                || isCoordinateEqual(x, this.shape.getLowerRight().getX())) {
            return new Velocity((-1) * currentVelocity.getDx(),  currentVelocity.getDy());
        }

        // no hit - same velocity
        return currentVelocity;
    }

    @Override
    public boolean isBallInCollidable(Ball b) {

        // Check if the ball is in the paddle.
        if (b.getLocation().getY() < this.shape.getUpperLeft().getY()
                && b.getLocation().getY() > this.shape.getLowerRight().getY()) {
            return b.getLocation().getX() > this.shape.getUpperLeft().getX()
                    && b.getLocation().getX() < this.shape.getLowerRight().getX();
        }
        return false;
    }

    /**
     * Add this paddle to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}