import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Problem15 {

	public static void main(String[] args) {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> t = threeSum(nums);
		for(List<Integer> x : t) {
			System.out.println(x.get(0) + "," + x.get(1) + "," + x.get(2));
		}
	}
	
	// Use the logic of 2sum and binary search. 
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		int middle = 0;
		int left = 0;
		int right = 0;
		int sum = 0;
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length; i++) {
			// Avoid duplicates
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			middle = 0 - nums[i];
			left = i + 1;
			right = nums.length - 1;
			// We will use binary search to find two numbers such that their sum = nums[i] * -1. So through this the sum of all 3 numbers will be zero.
			while (left < right) {
				sum = nums[left] + nums[right];
				if (middle > sum)
					left++;
				else if (middle < sum)
					right--;
				else {
					tempArrayList = new ArrayList<Integer>();
					tempArrayList.add(nums[i]);
					tempArrayList.add(nums[left]);
					tempArrayList.add(nums[right]);
					//update the left and right because we dont have to stop. We have to find all combinations.
					left++;
					right--;
					
					if (!result.contains(tempArrayList))
						result.add(tempArrayList);
				}
			}
		}
		
		return result;
	}
	
	// This is correct BUT time is exceeded. This is using the logic of 2sum only.
	public static List<List<Integer>> threeSum_2(int[] nums) {
		Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>();
		HashSet<String> uniqueCombination = new HashSet<String>();
		Integer[] tempArray = new Integer[3];
		String tempString = "";
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
		
		for(int x : nums) {
			if(hashtable.containsKey(x))
				hashtable.put(x, hashtable.get(x) + 1);
			else
				hashtable.put(x, 1);
		}
		
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if(i == j)
					continue;
				int sum = (nums[i] + nums[j]) * -1;
				if(hashtable.containsKey(sum)) {
					if((sum == nums[i] || sum == nums[j]) && hashtable.get(sum) == 1) {
						continue;
					}
					tempArray[0] = sum;
					tempArray[1] = nums[i];
					tempArray[2] = nums[j];
					Arrays.sort(tempArray);
					tempString = Arrays.toString(tempArray);
					if(uniqueCombination.add(tempString)) {
						tempArrayList = new ArrayList<Integer>();
						tempArrayList.add(tempArray[0]);
						tempArrayList.add(tempArray[1]);
						tempArrayList.add(tempArray[2]);
						result.add(tempArrayList);
					}
				}
			}
		}
		
		return result;
	}
}
