package group5.eeet2580_project.common;

public class Utils {
    public static String userKey(String username, String token) {
        return username + ":" + token;
    }
}
