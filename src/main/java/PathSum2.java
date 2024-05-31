import java.util.ArrayList;
import java.util.List;

/*
 * Problem 113: Path Sum II
 * 
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

return

[
   [5,4,11,2],
   [5,8,4,5]
]

 */

public class PathSum2 {

    public static void main(String[] args) {
        
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        pathSumHelper(root, sum, result, new ArrayList<Integer>());
        return result;
    }
    
    /*
     * Used backtracking to track the path. Rest of the code is similar to Path Sum.
     */
    public void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> currentPath) {
        if (root == null)
            return;
        
        currentPath.add(root.val);
        if (root.left == null && root.right == null && root.val - sum == 0) {
            List<Integer> temp = new ArrayList<Integer>(currentPath);
            result.add(temp);
        }
        
        pathSumHelper(root.left, sum - root.val, result, currentPath);
        pathSumHelper(root.right, sum - root.val, result, currentPath);
        currentPath.remove(currentPath.size() - 1);
    }
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
