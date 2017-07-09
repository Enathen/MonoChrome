package ENSK.Windows.Login;

import ENSK.Windows.Connection.ConnectionAccount;
import ENSK.Windows.Account.Email;
import ENSK.Windows.Account.Username;
import ENSK.Windows.Design.SetDesignCorrectly;

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
    private JLabel getNewPasswordLabel;
    private JLabel noConnectionLabel;

    public ForgotPassword() throws SQLException, ClassNotFoundException, NamingException {
        initialize();

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
        getNewPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {

                    if(userNameTextfield.getText().isEmpty() || emailTextField.getText().isEmpty()){
                        incorrectEmailUsernameTextLabel.setText("<html>Email And/or <br>Username<br>Empty!</html>");
                        return;
                    }
                    Username username = new Username(userNameTextfield.getText());
                    Email email = new Email(emailTextField.getText());
                    ConnectionAccount connection = new ConnectionAccount(noConnectionLabel);
                    if(checkIfUsernameAndEmailExists(connection)){
                        email.sendEmail(emailTextField.getText(), connection);
                        dispose();


                    }else {
                        incorrectEmailUsernameTextLabel.setText("<html>Email And/or <br>Username<br>Incorrect!</html>");

                    }
                    connection.close();
                } catch (SQLException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (NamingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * starts the form.
     */
    private void initialize(){
        JPanelForgotPassword.setMinimumSize(new Dimension(540,280));
        JPanelForgotPassword.setBackground(Color.white);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(getNewPasswordLabel,0.8);

        setContentPane(JPanelForgotPassword);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);
        setResizable(false);
    }
    private boolean checkIfUsernameAndEmailExists(ConnectionAccount connection) throws SQLException, ClassNotFoundException, NamingException {
        Username username = new Username(userNameTextfield.getText());
        String sql = "select email from Account where userName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username.userNameCorrect());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
                if(resultSet.getString(1).equals(emailTextField.getText())){
                    connection.close(resultSet, statement);

                    return true;
                }
            connection.close(resultSet, statement);
            return false;

        }

        return false;
    }
}
