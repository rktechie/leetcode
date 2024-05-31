import java.util.List;

/*
 * Problem 339 : Nested List Weight Sum
 * 
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class NestedListWeightSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * The algorithm takes O(N) time, where N is the total number of nested elements in the input list.
	 *
	 * Time complexity : O(N).
	 * Recursive functions can be a bit tricky to analyze, particularly when their implementation includes a loop.
	 * A good strategy is to start by determining how many times the recursive function is called, and then
	 * how many times the loop will iterate across all calls to the recursive function.
	 *
	 * The recursive function, dfs(...) is called exactly once for each nested list. As N also includes
	 * nested integers, we know that the number of recursive calls has to be less than N.
	 * On each nested list, it iterates over all of the nested elements directly inside that list (in other
	 * words, not nested further). As each nested element can only be directly inside one list, we know that
	 * there must only be one loop iteration for each nested element. This is a total of N loop iterations.
	 * So combined, we are performing at most 2*N recursive calls and loop iterations.
	 * We drop the 2 as it is a constant, leaving us with time complexity O(N)
	 *
	 * Space complexity : O(N).
	 * In terms of space, at most O(D) recursive calls are placed on the stack, where D is the maximum level
	 * of nesting in the input. For example, D=2 for the input [[1,1],2,[1,1]], and D=3 for the input [1,[4,[6]]].
	 * In the worst case, D=N, (e.g. the list [[[[[[]]]]]]) so the worst-case space complexity is O(N).
	 */
	public int depthSum(List<NestedInteger> nestedList) {
	    return depthSum(nestedList, 1);
	}

	public int depthSum(List<NestedInteger> list, int depth) {
	    int sum = 0;
	    for (NestedInteger n : list) {
	        if (n.isInteger()) {
	            sum += n.getInteger() * depth;
	        } else {
	            sum += depthSum(n.getList(), depth + 1);
	        }
	    }
	    
	    return sum;
	}

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	interface NestedInteger {
		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
}
