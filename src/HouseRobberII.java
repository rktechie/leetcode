/*
 * Problem 213: House Robber II
 * Note: This is an extension of House Robber.
 * 
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobberII {

    int rob(int[] num, int st, int len) {
        if (len == 0)
            return 0;
        if (len == 1)
            return num[st + 0];

        int[] P = new int[len];

        P[0] = num[st + 0];
        P[1] = Math.max(num[st + 0], num[st + 1]);

        for (int i = 2; i < len; i++) {
            P[i] = Math.max(num[st + i] + P[i - 2], P[i - 1]);
        }

        return P[len - 1];
    }

    public int rob(int[] nums) {

        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length - 1));
    }
}
