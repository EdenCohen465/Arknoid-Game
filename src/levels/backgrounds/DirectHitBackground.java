package levels.backgrounds;

import biuoop.DrawSurface;
import logics.Sprite;

import java.awt.Color;

/**
 * @author Eden Cohen
 * this class incharge of drawing the background of DirectHit level.
 */
public class DirectHitBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawLine(265, 215, 565, 215);
        d.drawLine(415, 65, 415, 365);
        d.drawCircle(415, 215, 100);
        d.drawCircle(415, 215, 80);
        d.drawCircle(415, 215, 60);
    }

    @Override
    public void timePassed() { }
}
