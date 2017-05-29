package ENSK.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;
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
    private JLabel userNameIncorrectLabel;
    private static Connection connection;
    private boolean connectionHasData = false;

    /**
     * creates a new account
     */
    public CreateNewAccount() {
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
                for(int i = 0; i< usernameTextField.getText().length(); i++){
                    if(!(usernameTextField.getText().charAt(i) >= 'A' &&
                            usernameTextField.getText().charAt(i) <= 'z')){
                        userNameIncorrectLabel.setText("<html>Username incorrect!<br>cant allow character</html>");
                        userNameIncorrectLabel.setVisible(true);
                        working = false;

                    }
                }
                if(!(usernameTextField.getText().length() > 3)){
                    userNameIncorrectLabel.setText("<html>Username incorrect!<br>Atleast 3 char!</html>");
                    working = false;

                }
                if(!checkIfEmail()){
                    emailIncorrectLabel.setVisible(true);
                    working = false;
                }else{
                    emailIncorrectLabel.setVisible(false);
                }

                if(working){
                    try {
                        addUser();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }

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
     * check if email are a correct format
     * @return
     */
    private boolean checkIfEmail() {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(emailTextField.getText());
        boolean matchFound = m.matches();
        if (matchFound) {

            return true;

        }
        return false;
    }

    /**
     * add a user.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void addUser() throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }

        if(!checkIfEqualUsername()){
            String sql = "INSERT INTO Account (id ,userName, email,workstation,admin, saltedHash, hash) VALUES (?,?,?,?,?,?,?)";
            String userNameCorrect = userNameCorrect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, userNameCorrect);
            preparedStatement.setString(3, emailTextField.getText());

            if(String.valueOf(comboBox1.getSelectedItem()).endsWith("you work?")){
                preparedStatement.setString(4,null);
            }
            else {
                preparedStatement.setString(4, String.valueOf(comboBox1.getSelectedItem()));
            }


            preparedStatement.setBoolean(5, administratorCheckBox.isSelected());
            String salt = createSalt();
            preparedStatement.setString(6,salt);
            preparedStatement.setString(7, String.valueOf(createHash(salt)));

            preparedStatement.execute();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
        userNameIncorrectLabel.setText("Username Exist!");
        userNameIncorrectLabel.setVisible(true);

    }

    /**
     * Convert the username to correct format capital letter front rest not capital
     * @return
     */
    private String userNameCorrect() {
        String string = usernameTextField.getText();
        String backupString;
        backupString = string.substring(0,1).toUpperCase();
        string =  backupString+string.substring(1).toLowerCase();
        return string;

    }

    /**
     * gets connection to database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:ENSK.sqlite");
        initialise();
    }

    /**
     * Start up the database.
     * @throws SQLException
     */
    private void initialise() throws SQLException {
        if(!connectionHasData){
            connectionHasData = true;
            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT name FROM sqlite_master Where type='table' AND name='Account'");
            if(!resultSet.next()){
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE Account(id integer, userName varchar(80)," +
                        "email VARCHAR(60),workstation VARCHAR(60),admin BOOLEAN,saltedHash VARCHAR(60), hash VARCHAR(60),  primary key(id));");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?);");
                preparedStatement.setString(2,"Enathen");
                preparedStatement.setString(3,"j@gmail.com");
                preparedStatement.setString(4,"Coop Ersboda");
                preparedStatement.setString(5,"1");
                String salt = createSalt();
                preparedStatement.setString(6,salt);
                preparedStatement.setString(7, String.valueOf(createHash(salt)));
            }
        }
    }

    /**
     * Check if username already exist in database
     * @return true if there already exist a user false if no user exist.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private boolean checkIfEqualUsername() throws SQLException, ClassNotFoundException {
        String userNameCorrect = userNameCorrect();

        String sql = "select * from Account where userName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userNameCorrect);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            return true;
        }
        return false;
    }
    private String createSalt() {
        SecureRandom random = new SecureRandom();
        String string = "";
        for (int i = 0; i < 50; i++) {
            System.out.println(random.nextInt(58));
            string += (char)(random.nextInt(58) + 65);
        }
        System.out.println(string);
        return string;
    }
    private long createHash(String salt){
        String hash = salt + Arrays.toString(passwordPasswordField.getPassword());
        return hash(hash);

    }
    public static long hash(String string) {
        long h = 1125899906842597L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 31*h + string.charAt(i);
        }
        return h;
    }

}
