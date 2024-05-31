import java.util.Random;
import java.util.TreeMap;

/*
528. Random Pick with Weight
Solved
Medium
Topics
Companies
You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).

For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).


Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.


Constraints:

1 <= w.length <= 104
1 <= w[i] <= 105
pickIndex will be called at most 104 times.
 */
public class RandomPickWithWeight {

  /*
   * Approach: Prefix Sums with Binary Search
   *
   *
   * Time Complexity:
   * For the constructor function, the time complexity would be O(N), which is due to the construction of the prefix sums.
   * For the pickIndex() function, this time its time complexity would be O(logN), since we did a binary search on the prefix sums.
   * We can do linear search but then time complexity for pickIndex() function is O(n)
   *
   * Space Complexity:
   * For the constructor function, the space complexity remains O(N), which is again due to the construction of the prefix sums.
   * For the pickIndex() function, its space complexity would be O(1), since it uses constant memory
   *
   * Algo explanation of why prefixSum works:
   * Think that if we had an array [1,2,3,4,3]. Normal random pickIndex would pick any index from 0 to 4 with equal probability.
   * But we want that index=1 is picked by 2/13 probability, index=0 with 1/13 probability and so on. (13 is sum of weights).
   * To ensure that one way to think of it if we make a larger array (of size 13) where the values are the indices
   * such that index i is repeated w[i] times then if we do a normal rand on this array then index 0 to 12 will
   * be picked randomly with equal probability. 13 index array -> [0, 1,1, 2,2,2, 3,3,3,3, 4,4,4]. So there
   * is a 3/13 chance of picking 2 as 2 is repeated thrice in the new array.
   *
   * Now instead of actually constructing this 13 index array, we just know the range of the index of the 13 index array where value = i.
   * Eg:
   * For index = 0, range is {0,0}
   * index = 1, range of indices of the new array is {1,2}
   * index = 2, range={3,5}
   * index = 3, range ={6,9}
   * index = 4, range = {10,12}
   *
   * In other words,
   * index = 0, range is <1
   * index = 1, range is <3
   * index = 2, range is <6
   * index = 3, range is < 10
   * index = 4, range is < 13
   *
   * If you notice the above numbers 1,3,6,10,13 - they are cumulative sum.
   * The reason this happens is because for every range: right = left + (w[i] - 1) and left is (prev right+1).
   * So if we substitute 2nd equation into 1st. right = (prev right)+w[i]; i.e. keep adding prev sum to current weight.
   * Thus the prefixSum is able to implement this.
   *
   * Asked a follow up on how to change the code if the random call is made million times when list is constant
   * Ans: basically the ask is to make the pickIndex method O(1) instead of O(logn) at the expense of higher memory use.
   * To do so we can save all values into the array at init instead of a prefixSum array. E.g make an array that
   * maps the weights from 0 to N to their index. Then there is no need to perform a binary search anymore
   * OR use a dictionary where the key is the target offset generated and the value as the result obtained from the binary search for this targetoffset.
   *
   */
  class Solution {
    private int[] prefixSums;
    private int totalSum;

    public Solution(int[] w) {
      this.prefixSums = new int[w.length];

      int prefixSum = 0;
      for (int i = 0; i < w.length; ++i) {
        prefixSum += w[i];
        this.prefixSums[i] = prefixSum;
      }
      this.totalSum = prefixSum;
    }

    public int pickIndex() {
      double target = this.totalSum * Math.random(); // we generate a random number between 0 and 1. We then scale up this number, which will serve as our target offset.

      // run a binary search to find the target zone. we need to find the index where target < this.prefixSums[j]
      int low = 0, high = this.prefixSums.length;
      while (low < high) {
        // better to avoid the overflow
        int mid = low + (high - low) / 2;
        if (target > this.prefixSums[mid])
          low = mid + 1;
        else
          high = mid;
      }
      return low;
    }
  }

  class Solution2 {

    int cnt = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Random rnd = new Random();

    public Solution2(int[] w) {
      for (int idx = 0; idx < w.length; idx++) {
        cnt += w[idx];
        map.put(cnt, idx);
      }
    }

    public int pickIndex() {
      // int key= map.ceilingKey(rnd.nextInt(cnt)+1); don't forget to +1, because rand.nextInt(cnt) generate random integer in range [0,cnt-1]
      int key = map.higherKey(rnd.nextInt(cnt));
      return map.get(key);
    }
  }

}
