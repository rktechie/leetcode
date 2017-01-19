import java.util.List;

/*
 * Problem 120: Triangle
 * 
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle. 
 */

public class Triangle {
    /*
     * DP. Do not need additional spaces (happen in-place).
     * 
     * For the above example:
     * i = 2; cur = 2; next = 3; j = 0,1,2
     * i = 1; cur = 1; next = 2; j = 0,1
     * i = 0; cur = 0; next = 1; j = 0
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; --i) {
            List<Integer> cur = triangle.get(i);
            List<Integer> next = triangle.get(i + 1);
            for (int j = 0; j < i + 1; ++j) {
                cur.set(j, Math.min(next.get(j), next.get(j + 1)) + cur.get(j));
            }
        }
        return triangle == null ? 0 : triangle.get(0).get(0);
    }
}
