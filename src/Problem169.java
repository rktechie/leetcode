import java.util.HashMap;

/*
 * Problem 169: Majority Element
 * 
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class Problem169 {

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int count = 0;
        for (int x : nums) {
            if (hashMap.containsKey(x))
                count = hashMap.get(x) + 1;
            else
                count = 1;
            
            if (count > nums.length / 2)
                return x;
            hashMap.put(x, count + 1);
        }
        return -1;
    }
}
