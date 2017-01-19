/*
 * Problem 136: Single Number
 * 
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 
 */

public class SingleNumber {

    /*
     * The simplest solution is to use hashtable/hashset. But usage of hashtable/hashset means using extra memory.
     * To do without extra memory, we will use bit manipulation. In our case, we use XOR.
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res = res^i;
        }
        return res;
    }
}
