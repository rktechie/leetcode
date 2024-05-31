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
     * 
     * Here's the reason why it works. 
     * 1) XOR is commutative, a ^ b = b ^ a. 
     * 2) a number XOR by another number twice makes no change on original number, a ^ b ^ b = a
     * 
     * Therefore, if a number appears twice in the array, it makes no change to result. 
     * And if a number is unique, since 0 ^ unique = unique, so result = unique
     * 
     * 
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res = res^i;
        }
        return res;
    }
}
