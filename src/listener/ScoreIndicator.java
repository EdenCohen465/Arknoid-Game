// ID: 318758778
package listener;

import objects.Point;
import objects.Rectangle;
import biuoop.DrawSurface;
import game.GameLevel;
import logics.Sprite;

import java.awt.Color;

/**
 * @author Eden Cohen
 * This class is incharge of display the player score on the screen.
 */
public class ScoreIndicator implements Sprite {

    // Constants:
    private static final int WIDTH = 800;

    private ScoreTrackingListener score;

    /**
     * Constructor.
     * @param score of the player.
     */
    public ScoreIndicator(ScoreTrackingListener score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle shape = new Rectangle(new Point(0, 0), WIDTH, 15);
        d.setColor(new Color(255, 102, 102));
        d.fillRectangle((int) shape.getUpperLeft().getX(), (int) shape.getUpperLeft().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(WIDTH / 2, 13, "Score:" + this.score.getCurrentScore().getValue(), 16);
    }

    @Override
    public void timePassed() { }

    /**
     * Add this paddle to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
