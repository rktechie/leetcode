import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem 90: Subsets II
 * 
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets.
 * 
 * Note:
 * 
 * Elements in a subset must be in non-descending order. The solution set must
 * not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,2], a solution is:
 * 
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * @author Rishabh Kedia
 *
 */
public class Problem90 {

    public static void main(String[] args) {
        
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        subsetsWithDupHelper(nums, 0, path, res);
        return res;
    }

    void subsetsWithDupHelper(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
        List<Integer> sub = new ArrayList<Integer>(path);
        res.add(sub);
        for (int i = start; i < nums.length; ++i) {
            // "i != start" is done to ensure the index doesn't become -1.  "nums[i] == nums[i - 1]" is to check if the contiguous numbers are same.
            if (i != start && nums[i] == nums[i - 1])
                continue;
            path.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
