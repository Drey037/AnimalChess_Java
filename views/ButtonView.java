package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import models.*;
import models.Animals;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * The class ButtonView represents the GUI component buttons
 * for the game
 *
 * @author Hyenne Lim
 * @version 1.0
 */
public class ButtonView extends JButton {

    /**
     * This constructor initializes the button and its borders
     */
    public ButtonView() {
        super();
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }

    /**
     * This method initializes the image of the button
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
     *
     * @param a The animal image to be initialized
     */
    public void imageAnimal(Animals a){
        if (a != null) {
            try {
                image = ImageIO.read(getClass().getResourceAsStream(a.getImgFile()));
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
            image = resizeImage(image, 100, 100);
        }
    }

    /**
     * This method initializes the image if the animal is alive
     *
     * @param a The tile in which the animal image will be initialized
     */
    public void animalPiece (Tile a){
            imageAnimal((Animals) a);
            isAlive = true;
    }


    /**
     * This overridden method paints the button if it's an animal piece
     * and highlights when selected
     *
     * @param a The Graophics class
     */
    @Override
    public void paintComponent(Graphics a) {
        if (isAlive) {
            a.drawImage(image, 10, 10, 75, 75, null);
        }
    }

    /**
     * This method deletes the image in the original position after moving a piece
     */
    public void deleteImage() {
        image = null;
        isAlive = false;
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

    /** The image for the button*/
    private BufferedImage image;

    /** The boolean variable for status of the animal piece */
    private boolean isAlive;
}
