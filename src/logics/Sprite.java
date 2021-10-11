// ID: 318758778
package logics;
import biuoop.DrawSurface;

/**
 * @author Eden Cohen
 * object that can be drawn to the screen
 */
public interface Sprite {

    /** draw the sprite to the screen.
     * @param d the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}