import java.util.ArrayList;
import java.util.List;

/*
 * Problem 254 : Factor Combinations
 * 
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */
public class FactorCombinations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution 1: Easy to understand. Start looking for factors from 2
	 * 
	 * Time complexity: O(NlogN). The explains are in the following:

	formula 1: time = (the number of nodes in the recursive tree) * (the time each node takes up)
	formula 2: the number of nodes in the recursive tree  = 
                 (the most number of branches among each node) ^ (the height of the tree)
	For the number of branches, it has at most N (from 2 to n) and in terms of the height of the tree, it should be logN since when the given number n is only decomposed by 2 will lead to the solution which has the longest length (pls take 32 as example in the description page). Thus, the number of nodes = (N)^(logN). And since each node only takes up O(1) time, therefore, the total time should be O(N^(logN))

	Space complexity: O(logN)
	Things will cost EXTRA space:
	1. the number of call stacks invoked = the height of the recursive tree = logN
	2. the item used to store the current solution which takes up logN at most (again, when the input n is only decomposed by 2)
	 */
	public List<List<Integer>> getFactors(int n) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    helper(result, new ArrayList<Integer>(), n, 2); // 2 is the lowest starting factor
	    return result;
	}

	public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
	    if (n == 1) {
	        if (item.size() > 1) {
	            result.add(new ArrayList<Integer>(item));
	        }
	        return;
	    }
	    
	    for (int i = start; i <= n; ++i) {
	        if (n % i == 0) { // i.e. n is a factor of i
	            item.add(i);
	            helper(result, item, n/i, i);
	            item.remove(item.size()-1);
	        }
	    }
	}
	
	/*
	 * Solution 2: Similar to solution 1 but more optimized version of solution 1
	 * To find the factors, we dont have to search till n, we have to search till math.sqrt(n)
	 */
	public List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <=3) {
            return results;
        }
        
        getFactors(n, 2, new ArrayList<Integer>(), results);
        return results;
    }
    
    private void getFactors(int n, int start, List<Integer> current, List<List<Integer>> results) {
        if (n == 1) {
        	if (current.size() > 1) {
        		results.add(new ArrayList<Integer>(current));
        	}
            return;
        }
	        
        
        for (int i = start; i <= (int) Math.sqrt(n); i++) {  // ==> here, change 1
            if (n % i != 0) {
                continue;
            }   
            current.add(i);
            getFactors(n/i, i, current, results);
            current.remove(current.size()-1);
        }
        
        current.add(n); // ===> here, change 2
        getFactors(1, n, current, results);
        current.remove(current.size()-1);
    }
}
