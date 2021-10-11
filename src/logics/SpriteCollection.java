package logics;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author Eden Cohen
 * Collection of spirits.
 */
public class SpriteCollection {

    // Member
    private ArrayList<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * @param s to add to the sprites list.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * @return sprite collection
     */
    public ArrayList<Sprite> getSpriteCollection() {
        return this.sprites;
    }
}