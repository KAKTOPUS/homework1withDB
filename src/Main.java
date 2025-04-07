import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import data.*;
import tools.*;

public class Main { ;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AddAnimal addA = new AddAnimal();
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

            switch (CommandsData.valueOf(userCommandUpperCase)) {
                case ADD: {
                    addA.addAnimal();
                    break;
                }
                case LIST: {
                    addA.listAnimal();
                    break;
                }
                case EXIT: {
                    System.exit(0);
                }
            }
        }
    }
}