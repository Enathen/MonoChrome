package ENSK.Windows.Connection;

import javax.naming.NamingException;
import javax.swing.*;
import java.sql.*;

/**
 * Created by Enathen on 2017-06-16.
 */
abstract public class ConnectionClass {
    private java.sql.Connection connection;
    private boolean connectionHasData = false;
    public ConnectionClass(JLabel error){
        if(connection == null){
            getConnection(error);
        }

    }
    private void getConnection(JLabel error) {
        error.setVisible(false);
        if(connection == null) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection
                        ("jdbc:mysql://78.72.148.142:3306/ensk?characterEncoding=UTF-8&useSSL=false", "root", "password");
            } catch (SQLException e) {
                error.setText("No Connection");
                error.setVisible(true);
                e.printStackTrace();
            }
            System.out.println("DATABASE CONNECTION ESTABLISHED");
        }
    }
    abstract void initialise();
    protected Connection returnConnection(){
        return connection;
    }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);

    }
    public void close(ResultSet resultSet, PreparedStatement statement)  {
        try { if (resultSet != null) resultSet.close(); } catch (Exception e) {}
        try { if (statement != null) statement.close(); } catch (Exception e) {}
    }
    public void close(PreparedStatement statement){
        try { if (statement != null) statement.close(); } catch (Exception e) {}
    }
    public void close() {
        try { if (connection != null) connection.close(); } catch (Exception e) {}
    }
}
