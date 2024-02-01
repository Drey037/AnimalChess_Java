package models;

import java.util.*;

/**
 * The class Player is used to represent the players
 * in the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Player {

    /**
     * This constructor initializes the color, the animal pieces, the den, and the
     * number of pieces of the player
     *
     * @param c the boolean value whether the player is Red or Blue
     */
    public Player (boolean c) {
        COLOR = c;
        Pieces = new ArrayList<Animals>();
        initPieces(c);
        numPieces = 8;
    }

    /**
     * This method initializes the animal pieces of the player
     *
     * @param c the boolean value whether the player is Red or Blue
     */
    public void initPieces (boolean c) {
        if (c) { //Red
            Pieces.add(new Elephant(2, 0, true));
            Pieces.add(new Lion(0, 6, true));
            Pieces.add(new Tiger(0, 0, true));
            Pieces.add(new Leopard(2, 4, true));
            Pieces.add(new Dog(1, 5, true));
            Pieces.add(new Wolf(2, 2, true));
            Pieces.add(new Cat(1, 1, true));
            Pieces.add(new Mouse(2, 6, true));

            den = new Den(0, 3, false);
        }
        else //Blue
        {
            Pieces.add(new Elephant(6, 6, false));
            Pieces.add(new Lion(8, 0, false));
            Pieces.add(new Tiger(8, 6, false));
            Pieces.add(new Leopard(6, 2, false));
            Pieces.add(new Dog(7, 1, false));
            Pieces.add(new Wolf(6, 4, false));
            Pieces.add(new Cat(7, 5, false));
            Pieces.add(new Mouse(6, 0, false));

            den = new Den(8, 3, true);
        }
    }

    /**
     * This method returns the number of pieces the player has
     *
     * @return the number of pieces
     */
    public int getNumPieces() {
        return numPieces;
    }

    /**
     * This method returns the animal pieces of the player
     *
     * @return the array list of animal pieces
     */
    public ArrayList<Animals> getPieces() {
        return Pieces;
    }

    /**
     * This method returns the den
     *
     * @return the den of the player
     */
    public Den getDen() {
        return den;
    }

    /**
     * This method returns color of the player
     *
     * @return the color of the player
     */
    public boolean getColor () {
        return COLOR;
    }

    /** The array list of animal pieces */
    private ArrayList <Animals> Pieces;

    /** The color of the player */
    private final boolean COLOR;

    /** The number of animal pieces */
    private int numPieces;

    /** The home den of the player*/
    private Den den;
}

