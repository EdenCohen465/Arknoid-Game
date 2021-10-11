package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import logics.SpriteCollection;

import java.awt.Color;

/**
 * @author Eden Cohen.
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    // Members:
    private final double numOfSecond;
    private int countFrom;
    private final SpriteCollection gameScreenObjects;
    private final Sleeper sleeper;

    /**
     * Constructor.
     * @param numOfSeconds before the game starts to show the animation.
     * @param countFrom 3 to 1.
     * @param gameScreen to show under the count down.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreenObjects = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draw all game objects.
        this.gameScreenObjects.drawAllOn(d);

        // Write how many seconds left before the game starts.
        d.setColor(new Color(149, 84, 177));
        if (this.countFrom != 0) {
            d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2, "" + this.countFrom, 180);
        }

        // Each iteration decrease the amount of seconds.
        this.countFrom = this.countFrom - 1;

        // Sleeper.
        if (this.countFrom < this.numOfSecond) {
            this.sleeper.sleepFor((long) (1500 / this.numOfSecond));
        }
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom < 0;
    }

    @Override
    public String getAnimationName() {
        return "";
    }

}