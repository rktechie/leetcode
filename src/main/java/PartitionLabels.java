import java.util.ArrayList;
import java.util.List;

/*
 * Problem 763: Partition Labels
 * 
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Approach: Greedy
	 *
	 * Traverse the string record the last index of each char.
	 * Use pointer to record end of the current sub string.
	 * 
	 * Time Complexity: O(N), where N is the length of S.
	 * Space Complexity: O(N).
	 */
  public List<Integer> partitionLabels(String S) {
    if (S == null || S.length() == 0) {
      return null;
    }
    List<Integer> list = new ArrayList<>();
    int[] map = new int[26];  // record the last index of the each char

    for (int i = 0; i < S.length(); i++) {
      map[S.charAt(i) - 'a'] = i;
    }
    // record the end index of the current sub string
    int last = 0;
    int start = 0;
    for (int i = 0; i < S.length(); i++) {
      last = Math.max(last, map[S.charAt(i) - 'a']);
      if (last == i) {
        list.add(last - start + 1);
        start = last + 1;
      }
    }
    return list;
  }
}
