import java.util.ArrayList;
import java.util.List;

public class Problem47 {

	public static void main(String[] args) {
		
	}
    
	// This solution is right but I'm getting TLE for some reason. For explanation of this code see Problem 46
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		permuteUniqueHelper(nums, result, "", new ArrayList<Integer>());
		return result;
	}

	public void permuteUniqueHelper(int[] nums, List<List<Integer>> result, String currentIndex,
			ArrayList<Integer> currentList) {
		if (currentList.size() == nums.length) {
			ArrayList<Integer> temp = new ArrayList<Integer>(currentList);
			if (!result.contains(temp))
				result.add(temp);
		}

		for (int i = 0; i < nums.length; i++) {
			if (currentIndex.contains("-" + i))
				continue;
			currentList.add(nums[i]);
			currentIndex = currentIndex + "-" + i;
			permuteUniqueHelper(nums, result, currentIndex, currentList);
			currentList.remove(currentList.size() - 1);
			currentIndex = currentIndex.substring(0, currentIndex.length() - 1);
		}
	}
}
