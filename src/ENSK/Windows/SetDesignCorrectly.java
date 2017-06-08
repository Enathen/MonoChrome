package ENSK.Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Enathen on 2017-06-06.
 */
public class SetDesignCorrectly {

    public void setButtonCorrectly(String url, JLabel label){
        try {
            Image img = ImageIO.read(getClass().getResource(url));
            label.setIcon(new ImageIcon(img));
            label.setHorizontalTextPosition(JButton.CENTER);
            label.setVerticalTextPosition(JButton.CENTER);
            label.setOpaque(false);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void setButtonCorrectly(Image img, JLabel label){
        try {
            label.setIcon(new ImageIcon(img));
            label.setHorizontalTextPosition(JButton.CENTER);
            label.setVerticalTextPosition(JButton.CENTER);
            label.setOpaque(false);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /**
     * sets the buttons to color orange
     * @param label to change
     */
    public void setDesignOrange(JLabel label, double size){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/Button.png", size);
        Image imgClicked = imageResize.resize("/res/buttonClicked.png", size);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }
    /**
     * sets the buttons to color orange
     * @param label to change
     */
    public void setDesignOrange(JLabel label, int width, int height){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/Button.png", width, height);
        Image imgClicked = imageResize.resize("/res/buttonClicked.png", width, height);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }
    /**
     * sets the buttons to color blue
     * @param label to change
     */
    public void setDesignBlue(JLabel label, double size){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/blueButton.png", size);
        Image imgClicked = imageResize.resize("/res/blueButtonClicked.png", size);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }

    /**
     * sets the buttons to color blue
     * @param label to change
     */
    public void setDesignBlue(JLabel label, int width, int height){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/blueButton.png", width, height);
        Image imgClicked = imageResize.resize("/res/blueButtonClicked.png", width, height);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }
    /**
     * sets the buttons to color lime
     * @param label to change
     */
    public void setDesignLime(JLabel label, double size){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/limeButton.png", size);
        Image imgClicked = imageResize.resize("/res/limeButtonClicked.png", size);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }
    public void setDesignLime(JLabel label, int width, int height){
        ImageResize imageResize = new ImageResize();
        Image img = imageResize.resize("/res/limeButton.png", width, height);
        Image imgClicked = imageResize.resize("/res/limeButtonClicked.png", width, height);
        SetDesignCorrectly setButton = new SetDesignCorrectly();
        setButton.setButtonCorrectly(img, label);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SetDesignCorrectly SetDesignCorrectly = new SetDesignCorrectly();

                SetDesignCorrectly.setButtonCorrectly(imgClicked, label);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setButton = new SetDesignCorrectly();
                setButton.setButtonCorrectly(img, label);
            }
        });
    }

}
