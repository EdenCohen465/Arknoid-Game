package game.animation;

import biuoop.DrawSurface;

/**
 * @author Eden Cohen
 * Pause the game when the player pressed the "p" key until SPACE is pressed.
 */
public class PauseScreen implements Animation {

    // Members:
    private final boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Stop the game until SPACE key is pressed.
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {

        // If the "p" keyboard pressed, stop the game until SPACE key was pressed.
        d.drawText(180, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public String getAnimationName() {
        return "";
    }
}