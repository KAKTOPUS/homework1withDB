import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import data.*;
import tools.*;
import tables.AnimalTable;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AnimalTable animalTable = new AnimalTable();
    private static AddAnimal addA = new AddAnimal();
    private static ListAnimalFromTable listAnimalFromTable = new ListAnimalFromTable();

    public static void main(String[] args) {
        while (true) {
            List<String> nameStr = new ArrayList<>();
            for (CommandsData commandsData : CommandsData.values()) {
                nameStr.add(commandsData.name());
            }

            System.out.println(String.format("Введите команду: %s", String.join("/", nameStr)));

            String userCommand = scanner.next().trim();
            String userCommandUpperCase = userCommand.toUpperCase();

            boolean isCommandExist = false;
            for (CommandsData commandsData : CommandsData.values()) {
                if (userCommandUpperCase.equals(commandsData.name())) {
                    isCommandExist = true;
                    break;
                }
            }

            if (!isCommandExist) {
                System.out.println(String.format("Комманда %s не поддерживается", userCommand));
                continue;
            }
            try {
                animalTable.createTable();
                switch (CommandsData.valueOf(userCommandUpperCase)) {
                    case ADD: {
                        addA.addAnimal();
                        break;
                    }
                    case LIST: {
                        listAnimalFromTable.list();
                        break;
                    }
                    case UPDATE: {
                        animalTable.update();
                        break;
                    }
                    case EXIT: {
                        System.exit(0);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}