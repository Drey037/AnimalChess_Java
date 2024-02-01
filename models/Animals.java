package models;

/**
 * The class Animal is used
 * to represent the animal pieces to be used in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public abstract class Animals extends Tile{

    /**
     * This constructor initializes the ranking, the X and Y position,
     * the color side, the CANSWIM and CANJUMP attribute, and the trapped status
     *
     * @param n the ranking of the animal
     * @param x the X position
     * @param y the Y position
     * @param c the boolean value whether the animal is Red or Blue
     * @param s the boolean value whether the animal can swim
     * @param j the boolean value whether the animal can jump
     */
    public Animals(int n, int x, int y, boolean c, boolean s, boolean j) {
        super(x, y);
        RANKING = n;
        COLOR = c;
        CANSWIM = s;
        CANJUMP = j;
        isTrapped = false;
    }

    /**
     * This method changes the trapped status of the animal
     *
     * @param a the boolean value whether the animal is trapped or not
     */
    public void setTrapped (boolean a) {
        isTrapped = a;
    }

    /**
     * This method returns the team color of the animal piece
     *
     * @return COLOR of the animal piece
     */
    public boolean getColor() {
        return COLOR;
    }

    /**
     * This method returns the ranking of the animal piece
     *
     * @return RANKING of animal piece
     */
    public int getRanking() {
        return RANKING;
    }

    /**
     * This method returns the value of CANSWIM
     *
     * @return CANSWIM which is true if the animal can swim, false if otherwise
     */
    public boolean getCanSwim() {
        return CANSWIM;
    }

    /**
     * This method returns the value of CANJUMP
     *
     * @return CANSWIM which is true if the animal can jump, false if otherwise
     */
    public boolean getCanJump() {
        return CANJUMP;
    }

    /**
     * This method returns the value of isTrapped
     *
     * @return isTrapped which is true if the animal is currently trapped, false if otherwise
     */
    public boolean checkIfTrapped () {
        return isTrapped;
    }

    /**
     * This abstract method returns true if the animal can
     * eat the animal piece
     *
     * @param animal the piece to be checked if can be eaten
     *
     * @return true if animal can be eaten, false if otherwise
     */
    public abstract boolean canEat(Animals animal);


    /**
     * This method returns the string file name for the
     * picture of the animal
     *
     * @return ImgFile the string for the file location and name
     */
    public String getImgFile() {
        return ImgFile;
    }

    /** The color of the animal. Red if true, Blue if false */
    protected boolean COLOR;

    /** The ranking of the animal */
    protected final int RANKING;

    /** The boolean variable for capability of the animal to swim */
    protected final boolean CANSWIM;

    /** The boolean variable for capability of the animal to jump over rivers */
    protected final boolean CANJUMP;

    /** The boolean variable for the status of the animal if trapped or not */
    protected boolean isTrapped;

    /** The file name and location of the picture */
    protected String ImgFile;
}
