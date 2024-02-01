package models;

/**
 * The class Elephant is used to represent the elephant
 * pieces to be used in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Elephant extends Animals{

    /**
     * This constructor initializes the ranking, the X and Y position,
     * the color side, the CANSWIM and CANJUMP attribute, the string file name
     * and the trapped status of the Elephant class
     *
     * @param x the X position
     * @param y the Y position
     * @param c the boolean value whether the animal is Red or Blue
     */
    public Elephant (int x, int y, boolean c) {
        super( 8, x, y, c, false, false);
        if (c == false)
            ImgFile = "/Resources/Blue Elephant.png";
        else
            ImgFile = "/Resources/Red Elephant.png";
    }

    /**
     * This method returns true if the animal can
     * eat the animal piece
     *
     * @param animal the piece to be checked if can be eaten
     *
     * @return true if animal can be eaten, false if otherwise
     */
    public boolean canEat(Animals animal) {
        if (animal instanceof Mouse) {
            return animal.checkIfTrapped() && getColor() != animal.getColor();
        }
        else {
            return getColor() != animal.getColor();
        }
    }
}
