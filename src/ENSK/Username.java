package ENSK;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Username {
    private ConnectionClass connection = new ConnectionClass();
    private String username;
    public Username(String username) throws SQLException, ClassNotFoundException {
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
    public boolean checkIfEqualUsername() throws SQLException, ClassNotFoundException {
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
}
