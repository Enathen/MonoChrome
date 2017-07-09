package ENSK.Windows.Connection;

import ENSK.Windows.Account.Password;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Enathen on 2017-05-31.
 */
public class ConnectionAccount extends  ConnectionClass{
    /**
     * gets connection to database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private java.sql.Connection connection;
    private boolean connectionHasData = false;
    public ConnectionAccount(JLabel error){
        super(error);
        connection = returnConnection();
        initialise();

    }

    /**
     * Start up the database.
     * @throws SQLException
     */
    @Override
    protected void initialise() {
        if(!connectionHasData){
            connectionHasData = true;

            ResultSet resultSet = null;
            try {
                resultSet = connection.getMetaData().getCatalogs();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                if(!resultSet.next()){
                    Statement state2 = null;
                    try {
                        state2 = connection.createStatement();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    state2.execute("CREATE TABLE Account(id int, userName varchar(63)," +
                            "email VARCHAR(255),workstation VARCHAR(255),admin BOOLEAN,saltedHash VARCHAR(255), hash VARCHAR(255),  primary key(id));");
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?);");
                    preparedStatement.setString(2,"Enathen");
                    preparedStatement.setString(3,"j@gmail.com");
                    preparedStatement.setString(4,"Coop Ersboda");
                    preparedStatement.setString(5,"1");

                    Password salt = new Password("olikujmyhn");
                    preparedStatement.setString(6, salt.createSalt());
                    preparedStatement.setString(7, salt.createHash());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ResultSet getData(String username)  {
        String sql = "select * from Account where userName = ?";

        try {
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
