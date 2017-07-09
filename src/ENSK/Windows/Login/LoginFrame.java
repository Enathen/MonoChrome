package ENSK.Windows.Login;

import ENSK.Windows.Connection.ConnectionAccount;
import ENSK.Windows.Account.Password;
import ENSK.Windows.Account.Username;
import ENSK.Windows.Design.SetDesignCorrectly;
import ENSK.Windows.StartFrame;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Created by Enathen on 2017-05-26.
 */
public class LoginFrame extends JFrame{
    private JPasswordField passwordPasswordField;
    private JFormattedTextField usernameFormattedTextField;
    private JButton menuButton;
    private JButton createNewAccountButton;
    private JPanel JPanelLoginFrame;

    private JLabel welcomeLabel;
    private JLabel userPasswordWrong;
    private JLabel forgotPasswordLabel;
    private JLabel loginLabel;
    private JLabel createNewAccountLabel;
    private JLabel menuLabel;
    private JPanel JPanelButtons;
    private JLabel loginErrorLabel;
    private ForgotPassword forgotPassword = new ForgotPassword();
    private CreateNewAccount createNewAccount = new CreateNewAccount();
    public LoginFrame() throws SQLException, ClassNotFoundException, NamingException {
        initialize();
        welcomeLabel.setFont(new Font("default", Font.PLAIN, 20));
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.CENTER);
        welcomeLabel.setText("<html>Welcome!<br>Please sign in below:</html>");
        /**
         * when pressing login setButtonCorrectly check if password is correct and user exist in account database.
         */
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if(checkIfUsernameAndPasswordExists()){
                        Username username = new Username(usernameFormattedTextField.getText());
                        createNewAccount.dispose();
                        forgotPassword.dispose();
                        dispose ();
                        new StartFrame(username.userNameCorrect()).setVisible(true);

                    }
                } catch (SQLException | ClassNotFoundException | NamingException e1) {
                    e1.printStackTrace();
                }
                userPasswordWrong.setText("<html>Username and/or <br>password are wrong!</html>");
            }
        });


        usernameFormattedTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(usernameFormattedTextField.getText().equals("Username"))
                usernameFormattedTextField.setText("");
            }
        });
        passwordPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(String.valueOf(passwordPasswordField.getPassword()).equals("Password"))
                passwordPasswordField.setText("");
            }
        });


        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                    forgotPassword.setVisible(true);

            }
        });
        createNewAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                createNewAccount.setVisible(true);

            }
        });
    }
    private void initialize(){
        JPanelLoginFrame.setMinimumSize(new Dimension(540,280));
        JPanelLoginFrame.setBackground(Color.white);
        JPanelButtons.setBackground(Color.white);

        try {
            SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();

            setDesignCorrectly.setDesignBlue(menuLabel, 1);
            setDesignCorrectly.setDesignOrange(forgotPasswordLabel, 0.5);
            setDesignCorrectly.setDesignLime(loginLabel, 0.5);
            setDesignCorrectly.setDesignOrange(createNewAccountLabel, 1);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        setContentPane(JPanelLoginFrame);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);

    }
    /**
     * Check if username already exist in database
     * @return true if there already exist a user false if no user exust.
     * @throws SQLException if database incorrect
     * @throws ClassNotFoundException if class not found
     */
    private boolean checkIfUsernameAndPasswordExists() throws SQLException, ClassNotFoundException, NamingException {
        Password saltAndHashPassword = new Password(String.valueOf(passwordPasswordField.getPassword()));
        ConnectionAccount connection = new ConnectionAccount(loginErrorLabel);
        Username username = new Username(usernameFormattedTextField.getText());
        String sql = "select saltedHash, hash from Account where userName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username.userNameCorrect());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){

            saltAndHashPassword.setSalt(resultSet.getString(1));

            if(saltAndHashPassword.createHash().equals(resultSet.getString(2))){
                connection.close(resultSet, statement);
                connection.close();
                return true;
            }
            connection.close(resultSet, statement);
            connection.close();
            return false;
        }
        connection.close();
        return false;
    }
}
