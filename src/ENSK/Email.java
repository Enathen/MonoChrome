package ENSK;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Email {
    String email;
    ConnectionClass connection = new ConnectionClass();
    public Email(String email) throws SQLException, ClassNotFoundException {

        this.email = email.toLowerCase();
        checkIfEmail();
        checkIfEmailExists();
    }



    public boolean checkIfEmail() {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound) {

            return true;

        }
        return false;
    }
    public boolean checkIfEmailExists() throws SQLException, ClassNotFoundException {
        if(connection == null){
            connection.getConnection();
        }

        String sql = "select email from Account where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            return true;
        }
        return false;
    }

}
