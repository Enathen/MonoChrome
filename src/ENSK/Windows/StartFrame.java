package ENSK.Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Enathen on 2017-06-06.
 */
public class StartFrame extends JFrame{
    private JPanel JPanelStartFrame;



    private JLabel upperLeftLabel;
    private JLabel lowerLeftLabel;
    private JLabel upperRightLabel;
    private JLabel lowerRightLabel;
    private JLabel stapleLeftLabel;
    private JLabel stapleRightLabel;

    private JLabel addArticleLabel;
    private JLabel removeArticleLabel;
    private JLabel statisticsLabel;
    private JLabel stockLabel;
    private JLabel tipsLabel;
    private JLabel expiringItemsLabel;
    private JLabel whatLabel;

    private JLabel changeEmailLabel;
    private JLabel changeWorkLabel;
    private JLabel changeUsernameLabel;
    private JLabel changePasswordLabel;

    private JLabel profileLabel;
    private JLabel menuLabel;
    private JLabel logoutLabel;

    private JLabel generalSettingLabel;
    private JLabel startLabel;
    private JLabel feedbackLabel;
    private JLabel crewLabel;

    public StartFrame(){
        initialize();

        /**
         * when clicking menuButton button show more buttons
         */
        menuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!generalSettingLabel.isVisible()){
                    generalSettingLabel.setVisible(true);
                    startLabel.setVisible(true);
                    feedbackLabel.setVisible(true);
                    crewLabel.setVisible(true);
                }else {
                    generalSettingLabel.setVisible(false);
                    startLabel.setVisible(false);
                    feedbackLabel.setVisible(false);
                    crewLabel.setVisible(false);
                }



            }
        });

        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!changePasswordLabel.isVisible()) {
                    changeEmailLabel.setVisible(true);
                    changePasswordLabel.setVisible(true);
                    changeWorkLabel.setVisible(true);
                    changeUsernameLabel.setVisible(true);
                }else {
                    changeEmailLabel.setVisible(false);
                    changePasswordLabel.setVisible(false);
                    changeWorkLabel.setVisible(false);
                    changeUsernameLabel.setVisible(false);

                }
            }
        });
    }

    /**
     * set up the design.
     */
    private void initialize(){
        JPanelStartFrame.setMinimumSize(new Dimension(540,280));


        setContentPane(JPanelStartFrame);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();



        setDesignCorrectly.setDesignLime(addArticleLabel,1);
        setDesignCorrectly.setDesignLime(statisticsLabel,1);
        setDesignCorrectly.setDesignLime(stockLabel,1);
        setDesignCorrectly.setDesignLime(removeArticleLabel,1);
        setDesignCorrectly.setDesignLime(expiringItemsLabel,1);
        setDesignCorrectly.setDesignLime(tipsLabel,1);
        setDesignCorrectly.setDesignLime(whatLabel,1);

        setDesignCorrectly.setDesignBlue(generalSettingLabel,1);
        setDesignCorrectly.setDesignBlue(startLabel,1);
        setDesignCorrectly.setDesignBlue(feedbackLabel,1);
        setDesignCorrectly.setDesignBlue(crewLabel,1);
        setDesignCorrectly.setDesignBlue(menuLabel,1);
        generalSettingLabel.setVisible(false);
        startLabel.setVisible(false);
        feedbackLabel.setVisible(false);
        crewLabel.setVisible(false);


        setDesignCorrectly.setDesignOrange(profileLabel,1);
        setDesignCorrectly.setDesignOrange(changeUsernameLabel,1);
        setDesignCorrectly.setDesignOrange(changePasswordLabel,1);
        setDesignCorrectly.setDesignOrange(changeEmailLabel,1);
        setDesignCorrectly.setDesignOrange(changeWorkLabel,1);

        changeUsernameLabel.setVisible(false);
        changePasswordLabel.setVisible(false);
        changeEmailLabel.setVisible(false);
        changeWorkLabel.setVisible(false);

        setDesignCorrectly.setDesignBlue(logoutLabel,1);





        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println(getContentPane());



        try {
            Image img = null;
            upperLeftLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/res/upperLeft.png"))));
            lowerLeftLabel.setIcon(new ImageIcon( ImageIO.read(getClass().getResource("/res/lowerLeft.png"))));
            upperRightLabel.setIcon(new ImageIcon( ImageIO.read(getClass().getResource("/res/upperRight.png"))));
            lowerRightLabel.setIcon(new ImageIcon( ImageIO.read(getClass().getResource("/res/lowerRight.png"))));
            stapleLeftLabel.setIcon(new ImageIcon( ImageIO.read(getClass().getResource("/res/stapleLeft.png"))));
            stapleRightLabel.setIcon(new ImageIcon( ImageIO.read(getClass().getResource("/res/stapleRight.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pack();
        setVisible(true);
        setResizable(false);


    }
}
