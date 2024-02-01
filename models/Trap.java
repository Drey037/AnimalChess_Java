package models;

/**
 * The class Trap is used to represent the Trap
 * tiles in the board
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Trap extends Tile{

    /**
     * This constructor initializes the X and Y position,
     * and the color of the trap tile
     *
     * @param X the X position
     * @param Y the Y position
     * @param c The color side of which the trap belongs
     */
    public Trap (int X, int Y, boolean c) {
        super (X, Y);
        COLOR = c;
    }

    /**
     * This method returns the color of the trap tile
     *
     * @return COLOR of the trap
     */
    public boolean getColor () {
        return COLOR;
    }

    /** The color of the trap. Red if true, blue if false */
    private final boolean COLOR;
}
