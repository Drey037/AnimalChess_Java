package Controllers;
import models.*;
import views.gameView;
import views.ButtonView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class boardController is used to be a controller
 * for the Board class and the gameView GUI
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class boardController implements ActionListener {

    /**
     * This constructor initializes the board to be used, the GUI,
     * the buttons, and tiles
     *
     * @param game the GUI for the game
     *
     */
    public boardController(gameView game) {
        this.board = game.getGameBoard();
        this.game = game;
        buttons = game.getButtons();
        tiles = board.getTiles();

        select = false;
        game.addListener(this);
    }

    /**
     * This overridden actionPerformed method checks the
     * buttons that are clicked and performs actions
     *
     * @param e the action event
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = -1;
        int y = -1;
        Object temp = e.getSource();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (buttons[i][j] == temp) {
                    x = j;
                    y = i;
                    break;
                }
            }
        }

        //Selected a piece
        if (x > -1 && y > -1) {

            if (tiles[y][x] instanceof Animals) {
                if (!select && ((Animals) tiles[y][x]).getColor() == board.getTurn()) {
                    select = true;
                    selectedPiece = (Animals) tiles[y][x];
                    game.highlightDirections(tiles[y][x]);

                } else if (select && ((Animals) tiles[y][x]).getColor() == board.getTurn()) {
                    select = false;
                    selectedPiece = null;
                    game.deselectHighlight();
                } else if (select && ((Animals) tiles[y][x]).getColor() != board.getTurn()) {
                    if (board.movePiece(selectedPiece, x, y)) {
                        updateView(x, y);
                    }
                }
            }
            else {
                if (select) {
                    if (board.movePiece(selectedPiece, x, y)) {
                        updateView(x, y);
                    }
                }
                game.repaint();
            }
        }
    }

    /**
     * This method updates the GUI after the player
     * moves a piece
     *
     * @param x The new X position
     * @param y The new Y position
     */
    public void updateView (int x, int y) {

        //Delete the image at the original position
        buttons[selectedPiece.getPosY()][selectedPiece.getPosX()].deleteImage();

        //Update the buttons
        game.checkAnimals();

        //set selected to false
        buttons[selectedPiece.getPosY()][selectedPiece.getPosX()].setSelected(false);

        //Set the new positions
        selectedPiece.setPosX(x);
        selectedPiece.setPosY(y);

        //Change the select mode to false
        select = false;

        //Make selected piece to null
        selectedPiece = null;

        //Remove the highlights in tiles
        game.deselectHighlight();

        //Update score
        game.setScore();

        //Update Player's turn
        board.setTurn();

        //Update the label on Player's turn
        game.setTurnMessage(board.getTurn());

        //Check if game over
        board.checkGameOver();

        //Show win message if game over
        if (board.getBlueWins() || board.getRedWins()) {
            game.disableBoard();
            game.showWinMessage();
        }
    }
    /** The mode of selection. True is a piece is selected, false if otherwise */
    private boolean select;

    /** The piece currently selected by the player */
    private Animals selectedPiece;

    /** The board used for the game */
    private Board board;

    /** The GUI for the game */
    private gameView game;

    /** The array of buttons for the board */
    private ButtonView[][] buttons;

    /** The tiles of the board */
    private Tile[][] tiles;

    /** The number of rows in the board */
    private final int ROW = 7;

    /** The number of columns in the board */
    private final int COL = 9;
}
