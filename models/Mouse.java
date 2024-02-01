package models;

/**
 * The class Mouse is used to represent the mouse
 * pieces to be used in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Mouse extends Animals{

    /**
     * This constructor initializes the ranking, the X and Y position,
     * the color side, the CANSWIM and CANJUMP attribute, the string file name
     * and the trapped status of the Mouse class
     *
     * @param x the X position
     * @param y the Y position
     * @param c the boolean value whether the animal is Red or Blue
     */
    public Mouse(int x, int y, boolean c) {
        super( 1, x, y, c, true, false);

        if (c == false)
            ImgFile = "/Resources/Blue Mouse.png";
        else
            ImgFile = "/Resources/Red Mouse.png";
        isSwimming = false;
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

        if (animal instanceof Elephant) {
            return !isSwimming && getColor() != animal.getColor();
        } else if (animal instanceof Mouse) {
            Mouse temp = (Mouse) animal;

            if (isSwimming && temp.getIsSwimming() && getColor() != animal.getColor())
                return true;
            else if (!isSwimming && temp.getIsSwimming())
                return false;
            else if (!isSwimming && !temp.getIsSwimming() && getColor() != animal.getColor())
                return true;
            else
                return false;
        } else
            return animal.checkIfTrapped() && getColor() != animal.getColor() && !isSwimming;
    }

    /**
     * This method returns the value of isSwimming which is
     * true if the Mouse is in a river tile
     *
     *
     * @return true if true if Mouse is in a river tile, false if otherwise
     */
    public boolean getIsSwimming () {
        return isSwimming;
    }

    /**
     * This method sets the value of isSwimming to true
     * if Mouse is in a river tile
     *
     * @param c the boolean value
     *
     */
    public void setSwimming(boolean c) {
        isSwimming = c;
    }

    /** The boolean variable of whether the mouse is currently in a river tile */
    private boolean isSwimming;
}