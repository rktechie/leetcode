/*
 * Problem 198: House Robber
 * 
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is 
that adjacent houses have security system connected and it will automatically contact the police if two adjacent 
houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class Problem198 {

    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        
        int[] p = new int[nums.length];
        p[0] = nums[0];
        p[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < p.length; i++) {
            p[i] = Math.max(p[i - 1], p[i - 2] + nums[i]);
        }
        
        return p[p.length - 1];
    }
}
