package models;

/**
 * The class Den is used
 * to represent the Den pieces to be used in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Den extends Tile {

    /**
     * This constructor initializes the X and Y position, and
     * the color side of the den piece
     *
     * @param x the X position
     * @param y the Y position
     * @param c the boolean value whether the Den belongs to Red or Blue
     */
    public Den (int x, int y, boolean c) {
        super(x, y);
        COLOR = c;
    }

    /**
     * This method returns the team color of the den piece
     *
     * @return COLOR of the den piece
     */
    public boolean getColor () {
        return COLOR;
    }

    /** The color of the den. Red if true, Blue if false */
    private final boolean COLOR;
}
