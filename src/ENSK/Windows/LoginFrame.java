package ENSK.Windows;

import ENSK.ConnectionClass;
import ENSK.SaltAndHashPassword;
import ENSK.Username;

import javax.imageio.ImageIO;
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
    private ConnectionClass connection = new ConnectionClass();

    public LoginFrame() throws SQLException, ClassNotFoundException, NamingException {
        initialize();
        welcomeLabel.setFont(new Font("default", Font.PLAIN, 20));
        welcomeLabel.setText("<html>Welcome!<br>Please sign in below:</html>");
        /**
         * when pressing login setButtonCorrectly check if password is correct and user exist in account database.
         */
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if(checkIfUsernameAndPasswordExists()){
                        new StartFrame().setVisible(true);
                        dispose ();
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
                try {
                    new ForgotPassword().setVisible(true);
                } catch (SQLException | ClassNotFoundException | NamingException e1) {
                    e1.printStackTrace();
                }
            }
        });
        createNewAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new CreateNewAccount().setVisible(true);
                } catch (SQLException | ClassNotFoundException | NamingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    private void initialize(){
        JPanelLoginFrame.setMinimumSize(new Dimension(540,280));
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
        SaltAndHashPassword saltAndHashPassword = new SaltAndHashPassword(String.valueOf(passwordPasswordField.getPassword()));
        if(connection == null){
            connection.getConnection();
        }
        Username username = new Username(usernameFormattedTextField.getText());
        String sql = "select saltedHash, hash from Account where userName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username.userNameCorrect());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            saltAndHashPassword.setSalt(resultSet.getString(1));

            if(saltAndHashPassword.createHash().equals(resultSet.getString(2))){
                connection.close(resultSet, statement);
                return true;
            }
            return false;
        }
        return false;
    }
}
