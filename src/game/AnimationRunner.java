package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.animation.Animation;

import java.awt.Color;

/**
 * @author Eden Cohen.
 * Runs the animation.
 */
public class AnimationRunner {

    // Members.
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * Constructor.
     * @param framesPerSecond of the animation.
     * @param gui to draw on.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Get the gui.
     * @return gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Responsible of running the given animation.
     * @param animation - the game to run.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        // Run until the animation should stop.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            // Run the animation.
            animation.doOneFrame(d);

            // If the animation is level, write its name on the screen.
            if (!animation.getAnimationName().equals("")) {
                d.setColor(Color.BLACK);
                d.drawText(620, 13, "Level Name: " + animation.getAnimationName(), 16);
            }

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}