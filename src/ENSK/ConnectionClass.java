package ENSK;

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
    public ConnectionClass() throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }

    }
    public void getConnection() throws ClassNotFoundException, SQLException {

        if(connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ENSK.sqlite");
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
            ResultSet resultSet = state.executeQuery("SELECT name FROM sqlite_master Where type='table' AND name='Account'");
            if(!resultSet.next()){
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE Account(id integer, userName varchar(80)," +
                        "email VARCHAR(60),workstation VARCHAR(60),admin BOOLEAN,saltedHash VARCHAR(60), hash VARCHAR(60),  primary key(id));");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?);");
                SaltAndHashPassword saltAndHashPassword = new SaltAndHashPassword("");
                preparedStatement.setString(2,"Enathen");
                preparedStatement.setString(3,"j@gmail.com");
                preparedStatement.setString(4,"Coop Ersboda");
                preparedStatement.setString(5,"1");

                SaltAndHashPassword salt = new SaltAndHashPassword("olikujmyhn");
                preparedStatement.setString(6, salt.createSalt());
                preparedStatement.setString(7, salt.createHash());
            }
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
