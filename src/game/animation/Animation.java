package game.animation;

import biuoop.DrawSurface;

/**
 * @author Eden Cohen.
 * Objects that have animation, like levels, Pause screen and End screen.
 */
public interface Animation {

    /**
     * In charge of the logics- what is happening each frame.
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation should stop.
     */
    boolean shouldStop();

    /**
     * @return the animation name.
     */
    String getAnimationName();
}