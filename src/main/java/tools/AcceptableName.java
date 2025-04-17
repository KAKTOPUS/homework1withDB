package tools;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AcceptableName {

    private boolean isAcceptableName(String strName) {
        Pattern p = Pattern.compile("^[a-z]+$");
        Matcher m = p.matcher(strName);
        return m.find();
    }

    public String checkName(String inputStr, Scanner scanner) {
        inputStr = scanner.next().trim();
        while (!isAcceptableName(inputStr)) {
            System.out.println(String.format("%s - недопустимая строка для ввода, попробуйте снова", inputStr));
            inputStr = scanner.next().trim();
        }
        while (inputStr.length()>25) {
            System.out.println(String.format("%s - недопустимая строка для ввода, попробуйте снова", inputStr));
            inputStr = scanner.next().trim();
        }

        return inputStr;
    }
}