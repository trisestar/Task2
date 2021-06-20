package validation;

import java.io.File;

public class CustomFileValidator {
    public static boolean isFileValid(String pathFile) {
        if (pathFile == null) {
            return false;
        }
        File file = new File(pathFile);
        if (file.exists() && file.isFile() && file.length() > 0 && file.canRead()) {
            return true;
        }
        return false;
    }
}
