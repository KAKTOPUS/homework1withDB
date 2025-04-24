package tables;

import animals.Animal;
import data.AnimalTypeData;

public class AnimalFromTable extends Animal {

    AnimalFromTable(int id, String name, int age, int weight, String color, AnimalTypeData data) {
        super(id, name, age, weight, color, data);
    }
    public void say() {
        System.out.println("");
    }

}
