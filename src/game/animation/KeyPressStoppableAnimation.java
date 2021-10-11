package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Eden Cohen
 * Class that check if the given key is pressed- if yes- she stops the animaion.
 */
public class KeyPressStoppableAnimation implements Animation {

    // Members:
    private final String key;
    private final KeyboardSensor keyboardSensor;
    private final Animation animation;
    private Boolean stop;
    private Boolean isAlreadyPressed;

    /** Constructor.
     * @param keyboardSensor that the player pressed.
     * @param key that make the animation stop.
     * @param animation that run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.animation = animation;
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        if (isPressed() && !this.isAlreadyPressed) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public String getAnimationName() {
        return this.animation.getAnimationName();
    }

    /**
     * @return true if the given kew is pressed.
     */
    public Boolean isPressed() {
        return this.keyboardSensor.isPressed(this.key);
    }
}
