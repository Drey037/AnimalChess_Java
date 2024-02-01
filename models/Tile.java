package models;

/**
 * The class Tile is used to represent the tiles
 * in the board
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Tile {

    /**
     * This constructor initializes the X and Y position of the tile
     *
     * @param x the X position
     * @param y the Y position
     */
    public Tile (int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * This method returns the X position of the Tile
     *
     * @return posX of the tile
     */
    public int getPosX() {
        return posX;
    }

    /**
     * This method returns the Y position of the tile
     *
     * @return PosY of the tile
     */
    public int getPosY() {
        return posY;
    }

    /**
     * This method sets the X position of the tile
     *
     * @param posX the X position of the tile
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * This method sets the Y position of the tile
     *
     * @param posY The Y position of the tile
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /** The X position */
    protected int posX;

    /** The Y position */
    protected int posY;
}
