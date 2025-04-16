package tables;

import animals.Animal;
import data.AnimalTypeData;

public class AnimalFromTable extends Animal {
    //private int id = 0;

    AnimalFromTable(int id, String name, int age, int weight, String color, AnimalTypeData data) {
        super(id, name, age, weight, color, data);
      //  this.id = id;
    }
    public void say() {
        System.out.println("");
    }

}
