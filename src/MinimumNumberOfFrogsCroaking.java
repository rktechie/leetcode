/*
1419. Minimum Number of Frogs Croaking

You are given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple "croak" are mixed.

Return the minimum number of different frogs to finish all the croaks in the given string.

A valid "croak" means a frog is printing five letters 'c', 'r', 'o', 'a', and 'k' sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of a valid "croak" return -1.



Example 1:

Input: croakOfFrogs = "croakcroak"
Output: 1
Explanation: One frog yelling "croak" twice.
Example 2:

Input: croakOfFrogs = "crcoakroak"
Output: 2
Explanation: The minimum number of frogs is two.
The first frog could yell "crcoakroak".
The second frog could yell later "crcoakroak".
Example 3:

Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from different frogs.


Constraints:

1 <= croakOfFrogs.length <= 105
croakOfFrogs is either 'c', 'r', 'o', 'a', or 'k'.
 */
public class MinimumNumberOfFrogsCroaking {

  /*
   * We can track how many frogs are 'singing' each letter in cnt:
   *
   * Increase number of frogs singing this letter, and decrease number singing previous letter.
   * When a frog sings 'c', we increase the number of (simultaneous) frogs.
   * When a frog sings 'k', we decrease the number of (simultaneous) frogs.
   * If some frog is singing a letter, but no frog sang the previous letter, we return -1.
   *
   */
  public int minNumberOfFrogs(String croakOfFrogs) {
    int[] count = new int[5];
    int frogs = 0, max_frogs = 0;

    for (var i = 0; i < croakOfFrogs.length(); ++i) {
      char ch = croakOfFrogs.charAt(i);
      int n = "croak".indexOf(ch);
      ++count[n];

      if (n == 0) // if character is c (start of yelling), incrementing the the number frogs
        max_frogs = Math.max(max_frogs, ++frogs);
      else if (--count[n - 1] < 0) // we should at least have one previous char for string to be valid
        return -1;
      else if (n == 4) // if character is k (end of yelling), decrementing the the number frogs
        --frogs;
    }

    return frogs == 0 ? max_frogs : -1; // at the end all yelling should be done
  }
}
