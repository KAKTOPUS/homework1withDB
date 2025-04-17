package tools;
import animals.Animal;
import data.AnimalTypeData;
import factory.AnimalFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import animals.Duck;
import tables.AnimalTable;

public class AddAnimal {
    private static AnimalTable animalTable = new AnimalTable();
    private static List<Animal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addAnimal() throws SQLException, IOException {
        AcceptableName aName = new AcceptableName();
        AcceptableNumber aNum = new AcceptableNumber();
        AcceptableType aType = new AcceptableType();
        List<String> listA = new ArrayList<>();
        for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
            listA.add(animalTypeData.name());
        }
        System.out.println(String.format("Введите животное из списка: %s", String.join("/", listA)));
        String animalType = scanner.next().trim().toUpperCase();

         while (!aType.isAcceptableType(animalType)) {
             System.out.println(String.format("%s нету в списке, попробуйте снова", animalType));
              animalType = scanner.next().trim().toUpperCase();
           }

        AnimalTypeData animalTypeData = aType.setType(animalType);


        System.out.println("Введите имя: ");
        String name = "";
        name = aName.checkName(name, scanner);



        System.out.println("Введите возраст: ");
        String ageStr = "";
        ageStr = aNum.checkNum(ageStr, scanner);
        int age = Integer.parseInt(ageStr);


        System.out.println("Введите вес: ");
        String weightString = "";
        weightString = aNum.checkNum(weightString, scanner);
        int weight = Integer.parseInt(weightString);

        System.out.println("Введите цвет: ");
        String color = "";
        color = aName.checkName(color, scanner);


        Animal animal = AnimalFactory.create(name, age, weight, color, animalTypeData);
        animals.add(animal);
        System.out.print("Животное добавлено и говорит: ");
        animal.say();
        animal.eat();
        animal.drink();
        animal.go();

        if (animalTypeData.equals(AnimalTypeData.DUCK)) {
            Duck duck = new Duck(name, age, weight, color, animalTypeData);
            duck.fly();
        }
        System.out.println();

        animalTable.addAnimalToTable(animal.getName(), animal.getAge(), animal.getWeight(), animal.getColor(), animal.getType());

        System.out.println("животное добавлено в таблицу");
    }

}