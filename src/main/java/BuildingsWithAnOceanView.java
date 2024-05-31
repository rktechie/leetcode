import java.util.ArrayList;
import java.util.List;

/*
1762. Buildings With an Ocean View
Medium
Topics
Companies
Hint
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.



Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.


Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 109
 */
public class BuildingsWithAnOceanView {

  /*
   * Approach: Monotonic Stack Space Optimization
   *
   * We converted this problem into finding the next greater or equal element for each element in the array.
   * If an element does not have a next greater or equal element, it means its view is not blocked.
   *
   * Do we really need to store all the greater or equal height buildings to the right of the current building in the stack?
   * As we iterated over the array from right to left, we pushed each building into the stack. Each building
   * would remain in the stack until we reached a taller building. At which point, the shorter building would
   * be popped from the stack.
   * This means that the tallest building seen so far would always be in the stack unless the current building
   * is the tallest building seen so far, in which case, the stack will be empty. Simply put, while traversing
   * from right to left, the current building will only have an ocean view if it is the tallest building seen so far.
   * Therefore, we can simplify the previous approach by traversing from right to left and just keep one
   * variable to denote the tallest building seen so far.
   *
   */
  public int[] findBuildings(int[] heights) {
    int n = heights.length;
    List<Integer> list = new ArrayList<>();
    int maxHeight = -1;

    for (int current = n - 1; current >= 0; --current) {
      // If there is no building higher (or equal) than the current one to its right, push it in the list.
      if (maxHeight < heights[current]) {
        list.add(current);
        // Update max building till now.
        maxHeight = heights[current];
      }
    }

    // Push building indices from list to answer array in reverse order.
    int[] answer = new int[list.size()];
    for (int i = 0; i < list.size(); ++i) {
      answer[i] = list.get(list.size() - 1 - i);
    }

    return answer;
  }
}
