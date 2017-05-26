package ENSK.Windows;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static Connection connection;
    private boolean connectionHasData = false;
    public CreateNewAccount(){
        initialize();
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(passwordPasswordField.getPassword());
                String password2 = String.valueOf(passwordPasswordField1.getPassword());
                if(!password.equals(password2)){

                    passwordIncorrectLabel.setText("<html>Password incorrect!<br>not equals!</html>");
                    createAccountIncorrect.setVisible(true);
                }
                if(!(passwordPasswordField.getPassword().length > 7)){
                    passwordIncorrectLabel.setText("<html>Password incorrect!<br>to short!</html>");
                    createAccountIncorrect.setVisible(true);

                }
                for(char character : passwordPasswordField.getPassword()){
                    System.out.println((int)character);
                    if(!((character >= 'A' && character <= 'z') || (character >= '1' && character <= '9'))){
                        System.out.println(character);
                        passwordIncorrectLabel.setText("<html>Password incorrect!<br>cant allow</html>" + character);

                    }
                }
                if(!checkIfEmail()){
                    emailIncorrectLabel.setVisible(true);
                }
                try {
                    ResultSet resultSet = displayUsers();
                    while (resultSet.next()){
                        System.out.println(resultSet.getString("userName") + " " + resultSet.getString("password")
                                + resultSet.getString("email") + " "
                                + resultSet.getString("workstation")+ " " + resultSet.getString("admin"));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    addUser();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    private void initialize(){
        JPanelCreateNewAccount.setMinimumSize(new Dimension(540,280));

        setContentPane(JPanelCreateNewAccount);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    private boolean checkIfEmail() {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(emailTextField.getText());
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        }
        return false;
    }
    public ResultSet displayUsers() throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT userName, password, email,workstation,admin FROM user");
    }
    private void addUser() throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }
        if(!checkIfEqualUsername()){
            String sql = "INSERT INTO user (id ,userName, password, email,workstation,admin) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, usernameTextField.getText());
            preparedStatement.setString(3, String.valueOf(passwordPasswordField.getPassword()));
            preparedStatement.setString(4, emailTextField.getText());
            preparedStatement.setString(5, "COOP");
            preparedStatement.setString(6, administratorCheckBox.getText());
            preparedStatement.execute();
        }

    }

    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:ENSK.sqlite");
        initialise();
    }

    private void initialise() throws SQLException {
        if(!connectionHasData){
            connectionHasData = true;
            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT name FROM sqlite_master Where type='table' AND name='user'");
            if(!resultSet.next()){
                System.out.println("POOP");
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE user(id integer, userName varchar(80),password VARCHAR(80),email VARCHAR(60),workstation VARCHAR(60),admin BOOLEAN,  primary key(id));");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user values(?,?,?,?,?,?);");
                preparedStatement.setString(2,"Enathen");
                preparedStatement.setString(3,"xyz");
                preparedStatement.setString(4,"Jontekaminen@hotmail.com");

                preparedStatement.setString(5,"COOP ERSBODA");
                preparedStatement.setString(6,"1");
                preparedStatement.execute();
            }
        }
    }
    public  boolean checkIfEqualUsername() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM user WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1001);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println();
            if(resultSet.getString("userName").equals(usernameTextField)){
                System.out.println("HEEEJ");
                return true;
            }
        }
        return false;
    }
}
