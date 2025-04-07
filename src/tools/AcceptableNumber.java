package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcceptableNumber {

    public boolean isAcceptableNumbers(int i) {
        String numberStr = Integer.toString(i);
        Pattern p = Pattern.compile("^\\d{1,2}$");;
        Matcher m = p.matcher(numberStr);
        return m.find();
    }
}