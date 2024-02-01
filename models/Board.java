package models;
import java.util.*;

/**
 * The class Board is used to represent the model for the
 * functions of the Animal chess board
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Board {

    /**
     * This constructor initializes the players, the array of tiles, the color
     * to make a move first, and the pieces
     *
     * @param Color the first color to make a move
     */
    public Board (boolean Color) {
        Red = new Player(true);
        Blue = new Player(false);
        Gameboard = new Tile[ROW][COL];

        turn = Color;
        redWins = false;
        blueWins = false;
        blueIsAtDen = false;
        redIsAtDen = false;

        redPieces = Red.getPieces();
        bluePieces = Blue.getPieces();

        numRedPieces = Red.getNumPieces();
        numBluePieces = Blue.getNumPieces();

        initAllPieces();
    }

    /**
     * This method initializes all the pieces in the board
     *
     */
    public void initAllPieces() {
        int i, j;

        //Red Player
        for (i = 0; i < redPieces.size(); i++) {
            Gameboard[redPieces.get(i).getPosY()][redPieces.get(i).getPosX()] = redPieces.get(i);
        }

        //Blue Player
        for (i = 0; i < Blue.getPieces().size(); i++) {
            Gameboard[bluePieces.get(i).getPosY()][bluePieces.get(i).getPosX()] = bluePieces.get(i);
        }

        //Rivers
        rivers = new ArrayList<River>();
        initRivers();
        for (j = 0; j < rivers.size(); j++) {
            Gameboard[rivers.get(j).getPosY()][rivers.get(j).getPosX()] = rivers.get(j);
        }

        //Dens
        Gameboard[3][0] = Blue.getDen();
        Gameboard[3][8] = Red.getDen();

        //Traps
        traps = new ArrayList<Trap>();
        initTraps();
    }

    /**
     * This method initializes the river tiles
     *
     */
    public void initRivers() {
        rivers.add(new River(3,1));
        rivers.add(new River(4,1));
        rivers.add(new River(5,1));
        rivers.add(new River(3,2));
        rivers.add(new River(4,2));
        rivers.add(new River(5,2));

        rivers.add(new River(3,4));
        rivers.add(new River(4,4));
        rivers.add(new River(5,4));
        rivers.add(new River(3,5));
        rivers.add(new River(4,5));
        rivers.add(new River(5,5));
    }

    /**
     * This method initializes the traps in the board
     *
     */
    public void initTraps() {
        traps.add(new Trap(0, 2, true));
        traps.add(new Trap(1, 3, true));
        traps.add(new Trap(0, 4, true));

        traps.add(new Trap(8, 2, false));
        traps.add(new Trap(7, 3, false));
        traps.add(new Trap(8, 4, false));
    }

    /**
     * This method changes turn after the player makes a move
     *
     */
    public void setTurn() {
        turn = !turn;
    }

    /**
     * This method checks if the animal can go left
     *
     * @param animal the piece which the possible moves will be checked
     * @return true if the animal piece can go left
     */
    public boolean canGoLeft (Tile animal) {
        Animals temp;
        Animals nextAnimal;
        Animals nextJump;

        if (animal.getPosX() - 1 >= 0) {
            temp = (Animals) animal;

            //Next Tile is a river
            if (checkLeftTile(animal) != null && checkLeftTile(animal) instanceof River) {
                //Piece is a mouse
                if (temp.getCanSwim())
                    return true;

                //Piece is a tiger or Lion
                else if (temp.getCanJump()) {

                    //There is no animal blocking
                    if (Gameboard[temp.getPosY()][3] instanceof River && Gameboard[temp.getPosY()][4] instanceof River && Gameboard[temp.getPosY()][5] instanceof River) {

                        //If Landing tile after jumping is an animal
                        if (Gameboard[temp.getPosY()][2] instanceof Animals) {
                            nextJump = (Animals) Gameboard[animal.getPosY()][2];
                            return temp.canEat(nextJump);
                        }
                        //Else if landing tile is null
                        else
                            return true;
                    }
                    else
                        return false;
                }

                //Piece cannot swim or jump
                else
                    return false;
            }

            //Next tile is a trap
            else if (checkLeftTile(animal) != null && checkLeftTile(animal) instanceof Trap) {
                Trap tempTrap = (Trap) Gameboard[animal.getPosY()][animal.getPosX() - 1];

                //Can move if the trap is not the same color
                return tempTrap.getColor() != temp.getColor();
            }

            //Next tile is an animal piece
            else if (checkLeftTile(animal) != null && checkLeftTile(animal) instanceof Animals) {
                nextAnimal = (Animals) Gameboard[animal.getPosY()][animal.getPosX() - 1];
                return temp.canEat(nextAnimal);
            }

            //Next tile is a den
            else if (checkLeftTile(animal) != null && checkLeftTile(animal) instanceof Den) {
                Den tempDen = (Den) Gameboard[animal.getPosY()][animal.getPosX() - 1];

                return temp.getColor() != tempDen.getColor();
            }
            //Next tile is empty
            else
                return true;
        }
        // Position is out of bounds
        else
            return false;
    }

    /**
     * This method checks if the animal can go right
     *
     * @param animal the piece which the possible moves will be checked
     * @return true if the animal piece can go right
     */
    public boolean canGoRight (Tile animal) {
        Animals temp;
        Animals nextAnimal;
        Animals nextJump;

        if (animal.getPosX() + 1 <= 8) {
            temp = (Animals) animal;

            //Next Tile is a river
            if (checkRightTile(animal) != null && checkRightTile(animal) instanceof River) {
                //Piece is a mouse
                if (temp.getCanSwim())
                    return true;

                    //Piece is a tiger or Lion
                else if (temp.getCanJump()) {

                    //There is no animal blocking
                    if (Gameboard[temp.getPosY()][3] instanceof River && Gameboard[temp.getPosY()][4] instanceof River && Gameboard[temp.getPosY()][5] instanceof River) {

                        //If Landing tile after jumping is an animal
                        if (Gameboard[temp.getPosY()][6] instanceof Animals) {
                            nextJump = (Animals) Gameboard[animal.getPosY()][6];
                            return temp.canEat(nextJump);
                        }
                        //Else if landing tile is null
                        else
                            return true;
                    }
                    else
                        return false;
                }

                //Piece cannot swim or jump
                else
                    return false;
            }

            //Next tile is a trap
            else if (checkRightTile(animal) != null && checkRightTile(animal) instanceof Trap) {
                Trap tempTrap = (Trap) Gameboard[animal.getPosY()][animal.getPosX() + 1];

                //Can move if the trap is not the same color
                return tempTrap.getColor() != temp.getColor();
            }

            //Next tile is an animal piece
            else if (checkRightTile(animal) != null && checkRightTile(animal) instanceof Animals) {
                nextAnimal = (Animals) Gameboard[animal.getPosY()][animal.getPosX() + 1];
                return temp.canEat(nextAnimal);
            }

            //Next tile is a den
            else if (checkRightTile(animal) != null && checkRightTile(animal) instanceof Den) {
                Den tempDen = (Den) Gameboard[animal.getPosY()][animal.getPosX() + 1];

                return temp.getColor() != tempDen.getColor();
            }
            //Next tile is empty
            else
                return true;
        }
        // Position is out of bounds
        else
            return false;
    }

    /**
     * This method checks if the animal can go up
     *
     * @param animal the piece which the possible moves will be checked
     * @return true if the animal piece can go up
     */
    public boolean canGoUp (Tile animal) {
        Animals temp;
        Animals nextAnimal;
        Animals nextJump;

        if (animal.getPosY()-1 >= 0) {
            temp = (Animals) animal;

            //Next Tile is a river
            if (checkUpTile(animal) != null && checkUpTile(animal) instanceof River) {

                //Piece is a mouse
                if (temp.getCanSwim() && temp instanceof Mouse) {
                    return true;
                }

                    //Piece is a tiger or Lion
                else if (temp.getCanJump() && (temp instanceof Lion || temp instanceof Tiger)) {

                    //There is no animal blocking
                    if (Gameboard[temp.getPosY()-1][temp.getPosX()] instanceof River && Gameboard[temp.getPosY()-2][temp.getPosX()] instanceof River) {

                        //If Landing tile after jumping is an animal
                        if (Gameboard[temp.getPosY()-3][temp.getPosX()] instanceof Animals) {
                            nextJump = (Animals) Gameboard[animal.getPosY()-3][temp.getPosX()];
                            return temp.canEat(nextJump);
                        }
                        //Else if landing tile is null
                        else
                            return true;
                    }
                    else
                        return false;
                }

                //Piece cannot swim or jump
                else
                    return false;
            }

            //Next tile is a trap
            else if (checkUpTile(animal) != null && checkUpTile(animal) instanceof Trap) {
                Trap tempTrap = (Trap) Gameboard[animal.getPosY()-1][animal.getPosX()];

                //Can move if the trap is not the same color
                return tempTrap.getColor() != temp.getColor();
            }

            //Next tile is an animal piece
            else if (checkUpTile(animal) != null && checkUpTile(animal) instanceof Animals) {
                nextAnimal = (Animals) Gameboard[animal.getPosY()-1][animal.getPosX()];
                return temp.canEat(nextAnimal);
            }

            //Next tile is a den
            else if (checkUpTile(animal) != null && checkUpTile(animal) instanceof Den) {
                Den tempDen = (Den) Gameboard[animal.getPosY()-1][animal.getPosX()];

                return temp.getColor() != tempDen.getColor();
            }
            //Next tile is empty
            else
                return true;
        }

        // Position is out of bounds
        else
            return false;
    }

    /**
     * This method checks if the animal can go down
     *
     * @param animal the piece which the possible moves will be checked
     * @return true if the animal piece can go down
     */
    public boolean canGoDown (Tile animal) {
        Animals temp;
        Animals nextAnimal;
        Animals nextJump;

        if (animal.getPosY()+1 <= 6) {
            temp = (Animals) animal;

            //Next Tile is a river
            if (checkDownTile(animal) != null && checkDownTile(animal) instanceof River) {
                //Piece is a mouse
                if (temp.getCanSwim() && temp instanceof Mouse)
                    return true;

                    //Piece is a tiger or Lion
                else if (temp.getCanJump() && (temp instanceof Lion || temp instanceof Tiger)) {

                    //There is no animal blocking
                    if (Gameboard[temp.getPosY()+1][temp.getPosX()] instanceof River && Gameboard[temp.getPosY()+2][temp.getPosX()] instanceof River) {

                        //If Landing tile after jumping is an animal
                        if (Gameboard[temp.getPosY()+3][temp.getPosX()] instanceof Animals) {
                            nextJump = (Animals) Gameboard[animal.getPosY()+3][temp.getPosX()];
                            return temp.canEat(nextJump);
                        }
                        //Else if landing tile is null
                        else
                            return true;
                    }
                    else
                        return false;
                }

                //Piece cannot swim or jump
                else
                    return false;
            }

            //Next tile is a trap
            else if (checkDownTile(animal) != null && checkDownTile(animal) instanceof Trap) {
                Trap tempTrap = (Trap) Gameboard[animal.getPosY()+1][animal.getPosX()];

                //Can move if the trap is not the same color
                return tempTrap.getColor() != temp.getColor();
            }

            //Next tile is an animal piece
            else if (checkDownTile(animal) != null && checkDownTile(animal) instanceof Animals) {
                nextAnimal = (Animals) Gameboard[animal.getPosY()+1][animal.getPosX()];
                return temp.canEat(nextAnimal);
            }

            //Next tile is a den
            else if (checkDownTile(animal) != null && checkDownTile(animal) instanceof Den) {
                Den tempDen = (Den) Gameboard[animal.getPosY()+1][animal.getPosX()];

                return temp.getColor() != tempDen.getColor();
            }
            //Next tile is empty
            else
                return true;
        }

        // Position is out of bounds
        else
            return false;
    }

    /**
     * This method checks if the animal piece can be moved to the given position
     * and returns true if the piece is moved successfully
     *
     * @param piece the piece that will be moved
     * @param x the given X position
     * @param y the given Y position
     * @return canMove True if the animal piece is moved successfully
     */
    public boolean movePiece(Animals piece, int x, int y) {
        boolean canMove = false;


        //Can go down
        if (x == piece.getPosX() && y == piece.getPosY()+1 || (x == piece.getPosX() &&
                y == piece.getPosY()+3 && Gameboard[y-1][x] instanceof River)) {
             if (canGoDown(piece)) {
                 canMove = true;
             }
        }

        //Can go up
        else if (x == piece.getPosX() && y == piece.getPosY()-1 || (x == piece.getPosX() &&
                y == piece.getPosY()-3 && Gameboard[y+1][x] instanceof River)) {
            if (canGoUp(piece)) {
                canMove = true;
            }
        }

        //Can go left
        else if (x == piece.getPosX()-1 && y == piece.getPosY() || (x == piece.getPosX()-4 &&
                y == piece.getPosY() && Gameboard[y][x+1] instanceof River)) {
            if (canGoLeft(piece)) {
                canMove = true;
            }
        }

        //Can go right
        else if (x == piece.getPosX()+1 && y == piece.getPosY() || (x == piece.getPosX()+4 &&
                y == piece.getPosY()  && Gameboard[y][x-1] instanceof River)) {
            if (canGoRight(piece)) {
                canMove = true;
            }
        }

        //Current position is selected
        else if (x == piece.getPosX() && y == piece.getPosY()) {
            canMove = false;
        }


        if (canMove) {

            //If next position has a trap
            if (Gameboard[y][x] instanceof Trap)
                piece.setTrapped(true);

            //If a currently trapped animal will go out of the trapped
            if (!(Gameboard[y][x] instanceof Trap) && piece.isTrapped)
                piece.setTrapped(false);

            //If a mouse will go to a river tile
            if (Gameboard[y][x] instanceof River && piece instanceof Mouse)
                setMouseSwim((Mouse)piece, true);

            //If a mouse will go to land
            if (Gameboard[y][x] == null && piece instanceof Mouse && ((Mouse) piece).getIsSwimming())
                setMouseSwim((Mouse) piece, false);

            //If next position is a den
            if (Gameboard[y][x] instanceof Den) {
                if (piece.getColor())
                    redIsAtDen = true;
                else
                    blueIsAtDen = true;
            }

            //If next position has an animal piece that will be eaten
            if (Gameboard[y][x] instanceof Animals) {
                if (piece instanceof Mouse) {
                    if (((Mouse) piece).getIsSwimming())
                        setMouseSwim((Mouse)piece, true);
                }
                decreasePiece((Animals) Gameboard[y][x]);
                Gameboard[y][x] = null;
            }

            Gameboard[y][x] = piece;
            Gameboard[piece.getPosY()][piece.getPosX()] = null;
            resetRivers();
            resetTraps();
        }
        return canMove;
    }

    /**
     * This method resets river tiles after every move
     *
     */
    public void resetRivers() {
        for (int j = 0; j < rivers.size(); j++) {
            if (!(Gameboard[rivers.get(j).getPosY()][rivers.get(j).getPosX()] instanceof Mouse))
                Gameboard[rivers.get(j).getPosY()][rivers.get(j).getPosX()] = rivers.get(j);
        }
    }

    /**
     * This method resets traps after every move
     *
     */
    public void resetTraps() {
        for (int j = 0; j < traps.size(); j++) {
            if (!(Gameboard[traps.get(j).getPosY()][traps.get(j).getPosX()] instanceof Animals))
                Gameboard[traps.get(j).getPosY()][traps.get(j).getPosX()] = traps.get(j);
        }
    }

    /**
     * This method changes the isSwimming value if the mouse is
     * in a river tile
     *
     * @param mouse the piece that will be checked
     * @param c if the mouse piece is swimming
     */
    public void setMouseSwim(Mouse mouse, boolean c) {
        mouse.setSwimming(c);


        //DELETE
        if (c)
            System.out.println("Mouse is swimming");
        else
            System.out.println("Mouse is on land");
    }

    /**
     * This method returns the tile that is on the left of the piece
     *
     * @param obj the selected animal piece
     * @return the next tile on the left
     */
    public Tile checkLeftTile (Tile obj) {
        if (obj != null && obj.getPosX()-1 >= 0)
            return Gameboard[obj.getPosY()][obj.getPosX()-1];
        else
            return null;
    }

    /**
     * This method returns the tile that is on the right of the piece
     *
     * @param obj the selected animal piece
     * @return the next tile on the right
     */
    public Tile checkRightTile(Tile obj) {
        if (obj != null && obj.getPosX()+1 <= 8)
            return Gameboard[obj.getPosY()][obj.getPosX()+1];
        else
            return null;
    }

    /**
     * This method returns the tile that is above the piece
     *
     * @param obj the selected animal piece
     * @return the next tile above the piece
     */
    public Tile checkUpTile (Tile obj) {
        if (obj != null && obj.getPosY()-1 >= 0)
            return Gameboard[obj.getPosY()-1][obj.getPosX()];
        else
            return null;
    }

    /**
     * This method returns the tile that is below the piece
     *
     * @param obj the selected animal piece
     * @return the next tile below the piece
     */
    public Tile checkDownTile (Tile obj) {
        if (obj != null && obj.getPosY()+1 <= 6)
            return Gameboard[obj.getPosY()+1][obj.getPosX()];
        else
            return null;
    }

    /**
     * This method decreases the current number of pieces
     * when a piece is eaten
     *
     * @param piece the animal that is eaten
     */
    public void decreasePiece(Animals piece) {
        if (piece.getColor())
            numRedPieces--;
        else
            numBluePieces--;
    }

    /**
     * This method checks if the winning conditions are satisfied
     *
     */
    public void checkGameOver() {
        if (numBluePieces == 0 || redIsAtDen)
            redWins = true;
        else if (numRedPieces == 0 || blueIsAtDen)
            blueWins = true;
    }

    /**
     * This method returns redWins variable
     *
     * @return redWins true if red player has won
     */
    public boolean getRedWins () {
        return redWins;
    }


    /**
     * This method returns redWins variable
     *
     * @return redWins true if red player has won
     */
    public boolean getBlueWins () {
        return blueWins;
    }

    /**
     * This method returns red player
     *
     * @return Red player
     */
    public Player getRed() {
        return Red;
    }

    /**
     * This method returns blue player
     *
     * @return Blue player
     */
    public Player getBlue() {
        return Blue;
    }

    /**
     * This method returns the 2D array of tiles
     *
     * @return current gameboard
     */
    public Tile[][] getTiles() {
        return Gameboard;
    }

    /**
     * This method returns the current turn
     *
     * @return the current color's turn
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * This method returns the current number of red pieces
     *
     * @return numRedPieces the current number of red pieces
     */
    public int getNumRedPieces() {
        return numRedPieces;
    }

    /**
     * This method returns the current number of blue pieces
     *
     * @return numRedPieces the current number of blue pieces
     */
    public int getNumBluePieces() {
        return numBluePieces;
    }


    /** The number of rows for the gameboard */
    private final int ROW = 7;

    /** The number of columns for the gameboard */
    private final int COL = 9;

    /** The color's turn in game */
    private boolean turn;

    /** The 2D array of tiles */
    private Tile[][] Gameboard;

    /** The red player */
    private Player Red;

    /** The animal pieces of the red player */
    private ArrayList<Animals> redPieces;

    /** The blue player */
    private Player Blue;

    /** The animal pieces of the blue player */
    private ArrayList<Animals> bluePieces;

    /** The river tiles */
    private ArrayList<River> rivers;

    /** The trap tiles */
    private ArrayList<Trap> traps;

    /** The boolean variable for the win status of the red player */
    private boolean redWins;

    /** The boolean variable for the win status of the blue player */
    private boolean blueWins;

    /** The number of red pieces */
    private int numRedPieces;

    /** The number of blue pieces */
    private int numBluePieces;

    /** The boolean variable whether a blue piece is at the den */
    private boolean blueIsAtDen;

    /** The boolean variable whether a red piece is at the den */
    private boolean redIsAtDen;
}
