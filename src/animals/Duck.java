package animals;
import featuresBirds.IFlying;

public class Duck extends Animal implements IFlying {

    public Duck(String name, int age, int weight, String color) {
        super(name, age, weight, color);
    }

    public void say() {
        System.out.println("'Кря'");
    }

    public void fly() {
        System.out.println("Я лечу");
    }
}
