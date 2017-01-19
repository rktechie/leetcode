import java.util.HashMap;

/*
 * Problem 205: Isomorphic Strings
 * 
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
 */

public class Problem205 {

    public static void main(String arg[]) {
        boolean x = isIsomorphic("ab", "ca");
        System.out.println(x);
    }

    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> hashMap = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                if (hashMap.get(s.charAt(i)) != t.charAt(i))
                    return false;
            } else {
                if (hashMap.containsValue(t.charAt(i)))
                    return false;
                hashMap.put(s.charAt(i), t.charAt(i));
            }
        }

        return true;
    }
}
