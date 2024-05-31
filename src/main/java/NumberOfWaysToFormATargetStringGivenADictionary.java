/*
1639. Number of Ways to Form a Target String Given a Dictionary
Hard
Topics
Companies
Hint
You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: words = ["acca","bbbb","caca"], target = "aba"
Output: 6
Explanation: There are 6 ways to form target.
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
Example 2:

Input: words = ["abba","baab"], target = "bab"
Output: 4
Explanation: There are 4 ways to form target.
"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 1000
All strings in words have the same length.
1 <= target.length <= 1000
words[i] and target contain only lowercase English letters.
 */
public class NumberOfWaysToFormATargetStringGivenADictionary {

  private final int mod = (int) 1e9 + 7; // as stated in problem statement as the number can be too large
  private int m;
  private int n;
  private String target;
  private Integer[][] f;
  private int[][] cnt;

  /*
   * Approach:
   *
   * Note: once we select a character, we cannot use the characters to left of that word or any other word.
   *
   * res[j] means the number of ways to form target j first characters.
   *
   * To understand the line: ans += 1L * dfs(i + 1, j + 1) * cnt[j][target.charAt(i) - 'a'];
   * - Lets say we select a char (eg: a) from position j of a word. Lets say other words also have 'a' at that same position
   * - It doesnt matter which word's 'a' we select as we only need to find the number of way we can reach target.
   * - thats why pre count all the chars and store them in a "cnt" array
   * - finally, to account for multiple words having 'a' at the same position, we simply multiply the result of dfs with the count
   *
   * We multiply the count with dfs result as we dont need to start
   */
  public int numWays(String[] words, String target) {
    m = target.length();
    n = words[0].length();
    f = new Integer[m][n];
    this.target = target;
    cnt = new int[n][26];

    // we are keeping a count of how many times a character is present at i th location across all the words
    // eg: at 1st position how many words have the character 'd'
    for (var w : words) {
      for (int i = 0; i < n; ++i) { // all the words are of same length
        cnt[i][w.charAt(i) - 'a']++;
      }
    }

    return dfs(0, 0);
  }

  private int dfs(int i, int j) {
    if (i >= m) { // base case: that means you have made the target word
      return 1;
    }
    if (j >= n) { // base case: that means we reached the end but still couldnt make the target word
      return 0;
    }
    if (f[i][j] != null) { // is the value already cached or memorized
      return f[i][j];
    }
    long ans = dfs(i, j + 1); // skip selecting jth pos char of any word
    ans += 1L * dfs(i + 1, j + 1) * cnt[j][target.charAt(i) - 'a']; // select jth pos char of any word.
    ans %= mod; // as stated in problem statement as the number can be too large
    return f[i][j] = (int) ans;
  }
}
