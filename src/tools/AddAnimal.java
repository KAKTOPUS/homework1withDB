package tools;
import animals.Animal;
import data.AnimalTypeData;
import factory.AnimalFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import animals.Duck;

public class AddAnimal {
    private static List<Animal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addAnimal() {
        AcceptableName aName = new AcceptableName();
        AcceptableNumber aNum = new AcceptableNumber();
        AcceptableType aType = new AcceptableType();
        List<String> listA = new ArrayList<>();
        for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
            listA.add(animalTypeData.name());
        }
        System.out.println(String.format("Введите животное из списка: %s", String.join("/", listA)));
        String animalType = scanner.next().trim().toUpperCase();

        while (!aType.acceptableType(animalType)) {
            System.out.println(String.format("%s нету в списке, попробуйте снова", animalType));
            animalType = scanner.next().trim().toUpperCase();
        }

        String animalTypeLowerCase = animalType.toLowerCase();


        System.out.println("Введите имя: ");
        String name = scanner.next().trim();
        while (!aName.isAcceptableName(name)) {
            System.out.println(String.format("%s - недопустимое имя, попробуйте снова", name));
            name = scanner.next().trim();
        }


        System.out.println("Введите возраст: ");
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


        System.out.println("Введите вес: ");
        String weightString = scanner.next().trim();
        while (!weightString.matches("\\d{1,2}$")) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", weightString, weightString));
            weightString = scanner.next().trim();
        }
        int weight = Integer.parseInt(weightString);
        while (!aNum.isAcceptableNumbers(weight)) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", weight, weight));
            weightString = scanner.next().trim();
            weight = Integer.parseInt(weightString);
        }
        while (weight==0||weight>=100) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", weight, weight));
            weightString = scanner.next().trim();
            weight = Integer.parseInt(weightString);
        }


        System.out.println("Введите цвет: ");
        String color = scanner.next().trim();
        while (!aName.isAcceptableName(color)) {
            System.out.println(String.format("%s недопустимый цвет, попробуйте снова", color));
            color = scanner.next().trim();
        }


        Animal animal = AnimalFactory.create(name, age, weight, color, animalTypeLowerCase);
        animals.add(animal);
        System.out.print("Животное добавлено и говорит: ");
        animal.say();
        animal.eat();
        animal.drink();
        animal.go();

        if (animalTypeLowerCase.equals("duck")) {
            Duck duck = new Duck(name, age, weight, color);
            duck.fly();
        }
        System.out.println();
    }

    public static void listAnimal() {
        if (animals.isEmpty()) {
            System.out.println("Вы ещё ничего не добавили, список пуст!");
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}