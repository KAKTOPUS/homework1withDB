package factory;
import animals.Animal;
import animals.Cat;
import animals.Dog;
import animals.Duck;

public class AnimalFactory {
    public static Animal create(String name, int age, int weight, String color, String str) {
        switch (str) {
            case "cat": {
                return  new Cat(name, age, weight, color);
            }
            case "dog" : {
                return new Dog(name, age, weight, color);
            }
            case "duck": {
                return new Duck(name, age, weight, color);
            }
        }

        return null;
    }
}
