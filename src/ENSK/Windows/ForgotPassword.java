package ENSK.Windows;

import ENSK.ConnectionClass;
import ENSK.Email;
import ENSK.Username;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Created by Enathen on 2017-05-31.
 */
public class ForgotPassword extends JFrame {

    private JPanel JPanelForgotPassword;
    private JButton getNewPasswordButton;
    private JTextField emailTextField;
    private JTextField userNameTextfield;
    private JLabel incorrectEmailUsernameTextLabel;
    private ConnectionClass connection = new ConnectionClass();
    private boolean connectionHasData = false;
    public ForgotPassword() throws SQLException, ClassNotFoundException, NamingException {
        initialize();

        getNewPasswordButton.addActionListener(e -> {
            try {
                if(userNameTextfield.getText().equals("") || emailTextField.getText().equals("")){
                    incorrectEmailUsernameTextLabel.setText("<html>Email And/or <br>Username<br>Empty!</html>");
                    return;
                }
                Username username = new Username(userNameTextfield.getText());
                Email email = new Email(emailTextField.getText());
                if(username.checkIfEqualUsername()&& email.checkIfEmailExists()){
                    email.sendEmail(emailTextField.getText());
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

                }else {
                    incorrectEmailUsernameTextLabel.setText("<html>Email And/or <br>Username<br>Incorrect!</html>");

                }
            } catch (SQLException | ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (NamingException e1) {
                e1.printStackTrace();
            }
        });
        emailTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emailTextField.getText().equals("Email"))
                    emailTextField.setText("");
            }
        });
        userNameTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(userNameTextfield.getText().equals("Username"))
                    userNameTextfield.setText("");
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
