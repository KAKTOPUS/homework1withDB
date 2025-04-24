package tools;
import animals.Animal;
import data.AnimalTypeData;
import data.GetterListData;
import tables.AnimalTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListAnimalFromTable {
    private static AnimalTable animalTable = new AnimalTable();
    private static Scanner scanner = new Scanner(System.in);

    public void list() throws SQLException, IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        for (GetterListData getterListData : GetterListData.values()) {
            arrayList.add(getterListData.name());
        }

        while (true) {
            System.out.println(String.format("Введите комманду для вывода животных из таблицы: %s" + " или введите 'BACK' для выхода в предыдущее меню", String.join("/", arrayList)));

            String userCommand = scanner.next().trim();
            String userCommandUpperCase = userCommand.toUpperCase();

            boolean isCommandExist = false;

            for (GetterListData getterListData : GetterListData.values()) {
                if (userCommandUpperCase.equals(getterListData.name())) {
                    isCommandExist = true;
                    break;
                }
            }

            if (!isCommandExist) {
                System.out.println(String.format("Команда %s не поддерживается!", userCommand));
                continue;
            }

            switch (GetterListData.valueOf(userCommandUpperCase)) {
                case ALL: {
                    List<Animal> animals = animalTable.getAllAnimalsFromTable();
                    if(animals.isEmpty()) {
                        System.out.println("Таблица пуста");
                    }
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                    break;
                }
                case TYPE: {
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

                    List<Animal> animals = animalTable.getAnimalByType(animalTypeData);
                    if(animals.isEmpty()) {
                        System.out.println("Таблица пуста");
                    }
                    for(Animal animal : animals) {
                        System.out.println(animal);
                    }
                }
                case BACK: {
                    return;
                }
            }
        }
    }
}
