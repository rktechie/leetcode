import java.util.ArrayList;
import java.util.List;

/*
 * Problem 228: Summary Ranges
 * 
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 
 */

public class SummaryRanges {

    // Both Solution 1 and Solution 2 are the same. Solution 2 is just less lines of code. The logic of both is still the same.
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        int n = nums.length;
        if (n < 1)
            return result;
        if (n == 1) {
            result.add(nums[0] + "");
            return result;
        }

        String lastAdded = nums[0] + "";
        String sequence = nums[0] + "";
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                continue;
            } else {
                if (nums[i - 1] == Integer.parseInt(lastAdded)) {
                    result.add(nums[i - 1] + "");
                } else {
                    result.add(sequence + "->" + nums[i - 1]);
                }
                sequence = nums[i] + "";
                lastAdded = nums[i] + "";
            }
        }

        if (nums[n - 1] == nums[n - 2] + 1) {
            result.add(sequence + "->" + nums[n - 1]);
        } else {
            result.add(nums[n - 1] + "");
        }

        return result;
    }

    public List<String> summaryRanges2(int[] nums) {
        int length = nums.length;
        List<String> result = new ArrayList<String>(length);

        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (i < length - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (num != nums[i]) {
                result.add(num + "->" + nums[i]);
            } else {
                result.add(num + "");
            }
        }

        return result;
    }
}
