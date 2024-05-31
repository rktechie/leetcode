import java.util.HashSet;

/*
 * Problem 217: Contains Duplicate
 * 
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct. 
 */

public class ContainsDuplicate {
    
    public static void main (String args[]) {
        boolean x = containsDuplicate(new int[]{1,1});
        System.out.println(x);
    }
    
    /*
     * Very Imp Note:  
     * If we write 
     *      if (s.contains(nums[i]))
                return true;
            s.add(nums[i]);
            
            = We get *Time Limit Exceeded*
            
     * BUTTTTTT if we write 
     *      if (!s.add(nums[i]))
                return true;
                
            = It gets accepted. So do not the small level of optimization.
     */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> s = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!s.add(nums[i]))
                return true;
        }
        
        return false;
    }
}
