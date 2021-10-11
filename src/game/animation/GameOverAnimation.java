package game.animation;

import biuoop.DrawSurface;

import listener.ScoreTrackingListener;

/**
 * @author Eden Cohen
 * Once the game is over (either the player died, or he managed to clear all the levels), the player's final score
 * is displayed.
 */
public class GameOverAnimation implements Animation {

    // Members:
    private boolean stop;
    private final ScoreTrackingListener scoreTrackingListener;

    /**
     * Constructor.
     * @param scoreTrackingListener the player score.
     */
    public GameOverAnimation(ScoreTrackingListener scoreTrackingListener) {
        this.scoreTrackingListener = scoreTrackingListener;
        this.stop = false;
    }

    /**
     * Stop the game until SPACE key is pressed.
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {

        // Write "GAME OVER!" until SPACE is pressed.
        d.drawText(180, d.getHeight() / 2,
                    "Game Over. Your score is " + this.scoreTrackingListener.getCurrentScore().getValue(), 32);
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
