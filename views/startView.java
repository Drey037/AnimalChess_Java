package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The class startView is used to represent the GUI
 * for the start game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class startView {

    /**
     * This constructor initializes the GUI components
     * and layout for the start game GUI
     *
     */
    public startView() {
        frame = new JFrame("Animal Chess");
        frame.setLocation(600, 300);
        frame.setSize(300, 100);
        frame.setResizable(false);

        init();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * This method initializes the panel and the components
     */
    private void init() {
        JPanel start = new JPanel();

        startGame = new JButton("Start Game");
        start.setLayout(new FlowLayout());

        start.add(startGame);

        frame.add(start, BorderLayout.CENTER);
    }

    /**
     * This method adds an action listener to the start button
     *
     * @param l The action listener
     */
    public void addListener(ActionListener l) {
        startGame.addActionListener(l);
    }

    /** The frame for the start window*/
    private JFrame frame;

    /** The button to start game*/
    private JButton startGame;
}
