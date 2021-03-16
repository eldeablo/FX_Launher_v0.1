package FX_Launcher.Utils;

import java.nio.file.Path;

public class StringUtils {

    /**
     * Get name is File
     */
    public static String getNameIsFile(Path path) {
        return path.getFileName().toString();
    }

    /**
     * Get name is path root
     */
    public static String getPathNameFile(Path path) {
        return path.subpath(1, 2).toString();
    }

    /**
     * Get json is UrlDecoder Text
     */
    public static String getJsonText(String urlDecoder) {
        String[] _getFirsJson = urlDecoder.split("player_response=");
        String[] _jsonText = _getFirsJson[1].split("}&[a-zA-Z]");
        System.out.println("[" + _jsonText[0] + "}]");
        return "[" + _jsonText[0] + "}]";
    }

    /**
     * Get time is Duration of second
     */
    public static String getTimeIsSecond(double totalSeconds) {
        int _total = (int) totalSeconds / 1000;
        int min = _total / 60;
        int sec = _total % 60;

        return String.format("%02d:%02d", min, sec);
    }
}