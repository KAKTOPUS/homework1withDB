package tables;


import animals.Animal;
import data.AnimalTypeData;
import data.GetterList;
import db.IDataBase;
import db.MySqlConnectorDb;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.AcceptableName;
import tools.AcceptableNumber;

public class AnimalTable extends AbsTable {
    private MySqlConnectorDb mySqlConnectorDb = new MySqlConnectorDb();
    private AcceptableNumber aNum = new AcceptableNumber();
    private AcceptableName aName = new AcceptableName();
    private static Scanner scanner = new Scanner(System.in);


    private IDataBase iDataBase = null;

    public AnimalTable() {

        super("animal");
        iDataBase = new MySqlConnectorDb();
    }

    private boolean isTableExist() throws SQLException, IOException {
        String sqlReq = "SHOW TABLES;";
        ResultSet tables = iDataBase.resultExecuteWithReturn(sqlReq);

        while (tables.next()) {
            String tableName = tables.getString(1);
            if (tableName.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void createTable() throws SQLException, IOException {
        if (!isTableExist()) {
            String sqlReq = String.format("CREATE TABLE IF NOT EXISTS %s (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), age INT, weight INT, color VARCHAR(20), type VARCHAR(20));", name);
            iDataBase.requestExecute(sqlReq);
        }
    }

    public void addAnimalToTable(String aName, int age, int weight, String color, AnimalTypeData type) throws SQLException, IOException {
        String sqlRequest = String.format("INSERT INTO %s (name, age, weight, color, type) VALUES ('%s', %d, %d, '%s', '%s');",
                name, aName, age, weight, color, type);
        iDataBase.executeUpdate(sqlRequest);
    }

    public List<Animal> getAllAnimalsFromTable() throws SQLException, IOException {
        String sqlRequest = String.format("SELECT * FROM %s", name);
        ResultSet resultSet = iDataBase.resultExecuteWithReturn(sqlRequest);
        return listValuesFromTable(resultSet);
    }

    public List<Animal> getAnimalByType(AnimalTypeData data) throws SQLException, IOException {
        String sqlRequest = String.format("SELECT * FROM %s WHERE type = '%s'", name, data);
        ResultSet resultSet = iDataBase.resultExecuteWithReturn(sqlRequest);
        return listValuesFromTable(resultSet);
    }

    private List<Animal> getAnimalById(int id) throws SQLException, IOException {
        String sqlRequest = String.format("SELECT * FROM %s WHERE id = %d", name, id);
        ResultSet resultSet = iDataBase.resultExecuteWithReturn(sqlRequest);
        return listValuesFromTable(resultSet);
    }

    public void update() throws SQLException, IOException {
        System.out.println("Введите id животного, которое хотите изменить: ");
    }
}

