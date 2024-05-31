import java.util.HashMap;
import java.util.Map;

/*
691. Stickers to Spell Word
Hard
Topics
Companies
Hint
We are given n different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.



Example 1:

Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:

Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.


Constraints:

n == stickers.length
1 <= n <= 50
1 <= stickers[i].length <= 10
1 <= target.length <= 15
stickers[i] and target consist of lowercase English letters.
 */
public class StickersToSpellWord {

  /*
   * Approach: (DP + DFS) with memoization
   *
   * use a sorted string of target as the key for the unordered_map DP. A sorted target results in a
   * unique sub problem for possibly different strings.
   *
   * cache[s] is the minimum stickers required for string s (-1 if impossible). Note s is sorted.
   * Clearly, cache[""] = 0, and the problem asks for dp[target].
   *
   * The DP formula is:
   * cache[s] = min(1+cache[reduced_s]) for all stickers, here reduced_s is a new string after certain sticker applied
   *
   * Optimization: If the target can be spelled out by a group of stickers, at least one of them has
   * to contain character target[0]. So I explicitly require next sticker containing target[0],
   * which significantly reduced the search space.
   *
   * Time Complexity: O((m * (n + 26)) * 2^n), n - length of target, m - count of stickers
   * cache map can't contain more than 2^n keys- number of ways you can select different subset of characters
   * from target (next key character are sorted ascending)
   * Then to compute value for missing key we loop through m stickers and for each sticker it takes at
   * most max(n, 26) operations to build next key
   *
   *
   */
  public int minStickers(String[] stickers, String target) {
    int m = stickers.length;
    int[][] mp = new int[m][26]; // for each sticker, we count each individual char and store it
    for (int i = 0; i < m; i++) {
      for (char c : stickers[i].toCharArray()) {
        mp[i][c - 'a']++;
      }
    }

    // key is subsequence of target ; value is min num of stickers it takes to create that target string
    // we are going to use this as cache / memoization
    Map<String, Integer> cache = new HashMap<>();
    cache.put("", 0); // we add empty to indicate we have not used any stickers yet and lets start with the 0th sticker. this can also act as a base case when we finish processing all chars from target
    return helper(cache, mp, target);
  }

  private int helper(Map<String, Integer> cache, int[][] mp, String target) {
    if (cache.containsKey(target)) { // base case: if we have already cached it, return the value
      return cache.get(target);
    }

    int ans = Integer.MAX_VALUE; // to keep a count of how many stickers we have used
    int n = mp.length;
    int[] tar = new int[26];
    for (char c : target.toCharArray()) {
      tar[c - 'a']++;
    }

    // try every sticker and then call helper again for the remaining target string
    for (int i = 0; i < n; i++) {
      // optimization - if the current sticker does not contain the first char, then continue to other stickers
      if (mp[i][target.charAt(0) - 'a'] == 0) {
        continue;
      }
      StringBuilder sb = new StringBuilder(); // to track the remaining target string which could not be build using the current sticker
      // apply a sticker on every character a-z
      for (int j = 0; j < 26; j++) {
        if (tar[j] > 0) { // check if the current char exists in the target string before checking if the sticker has this char
          for (int k = 0; k < Math.max(0, tar[j] - mp[i][j]); k++) {
            sb.append((char) ('a' + j));
          }
        }
      }
      String s = sb.toString();
      int tmp = helper(cache, mp, s); // call dfs on the remaining target string that couldnt not be finished using the current sticker
      if (tmp != -1) {
        ans = Math.min(ans, 1 + tmp); // we do " 1+ " to count the current sticker in the calculation
      }
    }

    cache.put(target, ans == Integer.MAX_VALUE ? -1 : ans); // Integer.MAX_VALUE basically means we were unable to create the target string using the stickers
    return cache.get(target);
  }
}
