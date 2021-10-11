package logics;

import objects.Line;
import objects.Point;

import java.util.ArrayList;

/**
 * @author Eden Cohen
 * Create game enviroment with collection of collidable objects.
 */
public class GameEnvironment {

    // Constants:
    private static final int FIRST = 0;

    // collections of blocks.
    private ArrayList<Collidable> collidables;

    /**
     * Constructor1 - create a list of blocks.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * Constructor2 - create a list of blocks.
     * @param collidablesArr - list of blocks and paddle that in the game.
     */
    public GameEnvironment(ArrayList<Collidable> collidablesArr) {
        this.collidables = collidablesArr;
    }

    /**
     * @return the collidables list.
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c collidable to add to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Find the information about the closest collision that is going to occur.
     *
     * @param trajectory the object moving from trajectory.start() to trajectory.end().
     * @return the logics.CollisionInfo of the closest collision or null if this object will not collide with any of the
     *         collidables in this collection.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // Create new list of collision info.
        ArrayList<CollisionInfo> infoList = new ArrayList<>();

        // Loop through the blocks and save the potential collision points and their blocks.
        for (Collidable collidable : this.collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (p != null) {
                infoList.add(new CollisionInfo(p, collidable));
            }
        }

        // No collision Points.
        if (infoList.isEmpty()) {
            return null;
        }

        // Loop through the list of collisionPointsAndObjects and find who is the closest one to the object location.
        CollisionInfo closest = infoList.get(FIRST);
        for (CollisionInfo info : infoList) {

            // Check who is closer to the start point - and save the closest one.
            if (trajectory.start().distance(info.collisionPoint())
                    < trajectory.start().distance(closest.collisionPoint())) {
                closest = info;
            }
        }

        // return closest collision.
        return closest;
    }
}

