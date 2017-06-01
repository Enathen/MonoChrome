package ENSK.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Enathen on 2017-05-31.
 */
public class ForgotPassword extends JFrame {

    private JPanel JPanelForgotPassword;
    private JButton getNewPasswordButton;
    private JTextField emailTextField;
    private JTextField userNameTextfield;
    private JLabel incorrectEmailUsernameTextLabel;
    private static Connection connection;
    private boolean connectionHasData = false;
    public ForgotPassword(){
        initialize();

        getNewPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
