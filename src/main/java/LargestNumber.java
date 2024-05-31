import java.util.Arrays;
import java.util.Comparator;

/*
 * Problem 179: Largest Number
 * 
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 */

/*
 * Solution: Sort and Join
 * 
[3, 30, 34, 5, 9] sort into [9, 5, 34, 3, 30]

Why 3 > 30 ?
Ans: Just brute force.

3 + 30 (330) > 30 + 3 (303)

In general, x > y when xy > yx.
 */
public class LargestNumber {

    public static void main(String args[]) {
        String x = largestNumber(new int[] { 3, 30, 34, 5, 9 });
        System.out.println(x);
    }

    public static String largestNumber(int[] nums) {
        int size = nums.length;
        if (size <= 0)
            return new String();
        if (size == 1)
            return String.valueOf(nums[0]);
        
        // Sorting Stage
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                String aa = "" + a + b;
                String bb = "" + b + a;
                return bb.compareTo(aa);
            }
        };
        
        Integer[] in = new Integer[size];
        for (int i = 0; i < size; ++i)
            in[i] = Integer.valueOf(nums[i]);
        
        Arrays.sort(in, comp);
        
//      This is to handle cases where input is [0, 0].
//      Without this while loop the output will be 00 whereas the output should be 0.
        int i = 0;
        while ((i < in.length - 1) && (in[i] == 0)) 
            ++i;
        
        // Joining Stage
        StringBuffer res = new StringBuffer();
        while (i < in.length)
            res.append(in[i++]);
        
        return res.toString();
    }
}
