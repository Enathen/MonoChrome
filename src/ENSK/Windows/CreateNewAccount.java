package ENSK.Windows;

import ENSK.ConnectionClass;
import ENSK.Email;
import ENSK.SaltAndHashPassword;
import ENSK.Username;

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
    private ConnectionClass connection = new ConnectionClass();
    /**
     * creates a new account
     */
    public CreateNewAccount() throws SQLException, ClassNotFoundException, NamingException {
        initialize();
        comboBox1.addItem("Coop Forum Ersboda");

        /**
         * when create account button listener try to create a new account.
         */
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean working = true;
                String password = String.valueOf(passwordPasswordField.getPassword());
                String password2 = String.valueOf(passwordPasswordField1.getPassword());
                if(!password.equals(password2)){

                    passwordIncorrectLabel.setText("<html>Password incorrect!<br>not equals!</html>");
                    createAccountIncorrect.setVisible(true);
                    working = false;
                }else{
                    passwordIncorrectLabel.setText("");
                }
                if(!(passwordPasswordField.getPassword().length > 7)){
                    passwordIncorrectLabel.setText("<html>Password incorrect!<br>to short!</html>");
                    createAccountIncorrect.setVisible(true);
                    working = false;

                }
                for(char character : passwordPasswordField.getPassword()){

                    if(!((character >= 'A' && character <= 'z') || (character >= '1' && character <= '9'))){
                        passwordIncorrectLabel.setText("<html>Password incorrect!<br>cant allow character</html>");
                        working = false;

                    }
                }
                userNameIncorrectLabel.setVisible(false);
                try {
                    Username username = new Username(usernameTextField.getText());
                    if(!username.userNameCorrectFormat()){
                        userNameIncorrectLabel.setText("<html>Username incorrect!<br>min 4 max 59<br>" +
                                "A-z allowed letters</html>");
                        userNameIncorrectLabel.setVisible(true);
                        working = false;
                    }
                } catch (SQLException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (NamingException e1) {
                    e1.printStackTrace();
                }
                Email email= null;
                try {
                    email = new Email(emailTextField.getText());
                } catch (SQLException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (NamingException e1) {
                    e1.printStackTrace();
                }
                assert email != null;
                if(!email.checkIfEmail()){
                    emailIncorrectLabel.setText("<html>Email incorrect!</html>");
                    emailIncorrectLabel.setVisible(true);
                    working = false;
                }else{
                    emailIncorrectLabel.setVisible(false);
                }
                try {
                    if(email.checkIfEmailExists()){
                        emailIncorrectLabel.setText("<html>Email already<br> Exists!</html>");
                        emailIncorrectLabel.setVisible(true);
                        working = false;
                    }
                } catch (SQLException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                if(working){
                    try {
                        addUser();
                    } catch (SQLException | ClassNotFoundException | NamingException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
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
    }

    /**
     * starts the form.
     */
    private void initialize(){
        JPanelCreateNewAccount.setMinimumSize(new Dimension(540,280));

        setContentPane(JPanelCreateNewAccount);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    /**
     * add a user.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addUser() throws SQLException, ClassNotFoundException, NamingException {
        Username username = new Username(usernameTextField.getText());

        if(!username.checkIfEqualUsername()){
            String sql = "INSERT INTO Account (userName, email,workstation,admin, saltedHash, hash) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username.userNameCorrect());
            preparedStatement.setString(2, emailTextField.getText().toLowerCase());

            if(String.valueOf(comboBox1.getSelectedItem()).endsWith("you work?")){
                preparedStatement.setString(3,null);
            }
            else {
                preparedStatement.setString(3, String.valueOf(comboBox1.getSelectedItem()));
            }
            preparedStatement.setBoolean(4, administratorCheckBox.isSelected());
            SaltAndHashPassword saltAndHashPassword = new
                    SaltAndHashPassword(String.valueOf(passwordPasswordField.getPassword()));
            preparedStatement.setString(5,saltAndHashPassword.createSalt());
            preparedStatement.setString(6, saltAndHashPassword.createHash());

            preparedStatement.execute();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));


        }
        userNameIncorrectLabel.setText("Username Exist!");
        userNameIncorrectLabel.setVisible(true);

    }
}
