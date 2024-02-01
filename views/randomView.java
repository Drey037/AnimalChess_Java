package views;

import models.pickAColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The class randomView is used to represent the GUI to be
 * used for the picking of colors before starting a new game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class randomView extends JFrame {

    /**
     * This constructor initializes the layout for the GUI of the picking
     * of random pieces, the model, and the GUI for the picking of colors
     *
     * @param model The pick a color model for the GUI
     */
    public randomView(pickAColor model) {
        //JFrame
        super("Pick A Color");
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Default
        turn = true;
        this.model = model;
        pieces = model.getAnimals();

        //"Pick a color" Message
        ChoosePiece = new JPanel();
        ChoosePiece.setLayout(new FlowLayout());
        message = new JLabel("Player 1 pick a piece.");
        ChoosePiece.add(message);
        ChoosePiece.setVisible(true);
        add(ChoosePiece, BorderLayout.NORTH);

        //Pieces Panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));
        panel.setVisible(true);

        //Initializing buttons for the panel
        initImage();
        init();
        add(panel, BorderLayout.CENTER);
        addButtonsPanel();


        //Choose Color panel after the first player is chosen
        ChooseColor = new JPanel();
        ChooseColor.setLayout(new FlowLayout());
        ChooseColor.setBackground(Color.white);
        ChooseColor.setVisible(true);
        red = new JButton("Red");
        blue = new JButton("Blue");
        chooseMessage = new JLabel();
        chooseMessage.setVisible(false);
        red.setVisible(false);
        blue.setVisible(false);
        ChooseColor.add(chooseMessage, BorderLayout.CENTER);
        ChooseColor.add(red);
        ChooseColor.add(blue);
        add(ChooseColor, BorderLayout.SOUTH);


        //Pack
        pack();
        setSize(700,500);
        setLocation(400, 200);
        setVisible(true);
    }

    /**
     * This method initializes all the random buttons
     */
    public void init() {
        buttons = new ArrayList<JButton>();

        for (int i = 0; i < 8; i++) {
            buttons.add(new JButton(" "));
            buttons.get(i).setActionCommand(String.valueOf(pieces.get(i)));
            buttons.get(i).setIcon(new ImageIcon(randomPiece));
            buttons.get(i).setBorder(BorderFactory.createEmptyBorder());
            buttons.get(i).setContentAreaFilled(false);
        }
        //Shuffle the buttons
        Collections.shuffle(buttons);
    }

    /**
     * This method adds the buttons to the panel
     */
    public void addButtonsPanel() {
        for (int i = 0; i < 8; i++) {
            panel.add(buttons.get(i));
        }
    }

    /**
     * This methods adds listeners to the buttons
     *
     * @param l The action listeners to be added
     */
    public void addListeners(ActionListener l) {
        for (int i = 0; i < 8; i++) {
            buttons.get(i).addActionListener(l);
        }
        red.addActionListener(l);
        blue.addActionListener(l);
    }

    /**
     * This method sets the message after first player
     * picks a piece
     */
    public void displayMessage() {
        message.setText("Player 2 pick a piece.");
    }

    /**
     * This method initializes the images for the random pieces
     *
     * References for the images used:
     *
     * iconsPNG. (2017, December 17). Dou Shou Qi Elephant PNG icon.
     *      https://www.iconspng.com/image/22491/dou-shou-qi-elephant
     *
     * iconsPNG. (2018, January 17). Dou Shou Qi Lion PNG icon.
     *      https://www.iconspng.com/image/22492/dou-shou-qi-lion
     *
     * iconsPNG. (2018, January 11). Dou Shou Qi Tiger PNG icon.
     *      https://www.iconspng.com/image/22493/dou-shou-qi-tiger
     *
     * iconsPNG. (2018, February 12). Dou Shou Qi Leopard PNG icon.
     *      https://www.iconspng.com/image/22494/dou-shou-qi-leopard
     *
     * iconsPNG. (2017, September 15). Dou Shou Qi Dog PNG icon.
     *      https://www.iconspng.com/image/22496/dou-shou-qi-dog
     *
     * iconsPNG.(2018, January 16). Dou Shou Qi Wolf PNG icon.
     *      https://www.iconspng.com/image/22495/dou-shou-qi-wolf
     *
     * iconsPNG. (2017, December 30). Dou Shou Qi Cat PNG icon.
     *      https://www.iconspng.com/image/22497/dou-shou-qi-cat
     *
     * iconsPNG. (2018, February 10). Dou Shou Qi Rat PNG icon.
     *      https://www.iconspng.com/image/22498/dou-shou-qi-rat
     */
    public void initImage(){
        try {
            Elephant = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Elephant.png"));
            Lion = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Lion.png"));
            Tiger = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Tiger.png"));
            Leopard = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Leopard.png"));
            Dog = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Dog.png"));
            Wolf = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Wolf.png"));
            Cat = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Cat.png"));
            Mouse = ImageIO.read(getClass().getResourceAsStream("/Resources/Red Mouse.png"));
            randomPiece = ImageIO.read(getClass().getResourceAsStream("/Resources/Random Piece.png"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            Elephant = resizeImage(Elephant, 100, 100);
            Lion = resizeImage(Lion, 100, 100);
            Tiger = resizeImage(Tiger, 100, 100);
            Leopard = resizeImage(Leopard, 100, 100);
            Dog = resizeImage(Dog, 100, 100);
            Wolf = resizeImage(Wolf, 100, 100);
            Cat = resizeImage(Cat, 100, 100);
            Mouse = resizeImage(Mouse, 100, 100);
            randomPiece = resizeImage(randomPiece, 100, 100);
        }
    }

    /**
     * This method returns the current turn of which player
     * has to pick a piece
     *
     * @return The current player's turn
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * This method sets the turns of the next player
     * to pick a piece
     *
     * @param c The next player
     */
    public void setTurn(boolean c) {
        turn = c;
    }

    /**
     * This method will show the image of the chosen animal piece
     *
     * @param a The ranking of the random animal picked
     */
    public void chosePiece(int a) {
        ImageIcon img;
        if (a == 1)
            img = new ImageIcon(Mouse);
        else if (a == 2)
            img = new ImageIcon(Cat);
        else if (a == 3)
            img = new ImageIcon(Wolf);
        else if (a == 4)
            img = new ImageIcon(Dog);
        else if (a == 5)
            img = new ImageIcon(Leopard);
        else if (a == 6)
            img = new ImageIcon(Tiger);
        else if (a == 7)
            img = new ImageIcon(Lion);
        else
            img = new ImageIcon(Elephant);

        for (int i = 0; i < 8; i++) {
            if (buttons.get(i).getActionCommand().equals(String.valueOf(a))) {
                buttons.get(i).setIcon(img);
                buttons.get(i).setEnabled(false);
                break;
            }
        }
    }

    /**
     * This method resizes the image
     *
     * Reference:
     * Baeldung.(2021, May 22). How Can I Resize an Image Using Java?
     *      (https://www.baeldung.com/java-resize-image
     *
     * @param img The image to be resized
     * @param w The given width
     * @param h The given height
     * @return The resized image
     */
    public BufferedImage resizeImage(BufferedImage img, int w, int h){
        Image temp = img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        output.getGraphics().drawImage(temp, 0, 0, null);
        return output;
    }

    /**
     * This method disables all buttons when both players
     * picked a piece already
     */
    public void disableAllButtons () {
        for (int i = 0; i < 8; i++) {
            buttons.get(i).setEnabled(false);
        }
    }

    /**
     * This method displays the choose color panel and the buttons
     */
    public void displayChooseColor () {
        chooseMessage.setVisible(true);
        red.setVisible(true);
        blue.setVisible(true);
    }

    /**
     * This method sets the message of which player picked
     * the animal piece with higher rank
     *
     * @param c Boolean value of which player will go first
     */
    public void setMessage(boolean c) {
        if (c)
            chooseMessage.setText("Player 1 has the higher rank. Pick your color");
        else
            chooseMessage.setText("Player 2 has the higher rank. Pick your color");
    }

    /**
     * This method returns the pickAColor model used
     *
     * @return The pickAColor object
     */
    public pickAColor getModel() {
        return model;
    }

    /** The array list of rankings per animal piece*/
    private ArrayList<Integer> pieces;

    /** The boolean value of the current player's turn*/
    private boolean turn;

    /** The displayed message of which player's turn to pick*/
    private JLabel message;

    /** The panel that contains the buttons*/
    private JPanel panel;

    /** The images of the animal pieces*/
    private BufferedImage Elephant, Lion, Tiger, Leopard, Dog, Wolf, Cat, Mouse;

    /** The image of the hidden animal piece */
    private BufferedImage randomPiece;

    /** The panel that will contain the message for the choosing of pieces*/
    private JPanel ChoosePiece;

    /** The array list of buttons*/
    private ArrayList<JButton> buttons;

    /** The pickAColor object used*/
    private pickAColor model;

    /** The panel that will contain the label and buttons for the choosing of colors*/
    private JPanel ChooseColor;

    /** The message for the choosing of colors*/
    private JLabel chooseMessage;

    /** The button to choose red*/
    private JButton red;

    /** The button to choose blue*/
    private JButton blue;
}
