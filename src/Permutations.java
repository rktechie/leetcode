/*
 * Problem 46: Permutations
 * 
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static void main(String[] args) {

	}
	
	// Used backtracking. 
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		permuteHelper(nums, result, "", new ArrayList<Integer>());
		return result;
	}

	// If the size of the current list is same as the length of nums that means we can return the current list.
	// we maintain a string to track the indexes. indexes can be visited in random order so we cannot just simply
	// use an int counter variable.
	// after the recursive call we not only remove from the list the element we just added but also
	// remove the index we just added from the indexes list.
	public void permuteHelper(int[] nums, List<List<Integer>> result, String indexesVisited, ArrayList<Integer> currentList) {
		if (currentList.size() == nums.length) {
			ArrayList<Integer> temp = new ArrayList<Integer>(currentList);
			result.add(temp);
		}

		for (int i = 0; i < nums.length; i++) {
			if (indexesVisited.contains("-" + i)) // this means that this index has already been visited
				continue;
			currentList.add(nums[i]);
			indexesVisited = indexesVisited + "-" + i;
			permuteHelper(nums, result, indexesVisited, currentList);
			currentList.remove(currentList.size() - 1);
			indexesVisited = indexesVisited.substring(0, indexesVisited.length() - 1);
		}
	}
}
