import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem 139: Word Break
 * 
 Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code". 
 */

public class WordBreak {
    private static HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
    
    public static void main (String args[]) {
        Set<String> wordDict = new HashSet<String>();
        wordDict.add("leet");
        wordDict.add("code");
        boolean x = wordBreak2("leetcode", wordDict);
        System.out.println(x);
    }
    
    // Method 1 : DP (didn't understand this)
    public boolean wordBreak1(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (wordDict.contains(s.substring(i,j+1)) && dp[j+1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
    
    // Method 2: Memorized searching + DFS :- (This is less efficient than DP)
    public static boolean wordBreak2(String s, Set<String> wordDict) {
        if (s.length() == 0)
            return true;
        if (hashMap.containsKey(s))
            return hashMap.get(s);
        
        for (int i = 1; i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if (wordDict.contains(temp)) {
                boolean b = wordBreak2(s.substring(i), wordDict);
                if (b == true) {
                    hashMap.put(s, true);
                    return true;
                }
            }
        }
        hashMap.put(s, false);
        return false;
    }
}
