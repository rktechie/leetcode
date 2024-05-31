/*
670. Maximum Swap
Medium
Topics
Companies
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
 */
public class MaximumSwap {

  /*
   * Approach: Greedy
   * At each digit of the input number in order, if there is a larger digit that occurs later, we know
   * that the best swap must occur with the digit we are currently considering.
   *
   * 1. Use buckets to record the last position of digit 0 ~ 9 in this num. If the count a digit is more than 1 (eg: 145899),
   * where 9 is repeated, we will store only the last position of that digit (eg: num = 145899 then last[9] = 5).
   * 2. Loop through the num array from left to right.
   * 3. For each position, we check whether there exists a larger digit in this num (start from 9 to current digit).
   * 4. We also need to make sure the position of this larger digit is behind the current one.
   * 5. If we find it, simply swap these two digits and return the result.
   *
   * Time Complexity: O(N)
   * Space Complexity: O(N) as we keep each digit in the array A
   */
  public int maximumSwap(int num) {
    char[] digits = Integer.toString(num).toCharArray();
    int[] last = new int[10];
    for (int i = 0; i < digits.length; i++) {
      last[digits[i] - '0'] = i;
    }

    for (int i = 0; i < digits.length; i++) {
      for (int d = 9; d > digits[i] - '0'; d--) { // we make sure to only check till d is higher than the current element
        if (last[d] > i) {
          char tmp = digits[i];
          digits[i] = digits[last[d]];
          digits[last[d]] = tmp;
          return Integer.parseInt(new String(digits));
        }
      }
    }

    return num;
  }
}
