/*
367. Valid Perfect Square
Easy
Topics
Companies
Given a positive integer num, return true if num is a perfect square or false otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as sqrt.



Example 1:

Input: num = 16
Output: true
Explanation: We return true because 4 * 4 = 16 and 4 is an integer.
Example 2:

Input: num = 14
Output: false
Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.


Constraints:

1 <= num <= 231 - 1
 */
public class ValidPerfectSquare {

  /*
   * Approach 1: Binary Search
   *
   * For num > 2 the square root a is always less than num / 2 and greater than 1
   * Whenever we want to search in a sorted array, we use Binary Search.
   *
   * Time complexity : O(logN)
   * Space complexity : O(1)
   */
  public boolean isPerfectSquare(int num) {
    if (num < 2) {
      return true;
    }

    long left = 2;
    long right = num / 2;
    long mid, guessSquared;

    while (left <= right) {
      mid = (left + right) / 2;
      guessSquared = mid * mid;
      if (guessSquared == num) {
        return true;
      }
      if (guessSquared > num) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return false;
  }


  /*
   * Approach 2: Newton's Method
   *
   * Time complexity : O(logN)
   * Space complexity : O(1)
   */
  public boolean isPerfectSquare2(int num) {
    if (num < 2) {
      return true;
    }

    long x = num / 2;
    while (x * x > num) {
      x = (x + num / x) / 2;
    }
    return (x * x == num);
  }
}
