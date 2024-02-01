package Controllers;

import models.Board;
import models.pickAColor;
import views.gameView;
import views.randomView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class pickATurnController is used as a controller for
 * the picking of colors GUI and model
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class pickATurnController implements ActionListener {

    /**
     * This constructor initializes the GUI and model for the picking of colors
     * part before starting a game
     *
     * @param view the GUI for the picking of colors window
     *
     */
    public pickATurnController(randomView view) {
        this.view = view;
        panel = view.getModel();

        view.addListeners(this);
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
        if (e.getActionCommand().equals("1")) {
            if (view.getTurn()) {
                panel.setPlayerOne(1);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(1);
                updateView();
            }
            view.chosePiece(1);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("2")) {
            if (view.getTurn()) {
                panel.setPlayerOne(2);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(2);
                updateView();
            }
            view.chosePiece(2);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("3")) {
            if (view.getTurn()) {
                panel.setPlayerOne(3);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(3);
                updateView();
            }
            view.chosePiece(3);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("4")) {
            if (view.getTurn()) {
                panel.setPlayerOne(4);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(4);
                updateView();
            }
            view.chosePiece(4);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("5")) {
            if (view.getTurn()) {
                panel.setPlayerOne(5);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(5);
                updateView();
            }
            view.chosePiece(5);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("6")) {
            if (view.getTurn()) {
                panel.setPlayerOne(6);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(6);
                updateView();
            }
            view.chosePiece(6);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("7")) {
            if (view.getTurn()) {
                panel.setPlayerOne(7);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(7);
                updateView();
            }
            view.chosePiece(7);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("8")) {
            if (view.getTurn()) {
                panel.setPlayerOne(8);
                view.displayMessage();
            }
            else {
                panel.setPlayerTwo(8);
                updateView();
            }
            view.chosePiece(8);
            view.setTurn(false);
        }

        if (e.getActionCommand().equals("Red")) {
            boardController control = new boardController(new gameView(new Board(true)));
            view.setVisible(false);
        }

        if (e.getActionCommand().equals("Blue")) {
            boardController control = new boardController(new gameView(new Board(false)));
            view.setVisible(false);
        }
    }

    /**
     * This method checks the updates the panel view after
     * performing an action
     *
     */
    public void updateView() {
        if ((panel.getPlayerOne() > panel.getPlayerTwo())) {
            if (panel.getPlayerOne() == 8 && panel.getPlayerTwo() == 1)
                view.setMessage(false);
            else
                view.setMessage(true);
        }
        else {
            if (panel.getPlayerTwo() == 8 && panel.getPlayerOne() == 1)
                view.setMessage(true);
            else
                view.setMessage(false);
        }
        view.disableAllButtons();
        view.displayChooseColor();
    }

    /** The GUI for the picking of colors window */
    private randomView view;

    /** The model for the picking of colors */
    private pickAColor panel;
}
