import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RishabhSnapchat {

    public static void main(String[] args) {
         String[] data = { "chat", "ever", "snapchat", "snap", "salesperson", "per", "person", "sales", "son", "whatsoever", "what", "so" };
         System.out.println(Arrays.asList(simpleWords(data)));

    }

    static String[] simpleWords(String[] data) {
        List<String> result = new ArrayList<String>();
        for (String word : data) {
            if (!isCompound(data, word)) {
                result.add(word);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    private static boolean isCompound(String[] data, String word) {
        return isCompoundHelper(data, word, 0);
    }

    private static boolean isCompoundHelper(String[] data, String currentWord, int index) {
        if (data == null || currentWord == null || currentWord.trim().isEmpty()) {
            return false;
        }
        for (String str : data) {
            if (str.equals(currentWord) && index > 0) {
                return true;
            }
            if (currentWord.startsWith(str)) {
                String subword = currentWord.substring(str.length());
                if (isCompoundHelper(data, subword, index + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}


