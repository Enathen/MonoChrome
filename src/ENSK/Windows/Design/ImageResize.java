package ENSK.Windows.Design;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Created by Enathen on 2017-06-08.
 */

public class ImageResize {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)

     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public Image resize(String inputImagePath, int scaledWidth, int scaledHeight) {
        try {
            Image img = ImageIO.read(getClass().getResource(inputImagePath));
            return img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public Image resize(String inputImagePath, double percent)  {

        Image input = null;
        try {
            input = ImageIO.read(getClass().getResource(inputImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage buffered = (BufferedImage) input;
        int scaledWidth = (int) (buffered.getWidth() * percent);
        int scaledHeight = (int) (buffered.getHeight() * percent);
        return resize(inputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Test resizing images
     */


}
