package tools;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AcceptableName {

    public boolean isAcceptableName(String strName) {
        Pattern p = Pattern.compile("^[a-z]+$");
        Matcher m = p.matcher(strName);
        return m.find();
    }
}