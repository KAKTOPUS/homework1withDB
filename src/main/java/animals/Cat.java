package animals;

import data.AnimalTypeData;

public class Cat extends Animal {

    public Cat(String name, int age, int weight, String color, AnimalTypeData data) {
        super(name, age, weight, color, data);
    }
    public void say() {
        System.out.println("'Мяу'");
    }
}
