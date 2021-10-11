// ID: 318758778

package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eden Cohen
 * Class that create rectangle by one point and sizes of width and hieght.
 */
public class Rectangle {

    // Constants:
    private static final int NUM_SIDES = 4;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FORTH = 3;

    // Fields:
    private Point upperLeft;
    private Point lowerRight;
    private final double width;
    private final double height;

    /**
     * Constructor.
     * @param upperLeft point of the rectangle.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Return a (possibly empty) List of intersection point- with the specified line.
     * @param line to find the intersections point with.
     * @return a list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {

        double x = getUpperLeft().getX();
        double y = getUpperLeft().getY();

        // Define the vertexes of the rectangles.
        Point upperRight = new Point(x + getWidth(), y);
        Point lowerLeft = new Point(x, y + getHeight());

        // Create the lines of the rectangles and save it in array:
        Line[] sides = new Line[NUM_SIDES];
        sides[FIRST] = new Line(getUpperLeft(), upperRight);
        sides[SECOND] = new Line(lowerLeft, this.lowerRight);
        sides[THIRD] = new Line(getUpperLeft(), lowerLeft);
        sides[FORTH] = new Line(upperRight, this.lowerRight);

        /* In order to find the intersection points of the given line with the rectangles lines- call the method on
        the line and save the points in a list. */

        // Create list
        ArrayList<Point> interPoints = new ArrayList<>();

        // Loop through the array of lines in order to find intersection point with the given line.
        for (Line side : sides) {

            // Convert the line to int coordinate
            Line intLine = new Line((int) Math.round(line.start().getX()), (int) Math.round(line.start().getY()),
                    (int) Math.round(line.end().getX()), (int) Math.round(line.end().getY()));
            Point intersection = intLine.intersectionWith(side);
            boolean isAlreadyIn = false;

            // If there is intersection point, add him to the list.
            if (intersection != null) {

                // Check if the point is already in the list.
                for (Point point : interPoints) {
                    if (intersection.equals(point)) {
                        isAlreadyIn = true;
                        break;
                    }
                }

                //If the point already in the list - ignore, if not, add it to the list (we don't want duplicate points)
                if (!isAlreadyIn) {
                    interPoints.add(intersection);
                }
            }
        }
        // Return the list of intersection points.
        return interPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the lower-Right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * Set the location of the rectangle.
     * @param xUpperLeft  of the rectangle new location.
     * @return new rectangle.
     */
    public Rectangle moveUpperLeft(double xUpperLeft) {

        // Change the x coordinate of the rectangle
        this.upperLeft = new Point(xUpperLeft, this.getUpperLeft().getY());

        // Change the lowe right point accordingly.
        this.lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return this;
    }

}