package ENSK.Windows.Change;

import ENSK.Windows.Design.SetDesignCorrectly;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-07-06.
 */
public class ChangeEmail extends JFrame{
    private JTextField newEmailTextField;
    private JLabel emailIncorrectLabel;
    private JLabel passwordIncorrectLabel;
    private JLabel noConnectionLabel;
    private JLabel changeEmailLabel;
    private JPasswordField passwordField;
    private JPanel JPanelChangeEmail;

    public ChangeEmail(){
        initialize();
    }

    private void initialize() {
        JPanelChangeEmail.setMinimumSize(new Dimension(540,280));
        JPanelChangeEmail.setBackground(Color.white);
        setContentPane(JPanelChangeEmail);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(changeEmailLabel, 0.8);
        noConnectionLabel.setVisible(false);

        pack();
        setResizable(false);
    }
}
