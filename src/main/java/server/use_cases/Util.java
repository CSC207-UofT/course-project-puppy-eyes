package server.use_cases;

import java.util.regex.Pattern;

/**
 * A util class containing static methods for common operations.
 */
public final class Util {

    private Util() {
    }

    private final static Pattern intPattern = Pattern.compile("-?[0-9]+");

    public static boolean isInteger(String string) {
        return intPattern.matcher(string).matches();
    }

}