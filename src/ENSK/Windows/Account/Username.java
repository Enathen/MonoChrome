package ENSK.Windows.Account;

import ENSK.Windows.Connection.ConnectionAccount;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Username {

    private String username;
    public Username(String username) {
        this.username = username;
    }

    /**
     * Convert the username to correct format capital letter front rest not capital
     * @return
     */
    public String userNameCorrect() {
        String string = username;
        String backupString;
        backupString = string.substring(0,1).toUpperCase();
        string =  backupString+string.substring(1).toLowerCase();
        return string;

    }


    /**
     * Check if username already exist in database
     * @return true if there already exist a user false if no user exist.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean checkIfEqualUsername(ConnectionAccount connection) {
        String userNameCorrect = userNameCorrect();

        String sql = "select * from Account where userName = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userNameCorrect);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean userNameCorrectFormat( JLabel userNameIncorrectLabel) {
        userNameIncorrectLabel.setVisible(false);
        for(int i = 0; i< username.length(); i++){
            if(!(username.charAt(i) >= 'A' &&
                    username.charAt(i) <= 'z')){
                userNameIncorrectLabel.setText("<html>Username incorrect!<br>" +
                        "A-z allowed letters</html>");
                userNameIncorrectLabel.setVisible(true);
                return false;

            }
        }
        if(!(username.length() > 3)){
            userNameIncorrectLabel.setText("<html>Username incorrect!<br>" +
                    "min 4 letters</html>");
            userNameIncorrectLabel.setVisible(true);
            return false;

        }
        if(!(username.length() < 64)){
            userNameIncorrectLabel.setText("<html>Username incorrect!<br>" +
                    "max 64 letters</html>");
            userNameIncorrectLabel.setVisible(true);
            return false;
        }
        return true;
    }
}
