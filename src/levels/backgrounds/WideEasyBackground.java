package levels.backgrounds;

import biuoop.DrawSurface;
import logics.Sprite;

import java.awt.Color;

/**
 * @author Eden Cohen
 * this class incharge of drawing the background of Wide Easy level.
 */
public class WideEasyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(95, 246, 239));
        d.fillRectangle(0, 0, 800, 200);
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(100, 100, 70);
        d.setColor(new Color(252, 252, 60));
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(250, 218, 65));
        d.fillCircle(100, 100, 50);
    }

    @Override
    public void timePassed() {

    }
}
