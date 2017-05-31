package ENSK.Windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-05-31.
 */
public class ForgotPassword extends JFrame {

    private JPanel JPanelForgotPassword;
    private JButton GetNewPassword;
    private JTextField Email;

    public ForgotPassword(){
        initialize();
    }
    /**
     * starts the form.
     */
    private void initialize(){
        JPanelForgotPassword.setMinimumSize(new Dimension(540,280));

        setContentPane(JPanelForgotPassword);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
