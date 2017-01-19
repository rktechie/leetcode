import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem 131: Palindrome Partitioning
 *
 * 
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

 */
public class PalindromePartitioning {

    public static void main(String[] args) {

    }

    /*
     * Solution : Recursion
     * 
     * i = 0 .. end
     * partition(s) = s[0..i] + partition(s[i+1 .. end]) if s[0..i] is palindromic
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if ("".equals(s))
            return result;
        if (s.length() == 1)
            return Arrays.asList(Arrays.asList(s));

        for (int i = 0; i < s.length(); i++) {
            String x = s.substring(0, i + 1);
            if (isPal(x)) {
                List<List<String>> sub = partition(s.substring(i + 1));
                // sub will be empty is s.substring(i + 1) is empty or if no more palindromes can be created from the substring.
                if (sub.isEmpty()) {
                    result.add(Arrays.asList(x));
                } else {
                    // Add x and the palindromes from the sub to the result
                    for (List<String> l : sub) {
                        ArrayList<String> temp = new ArrayList<String>();
                        temp.add(x);
                        temp.addAll(l);
                        result.add(temp);
                    }
                }
            }
        }

        return result;
    }

    // To check if string s is a palindrome of not
    boolean isPal(String s) {
        char[] temp = s.toCharArray();
        for (int i = 0; i < temp.length / 2; i++) {
            if (temp[i] != temp[temp.length - i - 1])
                return false;
        }

        return true;
    }
}
