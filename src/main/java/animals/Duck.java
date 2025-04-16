package animals;
import data.AnimalTypeData;
import featuresBirds.IFlying;

public class Duck extends Animal implements IFlying {

    public Duck(String name, int age, int weight, String color, AnimalTypeData animalTypeData) {
        super(name, age, weight, color, animalTypeData);
    }

    public void say() {
        System.out.println("'Кря'");
    }

    public void fly() {
        System.out.println("Я лечу");
    }
}
