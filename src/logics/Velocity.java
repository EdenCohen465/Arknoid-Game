// ID: 318758778
package logics;

import objects.Point;

/**
 * @author Eden Cohen
 * logics.Velocity specifies the change in position on the 'x' and the 'y' axes.
 */
public class Velocity {

    // Fields:
    private final double dx;
    private final double dy;

    /**
     * Constructor.
     * @param dx -the changes in the x coordinate.
     * @param dy -the changes in the y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Default Constructor.
     */
    public Velocity() {
        this.dx = 1;
        this.dy = 1;
    }

    /**
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - the original point.
     * @return new point - after changing the coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /** Convert for angle an speed to dx and dy - velocity.
     * @param angle between dy and the speed.
     * @param speed - the hypotenuse in a right-angled triangle (with dx and dy).
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // Convert the angle to radians in order to calculate cos ans sin.
        double radAngle = Math.toRadians(angle);
        double dx = speed * (Math.sin(radAngle));
        double dy = speed *  -(Math.cos(radAngle));

        // Create and return the new velocity.
        return new Velocity(dx, dy);
    }
}