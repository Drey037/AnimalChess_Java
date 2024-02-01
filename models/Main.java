package models;

import views.*;
import Controllers.*;

/**
 * The class Main is to contain the main method
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class Main {

    /**
     * The main method for the program
     */
    public static void main(String[] args) {
        startController start = new startController(new startView());
    }
}
