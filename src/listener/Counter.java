// ID: 318758778
package listener;

/**
 * @author Eden Cohen
 * This cllas is used to count things.
 */
public class Counter {

    //Field;
    private int count;

    /**
     * Constructor.
     * @param count to initialize the counter.
     */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * @param number to add to the current count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * @param number to subtract from the current count.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return this.count;
    }
}