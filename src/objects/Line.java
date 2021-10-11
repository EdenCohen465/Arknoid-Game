// ID: 318758778
package objects;
import java.util.ArrayList;
import java.util.List;

/**
 * @author      Eden Cohen
 * The Objects.Line class create new line that consists of two points.
 * The class has several methods that gives a information about the line - i.e length, middle, start and end points,
 * if the line intersects with other line ect.
 */
public class Line {

    // Constants
    private static final int PARAMETERS = 2;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final double EPSILON = Math.pow(10, -15);

    // Fields: each line connects two points.
    private final Point start;
    private final Point end;

    /**
     *  Constructor in case there are two parameters as Points.
     * @param start - the first point.
     * @param end   - second point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor in case there are 4 double parameters.
     * @param x1 - x coordinate for start.
     * @param y1 - y coordinate for start.
     * @param x2 - x coordinate for end.
     * @param y2 - y coordinate for end.
     */
    public Line(double x1, double y1, double x2, double y2) {

        // Create new points by the given parameters.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *  @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     * @return middle
     */
    public Point middle() {

        // The x and the y is the average of the the first and the second point.
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     * @return start.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return end.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if two doubles equals.
     * @param x first double.
     * @param y second double.
     * @return true if equal and false if not.
     */
    public boolean isEqual(double x, double y) {
        return Math.abs(x - y) <= EPSILON;
    }

    /**
     * Check if the line is interesting with other line.
     * @param other - the line we want to check if intersect with our line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        // Check if the lines have intersection point then check if the point is in the line.
        Point intersection = intersectionWith(other);

        // Check if the point is in the range of the lines.
        if (intersection != null && checkRange(other, intersection)) {
            return true;
        }
        // If "intersectionWith" returns null- the lines don't intersect - return false, or unite - return true.
        // If the line equal
        if (equals(other)) {
            return true;
        }

        // Find the equations of the two lines.
        double[] lineEquation = findEquation(this.start, this.end);
        double[] otherEquation = findEquation(other.start(), other.end());

        // If both equation is x = something - check if they have points in common.
        if (lineEquation == null && otherEquation == null) {
            if (Math.abs(this.start.getX() - other.start().getX()) <= EPSILON) {
                return checkThisRange(other.start()) || checkThisRange(other.end());
            }
            return false;
        }

        /* If only one of the lines is x = something, there is only one or zero intersection point.
        because we already found if they have one intersection, in means they have zero*/
        if (otherEquation == null || lineEquation == null) {
            return false;
        }

        // If the lines have the same slope - check if unite- if they have common points.
        if (Math.abs(otherEquation[FIRST] - lineEquation[FIRST]) <= EPSILON) {
            if (otherEquation[SECOND] != lineEquation[SECOND]) {
                return false;
            }

            // return if the lines unite.
            return checkThisRange(other.start()) || checkThisRange(other.end());
        }
        return false;
    }

    /**
     * The method gets a list of double and checks if they are all different.
     * @param values - a list of values.
     * @return true if all values are different and false if not.
     */
    private boolean allAreDifferent(double... values) {

        // If the function did not get any numbers.
        if (values.length == 0) {
            return true;
        }

        /* Check each value with the rest values - if one of them is equal -
        return false. */
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {

                // If equal - return false.
                if (isEqual(values[i], values[j])) {
                    return false;
                }
            }
        }

        // If all values different.
        return true;
    }

    /**
     * Find the Equation of a line by 2 points --- y = m * x + b.
     * @param firstPoint  - first point of the line.
     * @param secondPoint - second point of the line.
     * @return equation- array of doubles with the parameters m and n.
     */
    private double[] findEquation(Point firstPoint, Point secondPoint) {

        // New array to save the parameters of the equation.
        double[] equation;
        equation = new double[PARAMETERS];

        /* Find the equation of a line --- y = m * x + n and save m and n in the
         array - equation[0]= m ,equation[1]= n. */
        double a = firstPoint.getY() - secondPoint.getY();
        double b = firstPoint.getX() - secondPoint.getX();

        // If the x's equal, the equation is x = something.
        if (isEqual(b, 0)) {
            return null;
        }

        // The slope in the equation (m) is (y1-y2)/(x1-x2).
        double m = a / b;

        // Find n by solving the equation with one of the points: y= m * x + n ----> n = y - m * x
        double n = firstPoint.getY() - (m * firstPoint.getX());

        // Save the findings in the array: equation = {m, n}.
        equation[FIRST] = m;
        equation[SECOND] = n;
        return equation;
    }

    /**
     * Check if the point in the range of this line.
     * @param point to check if in the range.
     * @return true if in the range and false if not in the range.
     */
    private boolean checkThisRange(Point point) {
        return point.getX() <= Math.max(this.start().getX(), this.end().getX())
                && point.getX() >= Math.min(this.start().getX(), this.end().getX())
                && point.getY() <= Math.max(this.start().getY(), this.end().getY())
                && point.getY() >= Math.min(this.start().getY(), this.end().getY());
    }

    /**
     * Check if the given point is in the range with the line and other line.
     * @param line1 - first line to check range.
     * @param point - to check if in the range of the lines.
     * @return true if in the range and false if not.
     */
    private boolean checkRange(Line line1, Point point) {

        // Check if the point is in the range of the first line.
        if (point.getX() <= Math.max(line1.start.getX(), line1.end.getX())
                && point.getX() >= Math.min(line1.start.getX(), line1.end.getX())
                && point.getY() <= Math.max(line1.start.getY(), line1.end.getY())
                && point.getY() >= Math.min(line1.start.getY(), line1.end.getY())) {

            // check if the point is in the range of this line
            return checkThisRange(point);
        }

        // The point is not in the range of the lines
        return false;
    }

    /**
     * The method is called when two lines are have the same equation and have one point in common (end or start) we
     * want to check if they have only one intersection point or infinite (if they have a common part).
     * @param other - line to check ranges of start point and end point
     * @return the intersection point - if there are infinite points - null.
     */
    private Point lineCheckRange(Line other) {
        if (other.start().equals(this.start)) {

            // check if the other point in the range of the line
            if (checkRange(other, this.end) || checkRange(other, other.end())) {
                return null;
            }

            //not in rage-
            return this.start;
        } else if (other.end().equals(this.start)) {

            // check if the other point in the range of the line
            if (checkRange(other, this.end) || checkRange(other, other.start())) {
                return null;
            }

            //not in rage-
            return this.start;
        } else if (other.end().equals(this.end)) {

            // check if the other point in the range of the line
            if (checkRange(other, this.start) || checkRange(other, other.start())) {
                return null;
            }

            //not in rage-
            return this.end;

        // if other.start == this.end
        } else {

            // check if in the range
            if (checkRange(other, this.start) || checkRange(other, other.end())) {
                return null;
            }

            //not in rage-
            return this.end;
        }
    }

    /**
     * The method check if the point is in line.
     * @param equation of the line y=m*x+n (array of double - equation[0] = m, equation[1] = n).
     * @param point to check if on line.
     * @return true if the point on the line and false if not.
     */
    private boolean isPointOnLine(double[] equation, Point point) {

        /* Set x and y and parameters in the equation (y=m*x+n) and check if its true:
        if the equation is true - the point is on the line. if the equation is false - the point is not on the line. */
        return isEqual(point.getY(), (equation[FIRST] * point.getX() + equation[SECOND]));
    }

    /**
     * Find the intersection point.
     * @param other - the line to find the intersection point with.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {

        // Check if one of the lines consists of two equal points
        if (this.start.equals(this.end) || other.start().equals(other.end())) {

            // If both lines consists of only one point- it means that they dont have an intersection point.
            if (this.start.equals(this.end) && other.start().equals(other.end())) {
                if (this.start.equals(other.start())) {
                    return this.start;
                }
                return null;
            }

            // If one of the lines is only point- check if the point is on the line.
            if (this.start.equals(this.end)) {

                // Find the equation of the other line.
                double[] otherEquation = findEquation(other.start(), other.end());

                // If the equation is x = something check if the point is in the range.
                if (otherEquation == null) {
                    if (checkRange(other, this.start)) {
                        return this.start;
                    }
                    return null;
                }

                // Check if the point on the line and in the range.
                if (isPointOnLine(otherEquation, this.start) && checkRange(other, this.start)) {
                    return this.start;
                }

                // The point is not on the line or not in range.
                return null;
            }

            if (other.start().equals(other.end())) {

                // Find the equation of the line.
                double[] thisEquation = findEquation(this.start, this.end);

                // If the equation is x = something check if the point is in the range.
                if (thisEquation == null) {
                    if (checkThisRange(other.start())) {
                        return other.start();
                    }
                    return null;
                }

                // Check if the point on the line and in the range.
                if (isPointOnLine(thisEquation, other.start()) && checkThisRange(other.start())) {
                    return other.start();
                }
                return null;
            }
        }
        // If the lines equal- return null - infinite common points.
        if (equals(other)) {
            return null;
        }

        // Find the equations of the two lines
        double[] lineEquation = findEquation(this.start, this.end);
        double[] otherEquation = findEquation(other.start(), other.end());

        // Edge cases - if one of the equation is x = something or both are.
        if (lineEquation == null || otherEquation == null) {

            // If both equation are null - they are both x = something
            if (lineEquation == null && otherEquation == null) {

                // If their x coordinates different- they dont have point in common- return null.
                if (!isEqual(this.start.getX(), other.start().getX())) {
                    return null;
                }

                // if their y coordinates different - they have zero or infinite common points - return null.
                if (allAreDifferent(other.start().getY(), other.end().getY(), this.start.getY(), this.end.getY())) {
                    return null;
                }

                //if they have y coordinate in common check if the second y coordinate is in the range of the other
                return lineCheckRange(other);
            }

            /* If the lineEquation is x = something, and the otherEquation is not null-
            find the intersection point by placing the variables. */
            if (isEqual(this.start.getX(), this.end.getX()) && otherEquation != null) {
                double y = (otherEquation[FIRST] * this.start.getX()) + otherEquation[SECOND];
                Point intersection = new Point(this.start.getX(), y);

                // check if the point in the range
                if (checkRange(other, intersection)) {
                    return intersection;
                }
                return null;
            }

            /* If the the otherEquation is x = something, and the lineEquation is not null- find the intersection point
            by placing the variables. */
            if (isEqual(other.start().getX(), other.end().getX()) && lineEquation != null) {
                double x = other.start.getX();
                double y = (lineEquation[FIRST] * x) + lineEquation[SECOND];
                Point intersection = new Point(x, y);

                // check if the point in the range
                if (checkRange(other, intersection)) {
                    return intersection;
                }
                return null;
            }
        }

        // If the lines have the same slope (m)- it means that they are parallels or coming together or never meet
        if (isEqual(lineEquation[FIRST], otherEquation[FIRST])) {

            // Parallels
            if (!isEqual(lineEquation[SECOND], otherEquation[SECOND])) {
                return null;
            }
            if (this.start.equals(other.start()) || this.start.equals(other.end())
                    || this.end.equals(other.start()) || this.end.equals(other.end())) {
                return lineCheckRange(other);
            }

            // if their slope is even but the dont have point in common - zero intersection points - return null.
            return null;
        }

        /* Find x,y of the intersection point by solve the two equations.
        y = m1 * x + n1 and y = m2 * x + n2 ---> 0 = m1 * x + n1 - (m2 * x + n2) ---> x (m1 - m2) = n2 - n1
        After solving we get: x = (n2 - n1) / (m1 - m2) and  y = m1 * x +n1 */
        double m1 = lineEquation[FIRST];
        double n1 = lineEquation[SECOND];
        double m2 = otherEquation[FIRST];
        double n2 = otherEquation[SECOND];

        // Solve and find the intersection point.
        double x = (n2 - n1) / (m1 - m2);
        double y = (m1 * x) + n1;
        Point intersection = new Point(x, y);

        // Return the intersection point if in the range.convert to int - avoid mistakes.
        Point intIntersection = new Point((int) Math.round(intersection.getX()),
                (int) Math.round(intersection.getY()));
        if (checkRange(other, intIntersection)) {
            return intIntersection;
        }
        return null;
    }

    /**
     * @param other line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {

        // If start = start and end = end.
        if (this.start.equals(other.start()) && this.end.equals(other.end())) {
            return true;
        }

        // If start = end and end = start.
        return this.start.equals(other.end()) && this.end.equals(other.start());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect to find the closest intersection point.
     * @return closest intersection point or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> interPoints = rect.intersectionPoints(this);

        // Check if there are intersection no points - return null.
        if (interPoints.isEmpty()) {
            return null;
        }

        // Create new array - max points is 4.
        ArrayList<Double> distances = new ArrayList<>();

        // Find the distance from each intersection point to the start point of the line and save in array.
        for (Point interPoint : interPoints) {
            distances.add(this.start.distance(interPoint));
        }

        // Find the minimum distance and return the point it related to.
        double min = distances.get(FIRST);
        for (double dist : distances) {
            if (dist < min) {
                min = dist;
            }
        }

        // Find the index of the minimum distance and return its point.
        int index = distances.indexOf(min);
        return interPoints.get(index);
    }
}



