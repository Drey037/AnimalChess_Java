package models;

import java.util.*;

/**
 * The class Lion is used as a model for
 * the picking of colors before the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class pickAColor {

    /**
     * This constructor initializes the integer pieces, and the
     * ranking of the pieces the two players picked.
     *
     */
    public pickAColor() {
        initArray();

        playerOne = 0;
        playerTwo = 0;
    }

    /**
     * This method initializes the integer pieces into an array list
     *
     */
    public void initArray () {
        animals = new ArrayList<Integer>();

        for (int i = 1; i <= 8; i++) {
            animals.add(i);
        }
    }

    /**
     * This method returns the array list of integers
     *
     * @return the list of integers which are the ranks the animal pieces
     */
    public ArrayList<Integer> getAnimals() {
        return animals;
    }

    /**
     * This method sets the score of the first player based on
     * the chosen piece.
     *
     * @param a the ranking of chosen piece
     */
    public void setPlayerOne(int a) {
        playerOne = a;
    }

    /**
     * This method sets the score of the second player based on
     * the chosen piece.
     *
     * @param a the ranking of chosen piece
     */
    public void setPlayerTwo(int a) {
        playerTwo = a;
    }

    /**
     * This method returns the score of player one
     *
     * @return The score of player one
     */
    public int getPlayerOne() {
        return playerOne;
    }

    /**
     * This method returns the score of player two
     *
     * @return The score of player two
     */
    public int getPlayerTwo() {
        return playerTwo;
    }

    /** The score of player one */
    private int playerOne;

    /** The score of player two */
    private int playerTwo;

    /** The array list of rankings of animal pieces */
    private ArrayList<Integer> animals;
}
