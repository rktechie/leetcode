import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem 78: Subsets
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note:
 * 
 * Elements in a subset must be in non-descending order. The solution set must
 * not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,3], a solution is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * @author rishabh
 *
 */

public class Subsets {

    public static void main(String[] args) {
        
    }
    
    /**
     * Used backtracking.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        subsetsHelper(nums, 0, path, result);
        
        return result;
     }
    
    public List<List<Integer>> subsetsHelper(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        List<Integer> temp = new ArrayList<Integer>(path);
        result.add(temp);
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsHelper(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
        
        return result;
    }
}
