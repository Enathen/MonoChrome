package ENSK;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;

/**
 * Created by Enathen on 2017-05-31.
 */
public class ConnectionClass {
    /**
     * gets connection to database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static java.sql.Connection connection;
    private boolean connectionHasData = false;
    public ConnectionClass() throws SQLException, ClassNotFoundException, NamingException {
        if(connection == null){
            getConnection();
        }

    }
    public void getConnection() throws ClassNotFoundException, SQLException, NamingException {

        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/ensk?characterEncoding=UTF-8&useSSL=false", "root", "Hccolh12Mhjitp12");
            System.out.println("DATABASE CONNECTION ESTABLISHED");
            initialise();
        }
    }

    /**
     * Start up the database.
     * @throws SQLException
     */
    private void initialise() throws SQLException {

        if(!connectionHasData){
            connectionHasData = true;
            Statement state = connection.createStatement();
            ResultSet resultSet = connection.getMetaData().getCatalogs();


            if(!resultSet.next()){
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE Account(id int, userName varchar(255)," +
                        "email VARCHAR(255),workstation VARCHAR(255),admin BOOLEAN,saltedHash VARCHAR(255), hash VARCHAR(255),  primary key(id));");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?);");
                preparedStatement.setString(2,"Enathen");
                preparedStatement.setString(3,"j@gmail.com");
                preparedStatement.setString(4,"Coop Ersboda");
                preparedStatement.setString(5,"1");

                SaltAndHashPassword salt = new SaltAndHashPassword("olikujmyhn");
                preparedStatement.setString(6, salt.createSalt());
                preparedStatement.setString(7, salt.createHash());
            }
            resultSet.close();
        }
    }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    public void close(ResultSet resultSet, PreparedStatement statement) throws SQLException {
        try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
        try { if (statement != null) statement.close(); } catch (Exception e) {}
        try { if (connection != null) connection.close(); } catch (Exception e) {}
    }
    public void close(PreparedStatement statement){
        try { if (statement != null) statement.close(); } catch (Exception e) {}
        try { if (connection != null) connection.close(); } catch (Exception e) {}
    }

}
