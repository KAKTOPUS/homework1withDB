package db;

import animals.Animal;
import data.AnimalTypeData;
import properties.FilePropertiesReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import properties.FilePropertiesReader;

public class MySqlConnectorDb implements IDataBase {
    private static Connection connection = null;
    private static Statement statement = null;

    private void openConnectToDb() throws SQLException, IOException {

        if (connection == null) {
         //   connection = DriverManager.getConnection(url, user, password);
            Map<String, String> settings = new FilePropertiesReader().getSettings();
            connection = DriverManager.getConnection(settings.get("url"), settings.get("login"), settings.get("password"));
        }

        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    public void requestExecute(String sqlRequest) throws SQLException, IOException {
        openConnectToDb();
        statement.execute(sqlRequest);
    }

    public ResultSet resultExecuteWithReturn(String sqlRequest) throws SQLException, IOException {
        openConnectToDb();
        return statement.executeQuery(sqlRequest);
    }


    public int executeUpdate(String sqlRequest) throws SQLException, IOException {
        openConnectToDb();
        return statement.executeUpdate(sqlRequest);
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }

        if(connection != null) {
            connection.close();
        }
    }
}
