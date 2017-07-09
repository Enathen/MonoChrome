package ENSK.Windows;

import ENSK.Windows.Article.AddArticleFrame;
import ENSK.Windows.Change.ChangeEmail;
import ENSK.Windows.Change.ChangePassword;
import ENSK.Windows.Change.ChangeUsername;
import ENSK.Windows.Change.ChangeWorkplace;
import ENSK.Windows.Design.SetDesignCorrectly;
import ENSK.Windows.Login.LoginFrame;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
    private JLabel welcomeLabel;
    private JLabel changeArticleLabel;
    private JLabel printSignsLabel;

    String userName;
    private AddArticleFrame addArticleFrame = new AddArticleFrame();
    private ChangeEmail changeEmailFrame = new ChangeEmail();
    private ChangeWorkplace changeWorkplaceFrame = new ChangeWorkplace();
    private ChangeUsername changeUsernameFrame = new ChangeUsername();
    private ChangePassword changePasswordFrame = new ChangePassword();

    public StartFrame(String userName) {
        this.userName = userName;

        System.out.println(userName);

        welcomeLabel.setFont(new Font("default", Font.BOLD, 20));
        welcomeLabel.setText("Welcome "+ userName + "!");
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
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new LoginFrame().setVisible(true);
                    dispose();
                } catch (SQLException | ClassNotFoundException | NamingException e1) {
                    e1.printStackTrace();
                }
            }
        });
        addArticleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                addArticleFrame.setVisible(true);
            }
        });

        changePasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changePasswordFrame.setVisible(true);
            }
        });
        changeEmailLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changeEmailFrame.setVisible(true);
            }
        });
        changeUsernameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changeUsernameFrame.setVisible(true);
            }
        });
        changeWorkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changeWorkplaceFrame.setVisible(true);
            }
        });
    }

    /**
     * set up the design.
     */
    private void initialize(){
        JPanelStartFrame.setMinimumSize(new Dimension(540,280));
        JPanelStartFrame.setBackground(Color.white);

        setContentPane(JPanelStartFrame);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();



        setDesignCorrectly.setDesignLime(addArticleLabel,1);
        setDesignCorrectly.setDesignLime(statisticsLabel,1);
        setDesignCorrectly.setDesignLime(stockLabel,1);
        setDesignCorrectly.setDesignLime(removeArticleLabel,1);
        setDesignCorrectly.setDesignLime(changeArticleLabel,1);
        setDesignCorrectly.setDesignLime(printSignsLabel,1);
        setDesignCorrectly.setDesignLime(expiringItemsLabel,1);
        setDesignCorrectly.setDesignLime(tipsLabel,1);
        setDesignCorrectly.setDesignLime(whatLabel,1);

        setDesignCorrectly.setDesignBlue(menuLabel,1);
        setDesignCorrectly.setDesignBlue(generalSettingLabel,1);
        setDesignCorrectly.setDesignBlue(startLabel,1);
        setDesignCorrectly.setDesignBlue(feedbackLabel,1);
        setDesignCorrectly.setDesignBlue(crewLabel,1);


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
        setDesignCorrectly.setButtonCorrectly("/res/upperLeft.png", upperLeftLabel);
        setDesignCorrectly.setButtonCorrectly("/res/lowerLeft.png", lowerLeftLabel);
        setDesignCorrectly.setButtonCorrectly("/res/upperRight.png", upperRightLabel);
        setDesignCorrectly.setButtonCorrectly("/res/lowerRight.png", lowerRightLabel);
        setDesignCorrectly.setButtonCorrectly("/res/stapleLeft.png", stapleLeftLabel);
        setDesignCorrectly.setButtonCorrectly("/res/stapleRight.png", stapleRightLabel);
        pack();
        setVisible(true);
        setResizable(false);


    }
}
