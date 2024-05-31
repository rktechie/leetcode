/*
 * Problem 165: Compare Version Numbers
 * 
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {

    public static void main(String args[]) {
        int x = compareVersion("1.0", "1");
        System.out.println(x);
    }

    public static int compareVersion(String version1, String version2) {
        String[] x1 = version1.split("\\.");
        String[] x2 = version2.split("\\.");
        int i = 0;
        int j = 0;

        for (i = 0, j = 0; i < x1.length && j < x2.length; i++, j++) {
            if (Integer.parseInt(x1[i]) > Integer.parseInt(x2[j]))
                return 1;
            else if (Integer.parseInt(x1[i]) < Integer.parseInt(x2[j]))
                return -1;
        }
        // We have to write a while loop to handle cases such as 1.0 and 1 OR 0.2.0 and 0.2
        while (i < x1.length && j == x2.length) {
            if (Integer.parseInt(x1[i]) > 0)
                return 1;
            i++;
        }
        while (i == x1.length && j < x2.length) {
            if (Integer.parseInt(x2[j]) > 0)
                return -1;
            j++;
        }
        return 0;
    }
}
