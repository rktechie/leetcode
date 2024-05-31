import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
249. Group Shifted Strings
Medium
Topics
Companies
We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.



Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
Example 2:

Input: strings = ["a"]
Output: [["a"]]


Constraints:

1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
 */
public class GroupShiftedStrings {

  /*
   * Approach:
   *
   * Basically we need to form some sort of key for each word to group them. (Remember the idea of group all anagrams?)
   * Consider acf and pru. Now notice the difference between each characters.
   * acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use
   * integers ASCII difference to form key.
   * Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, just
   * make it positive(rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.
   *
   * if you do not append "," the hashmap cannot distinguish the case "abc" and "al" (the keys are "11" and
   * "11" respectively if we not append ",") , if we append "," the above case can be distinguished, the keys are "1, 1" and "11",
   *
   * Time complexity: O(N*K) - We iterate over all N strings and for each string, we iterate over all
   * the characters to generate the Hash value, which takes O(K) time. To sum up, the overall time complexity is O(N * K).
   *
   * Space complexity: O(N*K) - We need to store all the strings plus their Hash values in map.
   * In the worst scenario, when each string in the given list belongs to a different Hash value, the maximum
   * number of strings stored in map is 2*N. Each string takes at most O(K) space.
   * Hence the overall space complexity is O(N*K).
   */
  public List<List<String>> groupStrings(String[] strings) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strings) {
      String key = getKey(s);
      List<String> list = map.getOrDefault(key, new ArrayList<>());
      list.add(s);
      map.put(key, list);
    }
    return new ArrayList<>(map.values());
  }

  private String getKey(String s) {
    char[] chars = s.toCharArray();
    String key = "";
    for (int i = 1; i < chars.length; i++) {
      int diff = chars[i] - chars[i - 1];
      key += diff < 0 ? diff + 26 : diff;
      key += ",";
    }
    return key;
  }
}
