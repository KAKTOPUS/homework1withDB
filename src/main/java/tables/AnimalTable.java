package tables;


import animals.Animal;
import data.AnimalTypeData;
import data.GetterList;
import data.ListOfChange;
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

    public boolean updateAge(int id, int newAge) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET age = %d WHERE id = %d", name, newAge, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        if(rows<=0) {
            return false;
        }
        return true;
    }

    public boolean updateWeight(int id, int newWeight) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET weight = %d WHERE id = %d", name, newWeight, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        return rows > 0;
    }

    public boolean updateColor(int id, String newColor) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET color = '%s' WHERE id = %d", name, newColor, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        return rows > 0;
    }

    public boolean updateType(int id, AnimalTypeData data) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET type = '%s' WHERE id = %d", name, data, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        return rows > 0;
    }

    public boolean updateName(int id, String newName) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET name = '%s' WHERE id = %d", name, newName, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        return rows > 0;
    }

    public boolean updateAll(int id, String newName, int newAge, int newWeight, String newColor, AnimalTypeData data) throws SQLException, IOException {
        String sqlRequest = String.format("UPDATE %s SET (" +
                "name = '%s'," +
                " age = %d, " +
                " weight = %d," +
                " color = '%s', " +
                " type = '%s') WHERE id = %d", name, newName, newAge, newWeight, newColor, data, id);
        int rows = iDataBase.executeUpdate(sqlRequest);
        return rows > 0;
    }



    public List<Animal> getAnimalByType(AnimalTypeData data) throws SQLException, IOException {
        String sqlRequest = String.format("SELECT * FROM %s WHERE type = '%s'", name, data);
        ResultSet resultSet = iDataBase.resultExecuteWithReturn(sqlRequest);
        return listValuesFromTable(resultSet);
    }

    private boolean findAnimalById(int id) throws SQLException, IOException {
        String sqlRequest = String.format("SELECT * FROM %s WHERE id = %d", name, id);
        ResultSet resultSet = iDataBase.resultExecuteWithReturn(sqlRequest);
        if(listValuesFromTable(resultSet)!=null) {
            return true;
        }
        return false;
    }

    public void update() throws SQLException, IOException {
        System.out.println("Введите id животного, которое хотите изменить: ");

        List<Animal> animals;
        animals = getAllAnimalsFromTable();
        if(animals.isEmpty()) {
            System.out.println("Таблица пуста");
        }
        for (Animal animal : animals) {
            System.out.println(animal.returnId());
            System.out.println(animal);
        }


        String inputId = scanner.next().trim();
        while (!inputId.matches("\\d{1,2}$")) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", inputId, inputId));
            inputId = scanner.next().trim();
        }
        int strId = Integer.parseInt(inputId);
        while (!aNum.isAcceptableNumbers(strId)) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", inputId, inputId));
            inputId = scanner.next().trim();
            strId = Integer.parseInt(inputId);
        }
        while (strId==0||strId>=100) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", inputId, inputId));
            inputId = scanner.next().trim();
            strId = Integer.parseInt(inputId);
        }
        while (!findAnimalById(strId)) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", inputId, inputId));
            inputId = scanner.next().trim();
            strId = Integer.parseInt(inputId);
        }

        while (true) {
            ArrayList<String> listOfChange = new ArrayList<>();
            for (ListOfChange list : ListOfChange.values()) {
                listOfChange.add(list.name());
            }

            System.out.println(String.format("Введите то, что хотите поменять: %s", String.join("/", listOfChange)));

            String userCommand = scanner.next().trim();
            String userCommandUpperCase = userCommand.toUpperCase();

            boolean isCommandExist = false;

            for(ListOfChange list : ListOfChange.values()) {
                if(userCommandUpperCase.equals(list.name())) {
                    isCommandExist = true;
                    break;
                }
            }

            if(!isCommandExist) {
                System.out.println(String.format("Комманда %s не поддерживается", userCommand));
                continue;
            }

            switch (ListOfChange.valueOf(userCommandUpperCase)) {

                case AGE: {
                    System.out.println("Введите новый возраст: ");

                    String ageStr = scanner.next().trim();
                    while (!ageStr.matches("\\d{1,2}$")) {
                        System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", ageStr, ageStr));
                        ageStr = scanner.next().trim();
                    }
                    int age = Integer.parseInt(ageStr);
                    while (!aNum.isAcceptableNumbers(age)) {
                        System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", age, age));
                        ageStr = scanner.next().trim();
                        age = Integer.parseInt(ageStr);
                    }
                    while (age==0||age>=100) {
                        System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", age, age));
                        ageStr = scanner.next().trim();
                        age = Integer.parseInt(ageStr);
                    }

                    if (!updateAge(strId, age)) {
                        System.out.println(String.format("Животное с id '%d' не удалось обновить!", strId));
                    }

                    updateAge(strId, age);
                }
            }
        }
    }
}

