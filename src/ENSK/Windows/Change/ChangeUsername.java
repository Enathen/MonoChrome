package ENSK.Windows.Change;

import ENSK.Windows.Design.SetDesignCorrectly;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-07-06.
 */
public class ChangeUsername extends JFrame{
    private JLabel changeUsernameLabel;
    private JLabel UsernameIncorrectLabel;
    private JLabel passwordIncorrectLabel;
    private JLabel noConnectionLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPanel JPanelUsername;

    public ChangeUsername(){
        initialize();
    }

    private void initialize() {
        JPanelUsername.setMinimumSize(new Dimension(540,280));
        JPanelUsername.setBackground(Color.white);
        setContentPane(JPanelUsername);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(changeUsernameLabel, 0.8);
        noConnectionLabel.setVisible(false);

        pack();
        setResizable(false);
    }
}
