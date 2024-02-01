package Controllers;

import models.pickAColor;
import views.randomView;
import views.startView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class startController is used as a controller
 * for the start window
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class startController implements ActionListener {

    /**
     * This constructor initializes the GUI for the start
     *
     * @param start the GUI for the start window
     *
     */
    public startController(startView start) {
        startGui = start;

        startGui.addListener(this);
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
        if (e.getActionCommand().equals("Start Game")) {
            pickATurnController random = new pickATurnController(new randomView(new pickAColor()));
        }
    }

    /** The GUI for the start window */
    private startView startGui;
}
