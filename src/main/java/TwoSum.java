/*
 * Problem 1: Two Sum 
 * 
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

UPDATE (2016/2/13):
The return format had been changed to zero-based indices.
 */
import java.util.HashMap;

public class TwoSum {

	public static void main(String[] args) {
		int[] numbers = {2, 7, 11, 15};
		int[] ans = twoSum2(numbers, 22);
		for(int i : ans)
			System.out.println("Index: " + i);
	}
	
	public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        outer: for(int i = 0; i < nums.length - 1; i++){
        	for(int j = i + 1; j < nums.length; j++){
        		if((nums[i] + nums[j]) == target){
        			System.out.println("Index1="+(i + 1) + ", index2=" + (j + 1));
        			answer[0] = i + 1;
        			answer[1] = j + 1;
        			break outer;
        		}
        	}
        }
        
        return answer;
	}
	
	//Better solution
	public static int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int sum = target - nums[i];
			if (map.containsKey(sum)) {
				return new int[]{map.get(sum), i};
			} else {
				map.put(nums[i], i);
			}
		}

		return null;
	}
}
