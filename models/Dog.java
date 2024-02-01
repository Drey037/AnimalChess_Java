package models;

/**
 * The class Dog is used to represent the dog
 * pieces to be used in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Dog extends Animals{

    /**
     * This constructor initializes the ranking, the X and Y position,
     * the color side, the CANSWIM and CANJUMP attribute, the string file name
     * and the trapped status of the Dog class
     *
     * @param x the X position
     * @param y the Y position
     * @param c the boolean value whether the animal is Red or Blue
     */
    public Dog (int x, int y, boolean c) {
        super( 4, x, y, c, false, false);

        if (c == false)
            ImgFile = "/Resources/Blue Dog.png";
        else
            ImgFile = "/Resources/Red Dog.png";
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
            Mouse temp = (Mouse) animal;

            return !temp.getIsSwimming() && getColor() != animal.getColor();
        }
        else
            return (getRanking() >= animal.getRanking() || animal.checkIfTrapped()) && getColor() != animal.getColor();
    }
}