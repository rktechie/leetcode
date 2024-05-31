
/*
 * Problem 15: 3 Sum
 * 
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> t = threeSum(nums);
		for (List<Integer> x : t) {
			System.out.println(x.get(0) + "," + x.get(1) + "," + x.get(2));
		}
	}

	// The idea is to sort an input array and then run through all indices of a
	// possible first element of a triplet.
	// For each possible first element we make a standard bi-directional 2Sum
	// sweep of the remaining part of the
	// array.
	// Also we want to skip equal elements to avoid duplicates in the answer
	// without making a set or smth like that.
	//
	// Time Complexity: O(n^2). while loop (binary search part of the code) is O(n), and we call it n times.
	// Sorting the array takes O(nlogn), so overall complexity is O(nlogn+n^2).
	// This is asymptotically equivalent to O(n^2).
	//
	// Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new LinkedList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			// nums[i] != nums[i - 1] is to avoid duplicates
			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				int lo = i + 1;
				int hi = nums.length - 1;
				int sum = 0 - nums[i]; // in terms of binary search you can call this mid
				while (lo < hi) {
					if (nums[lo] + nums[hi] == sum) {
						res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1]) // to avoid duplicates
							lo++;
						while (lo < hi && nums[hi] == nums[hi - 1]) // to avoid duplicates
							hi--;
						// we keep continuing and not break even though we found one result
						// this is because there can be various other combinations
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < sum)
						lo++;
					else
						hi--;
				}
			}
		}
		return res;
	}

	// Use the logic of 2sum and binary search. (suddenly even this is time
	// limit exceeded)
	// (this and the above solution is pretty much the same)
	public static List<List<Integer>> threeSum1(int[] nums) {
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
			// We will use binary search to find two numbers such that their sum
			// = nums[i] * -1. So through this the sum of all 3 numbers will be
			// zero.
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
					// update the left and right because we dont have to stop.
					// We have to find all combinations.
					left++;
					right--;

					if (!result.contains(tempArrayList))
						result.add(tempArrayList);
				}
			}
		}

		return result;
	}

	// This is correct BUT time is exceeded. This is using the logic of 2sum
	// only.
	public static List<List<Integer>> threeSum3(int[] nums) {
		Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>();
		HashSet<String> uniqueCombination = new HashSet<String>();
		Integer[] tempArray = new Integer[3];
		String tempString = "";
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> tempArrayList = new ArrayList<Integer>();

		for (int x : nums) {
			if (hashtable.containsKey(x))
				hashtable.put(x, hashtable.get(x) + 1);
			else
				hashtable.put(x, 1);
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i == j)
					continue;
				int sum = (nums[i] + nums[j]) * -1;
				if (hashtable.containsKey(sum)) {
					if ((sum == nums[i] || sum == nums[j]) && hashtable.get(sum) == 1) {
						continue;
					}
					tempArray[0] = sum;
					tempArray[1] = nums[i];
					tempArray[2] = nums[j];
					Arrays.sort(tempArray);
					tempString = Arrays.toString(tempArray);
					if (uniqueCombination.add(tempString)) {
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
