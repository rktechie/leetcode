import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Combination Sum
 *  
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    The solution set must not contain duplicate combinations.

For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]

*/

public class Problem39 {

	public static void main(String[] args) {
		List<List<Integer>> t = combinationSum(new int[] {1,2}, 3);
		for (int i = 0; i < t.size(); i++) {
			for (int x : t.get(i))
				System.out.println("X: " + x);
		}
	}
	
	// Used backtracking. We sort the candidates so that they are in a non decreasing order.
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		combinationSumHelper(candidates, target, result, new ArrayList<Integer>(), 0);
		return result;
	}

	// we add an element from candidate to the numbersSelectedTillNow list. 
	// We subtract this value from the target. If target is zero that means we have obtained the sum we are looking for.
	// After the recursive call we remove the element from the list because we have tried all combinations starting from that element. Now we move on to the next element.
	// start variable is used to track the element that we have to include now.
	public static void combinationSumHelper(int[] candidates, int target, List<List<Integer>> result, ArrayList<Integer> numbersSelectedTillNow, int start) {
		if (target == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(numbersSelectedTillNow);
			result.add(temp);
			return;
		}

		for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
			numbersSelectedTillNow.add(candidates[i]);
			// we pass "i" in the function i.e the same element will be added again. We did this because an element can be repeated unlimited number of times. 
			combinationSumHelper(candidates, target - candidates[i], result, numbersSelectedTillNow, i);
			numbersSelectedTillNow.remove(numbersSelectedTillNow.size() - 1);
		}
	}
}
