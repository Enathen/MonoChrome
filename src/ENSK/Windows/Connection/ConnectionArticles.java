package ENSK.Windows.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Enathen on 2017-06-16.
 */
public class ConnectionArticles extends ConnectionClass{
    private java.sql.Connection connection;
    private boolean connectionHasData = false;
    public ConnectionArticles(JLabel error){
        super(error);
        connection = returnConnection();
        initialise();
    }

    void initialise() {

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
                    state2.execute("CREATE TABLE Articles(idArticle varchar(255),barcode varchar(255), " +
                            "expiryDate varchar(255)," + "weight float(10,3), stockAmount int(64),sellPrice float(10,2)," +
                            "inPrice float(10,2), onSalePrice float(10,2), primary key(idArticle));");
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

    public boolean barcodeExist(String barcodeTextField) {
        String sql = "select * from articles where barcode = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, barcodeTextField);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
