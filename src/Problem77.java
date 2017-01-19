/*
 * Problem 77 : Combinations
 * 
 
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

 */

import java.util.ArrayList;
import java.util.List;

public class Problem77 {

	public static void main(String[] args) {
		List<List<Integer>> t = combine(4, 2);
		for (List<Integer> x : t) {
			for (int y : x)
				System.out.println(y);
			System.out.println();
		}
	}

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		combineHelper(n, k, 1, result, path);
		return result;
	}
	
	public static void combineHelper(int n, int k, int start, List<List<Integer>> result, ArrayList<Integer> path) {
		if (path.size() == k) {
			ArrayList<Integer> temp = new ArrayList<Integer>(path);
			result.add(temp);
			return;
		}
		for (int i = start; i <= n - (k - path.size()) + 1; i++) {
			path.add(i);
			combineHelper(n, k, i + 1, result, path);
			path.remove(path.size() - 1);
		}
	}
}
