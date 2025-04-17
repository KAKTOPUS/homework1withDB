package tools;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcceptableNumber {

    private boolean isAcceptableNumbers(int i) {
        String numberStr = Integer.toString(i);
        Pattern p = Pattern.compile("^\\d{1,2}$");;
        Matcher m = p.matcher(numberStr);
        return m.find();
    }

    public String checkNum(String inputStr, Scanner scanner) {
        inputStr = scanner.next().trim();
        while (!inputStr.matches("\\d{1,2}$")) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", inputStr, inputStr));
            inputStr = scanner.next().trim();
        }
        int intStr = Integer.parseInt(inputStr);
        while (!isAcceptableNumbers(intStr)) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", intStr, intStr));
            inputStr = scanner.next().trim();
            intStr = Integer.parseInt(inputStr);
        }
        while (intStr==0||intStr>=100) {
            System.out.println(String.format("%s недопустимое число, либо же %s не является числом, попробуйте снова", intStr, intStr));
            inputStr = scanner.next().trim();
            intStr = Integer.parseInt(inputStr);
        }
        return inputStr;
    }
}