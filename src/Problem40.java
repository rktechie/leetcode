/*
 * Combination Sum II:
 * 
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    The solution set must not contain duplicate combinations.

For example, given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem40 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Used backtracking. We sort the candidates so that they are in a non decreasing order.
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		combination2SumHelper(candidates, target, result, new ArrayList<Integer>(), 0);
		return result;
	}

	// we add an element from candidate to the numbersSelectedTillNow list. 
	// We subtract this value from the target. If target is zero that means we have obtained the sum we are looking for.
	// After the recursive call we remove the element from the list because we have tried all combinations starting from that element. Now we move on to the next element.
	// start variable is used to track the element that we have to include now.
	public static void combination2SumHelper(int[] candidates, int target, List<List<Integer>> result, ArrayList<Integer> numbersSelectedTillNow, int start) {
		if (target == 0) {
			ArrayList<Integer> temp = new ArrayList<Integer>(numbersSelectedTillNow);
			if (!result.contains(temp)) 
				result.add(temp);
			return;
		}

		for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
			numbersSelectedTillNow.add(candidates[i]);
			// NOTE: Only this line is different from problem 39. we do 'i+1' so that we go on the next element.
			combination2SumHelper(candidates, target - candidates[i], result, numbersSelectedTillNow, i + 1);
			numbersSelectedTillNow.remove(numbersSelectedTillNow.size() - 1);
		}
	}
}
