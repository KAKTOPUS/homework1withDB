package tables;

import animals.Animal;
import data.AnimalTypeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class AbsTable {
    protected String name;

    public AbsTable(String name) {
        this.name = name;
    }

    public List<Animal> listValuesFromTable(ResultSet resultSet) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {
            animals.add(new AnimalFromTable(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getInt("weight"),
                    resultSet.getString("color"),
                    AnimalTypeData.valueOf(resultSet.getString("type"))
            ));
        }
        return animals;
    }

}
