package ENSK.Windows.Login;

import ENSK.Windows.Connection.ConnectionAccount;
import ENSK.Windows.Account.Email;
import ENSK.Windows.Account.Password;
import ENSK.Windows.Account.Username;
import ENSK.Windows.Design.SetDesignCorrectly;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;


/**
 * Created by Enathen on 2017-05-26.
 */
public class CreateNewAccount extends JFrame{

    private JPanel JPanelCreateNewAccount;
    private JButton createAccountButton;
    private JTextField emailTextField;
    private JTextField usernameTextField;
    private JCheckBox administratorCheckBox;
    private JPasswordField passwordPasswordField;
    private JPasswordField passwordPasswordField1;
    private JComboBox comboBox1;
    private JLabel emailIncorrectLabel;
    private JLabel passwordIncorrectLabel;
    private JLabel createAccountIncorrect;
    private JLabel userNameIncorrectLabel;
    private JLabel createAccountLabel;
    private JLabel noConnectionLabel;

    /**
     * creates a new account
     */
    public CreateNewAccount() throws SQLException, ClassNotFoundException, NamingException {
        initialize();
        comboBox1.addItem("Coop Forum Ersboda");
        comboBox1.addItem("Coop Forum Haninge");
        comboBox1.addItem("Coop Ålidhem");
        comboBox1.addItem("ICA Supermarket Strömpilen");

        /**
         * when create account setButtonCorrectly listener try to create a new account.
         */
        emailTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emailTextField.getText().equals("Email"))
                emailTextField.setText("");
            }
        });
        usernameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(usernameTextField.getText().equals("Username"))
                usernameTextField.setText("");
            }
        });
        passwordPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(String.valueOf(passwordPasswordField.getPassword()).equals("Password"))
                passwordPasswordField.setText("");
            }
        });
        passwordPasswordField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(String.valueOf(passwordPasswordField1.getPassword()).equals("password"))
                passwordPasswordField1.setText("");
            }
        });
        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                boolean working = true;
                Password password = new Password();
                if(!password.passwordCorectFormat(passwordPasswordField,passwordPasswordField1,passwordIncorrectLabel)){
                    working = false;
                }
                Username username = new Username(usernameTextField.getText());
                if(!username.userNameCorrectFormat(userNameIncorrectLabel)){
                    working = false;
                }

                Email email = new Email(emailTextField.getText());
                if(!email.EmailCorrectFormat(emailIncorrectLabel)){
                    working = false;
                }
                if(working) {
                    ConnectionAccount connection = new ConnectionAccount(noConnectionLabel);
                    if (email.checkIfEmailExists(connection)) {
                        emailIncorrectLabel.setText("<html>Email already<br> Exists!</html>");
                        emailIncorrectLabel.setVisible(true);
                        working = false;
                    }

                    if (working) {
                        addUser(connection);
                    } else {
                        createAccountIncorrect.setVisible(true);
                    }
                    connection.close();
                }
                else{
                    createAccountIncorrect.setVisible(true);
                }

            }
        });
    }

    /**
     * starts the form.
     */
    private void initialize(){
        JPanelCreateNewAccount.setMinimumSize(new Dimension(540,280));
        JPanelCreateNewAccount.setBackground(Color.white);
        administratorCheckBox.setBackground(Color.white);
        setContentPane(JPanelCreateNewAccount);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(createAccountLabel,1);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    /**
     * add a user.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addUser(ConnectionAccount connection)  {
        Username username = new Username(usernameTextField.getText());

        if(!username.checkIfEqualUsername(connection)){
            String sql = "INSERT INTO Account (userName, email,workstation,admin, saltedHash, hash) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username.userNameCorrect());
                preparedStatement.setString(2, emailTextField.getText().toLowerCase());

                if(String.valueOf(comboBox1.getSelectedItem()).endsWith("you work?")){
                    preparedStatement.setString(3,null);
                }
                else {
                    preparedStatement.setString(3, String.valueOf(comboBox1.getSelectedItem()));
                }
                preparedStatement.setBoolean(4, administratorCheckBox.isSelected());
                Password saltAndHashPassword = new
                        Password(String.valueOf(passwordPasswordField.getPassword()));
                preparedStatement.setString(5,saltAndHashPassword.createSalt());
                preparedStatement.setString(6, saltAndHashPassword.createHash());

                preparedStatement.execute();
                connection.close( preparedStatement);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } catch (SQLException e) {
                e.printStackTrace();
            }




        }
        connection.close();
        userNameIncorrectLabel.setText("Username Exist!");
        userNameIncorrectLabel.setVisible(true);

    }
}
