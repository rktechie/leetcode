import java.util.HashMap;
import java.util.Map;

/*
 * Problem 149: Max Points on a Line
 * 
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MaxPointsOnALine {

	/*
	 * Solution: Time Complexity O(n^2)
	 * 
	 * For points to be on the same line, they must have equal slopes.
	 * This does not mean that all the lines with the same slope lie on the same line as they 
	 * can be parallel lines too. So to eliminate this, we calculate gcd.
	 * 
	 * #### Observation 
	 * - If given n points, we can calculate all possible slopes. O(n^2) times 
	 * - For the two dots that generates the same slope, these dots could be on **parallel** slopes 
	 * - figure out how to prune the parallel dots
	 * 
	 * #### Trick: prune parallel dots using greatest common divider - GCD: 
	 * - Divide the x and y by their greatest common divider, such that x and y can 
	 *   be reduced to minimum value 
	 * - All other x and y can be reduced to such condition as well 
	 * - track the final reduced (x,y) in a map: they are the key to the count 
	 * - No need to use Map<Integer, Map<Integer, Integer>> to perform 2 level mapping to 
	 *   store all the coordinates; just `map<String, Integer>`, where the key is "x@y"
	 *   
	 * Note: we DO NOT find gcd of the points but instead of the delta/difference 
	 * i.e. GCD(x2-x1,y2-y1)
	 */

	public int maxPoints(int[][] points) {
		if (points == null)
			return 0;
		if (points.length <= 2)
			return points.length;

		Map<String, Integer> map = new HashMap<>();
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			map.clear();
			int duplicates = 0, max = 0;

			for (int j = i + 1; j < points.length; j++) {
				int x = points[j][0] - points[i][0]; // x2-x1
				int y = points[j][1] - points[i][1]; // y2-y1
				if (x == 0 && y == 0) {
					duplicates++;
					continue;
				}
				int gcd = generateGCD(x, y);
				if (gcd != 0) {
					x /= gcd;
					y /= gcd;
				}

				String key = x + "@" + y;
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
				max = Math.max(max, map.get(key));
			}

			result = Math.max(result, max + duplicates + 1); // max_count=max(max_count, max_count_i)
		}

		return result;
	}

	private int generateGCD(int a, int b) {
		if (b == 0)
			return a;
		else
			return generateGCD(b, a % b);
	}
}
