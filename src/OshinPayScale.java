import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OshinPayScale {

    static HashSet<String> citySet = new HashSet<String>();
    static HashSet<String> stateSet = new HashSet<String>();
    static List<String> allNames = new ArrayList<String>();

    public static void main(String[] args) {
        citySet.add("Portland");
        citySet.add("San Francisco");
        citySet.add("New York");
        citySet.add("Minneapolis-Saint Paul");
        citySet.add("Winston-Salem");

        stateSet.add("Oregon");
        stateSet.add("California");
        stateSet.add("New York");
        stateSet.add("Minnesota");
        stateSet.add("North Carolina");

//         String t = ParseCityState("Portland-Oregon");
//         String t = ParseCityState("San-Francisco-California");
//         String t = ParseCityState("New-York-New-York");
//        String t = ParseCityState("Minneapolis-Saint-Paul-Minnesota");
        String t = ParseCityState("Winston-Salem-North-Carolina");
        System.out.println("Answer: " + t);
    }

    static String ParseCityState(String hyphenatedCityState) {
        if (hyphenatedCityState == null || hyphenatedCityState.trim().length() == 0)
            return "invalid input";
        
        String[] parts = hyphenatedCityState.split("-");
        if (parts.length < 2)
            return null;
        
        String finalCity = "";
        String finalState = "";
        int n = parts.length;
        
        List<String> tempPartsCity = new ArrayList<String>();
        List<String> tempPartsState = new ArrayList<String>();
        for (int i = 0; i < parts.length - 1; i++) {
            String[] strBuffer = new String[n * 2];
            strBuffer[0] = parts[0];
            tempPartsCity.add(parts[i]);
            getPatternHelper(tempPartsCity.toArray(new String[tempPartsCity.size()]), strBuffer, 1, 1, tempPartsCity.size());
            for (String x : allNames) {
                System.out.println("city names: " + x);
                if (ValidateCity(x)) {
                    System.out.println("in valid city");
                    finalCity = x;
                    tempPartsState = new ArrayList<String>();
                    for (int j = i + 1; j < parts.length; j++) {
                        tempPartsState.add(parts[j]);
                    }
                    allNames = new ArrayList<String>();
                    n = tempPartsState.size();
                    strBuffer = new String[n * 2];
                    strBuffer[0] = tempPartsState.get(0);
                    getPatternHelper(tempPartsState.toArray(new String[tempPartsState.size()]), strBuffer, 1, 1, tempPartsState.size());
                    for (String y : allNames) {
                        if (ValidateState(y)) {
                            finalState = y;
                            return finalCity + ", " + finalState;
                        }
                    }
                }
            }
        }
        
        return "invalid input";
    }

    private static boolean ValidateState(String temp) {
        if (stateSet.contains(temp))
            return true;
        return false;
    }

    private static boolean ValidateCity(String temp) {
        if (citySet.contains(temp))
            return true;
        return false;
    }

    static void getPatternHelper(String[] str, String[] buff, int i, int j, int n) {
        if (i == n) {
            StringBuilder builder = new StringBuilder();
            for (String s : buff) {
                if (s == null) {
                    break;
                }
                if (s == "-") {
                    builder = new StringBuilder(builder.toString().trim() + "-");
                } else
                    builder.append(s.trim() + " ");
            }
            System.out.println(builder.toString().trim());
            allNames.add(builder.toString().trim());
        } else {
            buff[j] = str[i];
            getPatternHelper(str, buff, i + 1, j + 1, n);

            // Or put a space followed by next character
            buff[j] = "-";
            buff[j + 1] = str[i];
            getPatternHelper(str, buff, i + 1, j + 2, n);
        }
    }
}
