package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import models.*;
import models.Board;

/**
 * The class gameView represents the board as a GUI component
 * for the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class gameView extends JPanel{

    /**
     * This constructor initializes the board, the players, and the GUI components
     * for the board and the win message.
     *
     * @param newBoard The board object to be used
     */
    public gameView(Board newBoard){
        gameBoard = newBoard;
        this.red = newBoard.getRed();
        this.blue = newBoard.getBlue();

        frame = new JFrame("Animal Chess");
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Score and Turn part
        topInfo = new JPanel();
        topInfo.setLayout(new BorderLayout());
        score = new JLabel("    Red pieces: 8     Blue pieces: 8");
        colorTurn = new JLabel();
        setTurnMessage(gameBoard.getTurn());
        topInfo.add(score, BorderLayout.WEST);
        topInfo.add(colorTurn, BorderLayout.EAST);
        frame.add(topInfo, BorderLayout.NORTH);

        //Load BG image
        try {
            board = ImageIO.read(getClass().getResourceAsStream("/Resources/Animal Chess Gameboard.jpg"));
        }
        catch (IOException error) {
            System.out.println("Can't Find File");
        }

        //Board
        setLayout(new GridLayout(ROW, COL));
        tiles = gameBoard.getTiles();
        buttons = new ButtonView[ROW][COL];
        initButtons();
        frame.add(this, BorderLayout.CENTER);


        //Win Message - hidden by default
        endFrame = new JFrame("Game Over");
        endPanel = new JPanel();
        endPanel.setLayout(new BorderLayout());
        winMessage = new JLabel();
        winMessage.setHorizontalAlignment(SwingConstants.CENTER);
        winMessage.setVerticalAlignment(SwingConstants.CENTER);
        endPanel.add(winMessage, BorderLayout.CENTER);
        endFrame.add(endPanel);
        endFrame.setSize(200, 100);
        endFrame.setVisible(false);
        endFrame.setLocation(600, 300);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();
        frame.setSize(900, 700);
        frame.setVisible(true);
    }

    /**
     * This method initializes the buttons
     */
    public void initButtons (){
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttons[i][j] = new ButtonView();
                if (tiles[i][j] != null && tiles[i][j] instanceof Animals)
                    buttons[i][j].animalPiece(tiles[i][j]);
                add(buttons[i][j]);
            }
        }
    }

    /**
     * This method updates the buttons based on the updated tile positions
     */
    public void checkAnimals() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (tiles[i][j] != null && tiles[i][j] instanceof Animals)
                    buttons[i][j].animalPiece(tiles[i][j]);
            }
        }
    }

    /**
     * This method adds listeners to the buttons
     *
     * @param l The action listener class
     */
    public void addListener(ActionListener l) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttons[i][j].addActionListener(l);
            }
        }
    }

    /**
     * This method highlights the possible directions of the selected piece
     *
     * @param a The piece chosen
     */
    public void highlightDirections(Tile a) {
        if (gameBoard.canGoUp(a)) {
            if (gameBoard.checkUpTile(a) != null && gameBoard.checkUpTile(a) instanceof River && (a instanceof Tiger || a instanceof Lion)) {
                buttons[a.getPosY() - 3][a.getPosX()].setBorderPainted(true);
                buttons[a.getPosY() - 3][a.getPosX()].setBorder(BorderFactory.createLineBorder(color, 3, true));
            } else {
                buttons[a.getPosY() - 1][a.getPosX()].setBorderPainted(true);
                buttons[a.getPosY() - 1][a.getPosX()].setBorder(BorderFactory.createLineBorder(color, 3, true));
            }
        }
        if (gameBoard.canGoDown(a)) {
            if (gameBoard.checkDownTile(a) != null && gameBoard.checkDownTile(a) instanceof River && (a instanceof Tiger || a instanceof Lion)) {
                buttons[a.getPosY() + 3][a.getPosX()].setBorderPainted(true);
                buttons[a.getPosY() + 3][a.getPosX()].setBorder(BorderFactory.createLineBorder(color, 3, true));
            } else {
                buttons[a.getPosY() + 1][a.getPosX()].setBorderPainted(true);
                buttons[a.getPosY() + 1][a.getPosX()].setBorder(BorderFactory.createLineBorder(color, 3, true));
            }
        }
        if (gameBoard.canGoRight(a)) {
            if (gameBoard.checkRightTile(a) != null && gameBoard.checkRightTile(a) instanceof River && (a instanceof Tiger || a instanceof Lion)) {
                buttons[a.getPosY()][a.getPosX() + 4].setBorderPainted(true);
                buttons[a.getPosY()][a.getPosX() + 4].setBorder(BorderFactory.createLineBorder(color, 3, true));
            } else {
                buttons[a.getPosY()][a.getPosX() + 1].setBorderPainted(true);
                buttons[a.getPosY()][a.getPosX() + 1].setBorder(BorderFactory.createLineBorder(color, 3, true));
            }
        }
        if (gameBoard.canGoLeft(a)) {
            if (gameBoard.checkLeftTile(a) != null && gameBoard.checkLeftTile(a) instanceof River && (a instanceof Tiger || a instanceof Lion)) {
                buttons[a.getPosY()][a.getPosX() - 4].setBorderPainted(true);
                buttons[a.getPosY()][a.getPosX() - 4].setBorder(BorderFactory.createLineBorder(color, 3, true));
            } else {
                buttons[a.getPosY()][a.getPosX() - 1].setBorderPainted(true);
                buttons[a.getPosY()][a.getPosX() - 1].setBorder(BorderFactory.createLineBorder(color, 3, true));
            }
        }
    }

    /**
     * This method removes the highlights after a move or when a player chooses
     * a different piece
     */
    public void deselectHighlight () {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttons[i][j].setBorderPainted(false);
            }
        }
    }

    /**
     * This method sets the turn label after every move
     *
     * @param turn The next turn of the game after the other player's move
     */
    public void setTurnMessage(boolean turn) {
        if (turn) {
            colorTurn.setText("Red Player's turn    ");
        }
        else
            colorTurn.setText("Blue Player's turn    ");
    }

    /**
     * This method updates the score message
     *
     */
    public void setScore() {
        score.setText("    Red pieces: " + gameBoard.getNumRedPieces() + "     Blue pieces: " + gameBoard.getNumBluePieces());
    }

    /**
     * This method paints the the GUI components of the board
     *
     * @param g The Graphics class object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(board, 0, 0, getWidth(), getHeight(), null);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttons[i][j].repaint();
            }
        }
    }

    /**
     * This method disables all the buttons when the game is over
     */
    public void disableBoard() {
        for (int i = 0; i< ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    /**
     * This method sets the win message when the winning condition is satisfied
     */
    public void showWinMessage() {
        if (gameBoard.getBlueWins()) {
            winMessage.setText("Blue Player Wins!");
        }
        if (gameBoard.getRedWins()) {
            winMessage.setText("Red Player Wins!");
        }
        endFrame.setVisible(true);
    }

    /**
     * This method returns the tile of buttons
     *
     * @return The 2D array of buttons
     *
     */
    public ButtonView[][] getButtons() {
        return buttons;
    }

    /**
     * This method returns the gameboard
     *
     * @return The gameboard used for the current game
     *
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /** The number of rows of the board*/
    private final int ROW = 7;

    /** The number of columns of the board*/
    private final int COL = 9;


    /** The top information panel */
    private JPanel topInfo;

    /** The score information */
    private JLabel score;

    /** The color's turn information*/
    private JLabel colorTurn;

    /** The image for the board*/
    private BufferedImage board;

    /** The array of buttons*/
    private ButtonView[][] buttons;

    /** The frame of the game*/
    private JFrame frame;

    /** The board used for the game*/
    private Board gameBoard;

    /** The array of tiles of the baord*/
    private Tile[][] tiles;

    /** The red player */
    private Player red;

    /** The blue player */
    private Player blue;

    /** The color for the borders*/
    private Color color = Color.yellow;

    /** The frame for the game over window */
    private JFrame endFrame;

    /** The Game over panel where information will be shown*/
    private JPanel endPanel;

    /** The win message */
    private JLabel winMessage;
}
