// ID: 318758778
package objects;
/**
 * The Objects.Point class create new point that consists of x, y coordinates.
 * The class has several methods that give information about the point like distance from another point, if the point
 * equals to another point etc.
 * @author      Eden Cohen
 */

public class Point {

    // Constant:
    private static final double EPSILON = Math.pow(10, -2);

    // Fields:
    private final double x;
    private final double y;

    /**
     * Constructor.
     * @param x - the x coordinate.
     * @param y - the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     * @param other - the other point we want to calculate the distance with.
     * @return the distance.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * return true is the points are equal, false otherwise.
     * @param other - the other point we want to check if equal.
     * @return true- if the points equal. false- if different.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.getX()) <= EPSILON && Math.abs(this.y - other.getY()) <= EPSILON);
    }

    /**
     * Return the x value of this point.
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the x value of this point.
     * @return x coordinate
     */
    public double getY() {
        return this.y;
    }
}