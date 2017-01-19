import java.util.ArrayList;
import java.util.List;

/*
 * Problem 216: Combination Sum III
 * 
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and 
each combination should be a unique set of numbers.

Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]


Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

 */

public class Problem216 {

    public static void main (String args[]) {
        List<List<Integer>> x = combinationSum3(3, 7);
    }
    
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        combinationSum3Helper(k, n, result, 1, new ArrayList<Integer>());
        
        return result;
    }
    
    /*
     * The solution is using backtracking and DFS.
     * Another way of backtracking is by using stacks. Try that next time.
     */
    public static void combinationSum3Helper(int targetSize, int targetSum, List<List<Integer>> result, int currentNumber, List<Integer> currentList) {
        if (targetSize == 0 && targetSum == 0){
            result.add(new ArrayList<Integer>(currentList));
        } else {
            for (int i = currentNumber; i <= 9 && targetSum > 0 && targetSize > 0; i++) {
                currentList.add(i);
                combinationSum3Helper(targetSize - 1, targetSum - i, result, i + 1, currentList);
                currentList.remove(currentList.size() - 1);
            }   
        }
    }
}
