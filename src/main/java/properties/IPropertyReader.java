package properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface IPropertyReader {
    Map<String, String> getSettings() throws IOException;
}
