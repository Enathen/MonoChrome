package ENSK.Windows.Change;

import ENSK.Windows.Design.SetDesignCorrectly;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-07-06.
 */
public class ChangePassword extends JFrame{
    private JLabel changePasswordLabel;
    private JLabel newPasswordIncorrectLabel;
    private JLabel oldPasswordIncorrectLabel;
    private JLabel noConnectionLabel;
    private JPasswordField passwordFieldNew2;
    private JPasswordField passwordFieldNew1;
    private JPasswordField passwordFieldOld;
    private JPanel JPanelPassword;

    public ChangePassword(){
        initialize();
    }

    private void initialize() {
        JPanelPassword.setMinimumSize(new Dimension(540,280));
        JPanelPassword.setBackground(Color.white);
        setContentPane(JPanelPassword);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(changePasswordLabel, 0.8);
        noConnectionLabel.setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
    }
}
