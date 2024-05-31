import java.util.ArrayList;
import java.util.List;

/*
 * Problem : Find Leaves of Binary Tree
 * 
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []   
 */
public class FindLeavesOfBinaryTree {

	/*
	 * Solution: We need to take bottom-up approach. The key is to find the height of each node.
	 * 
	 * The height of a node is the number of edges from the node to the deepest leaf.
	 * The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]
	 */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper(list, root);
        return list;
    }
    
    // return the level of root
    private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        int level = 1 + Math.max(findLeavesHelper(list, root.left), findLeavesHelper(list, root.right));
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        root.left = root.right = null; // This is NOT necessary. We only do this because the question said to "remove all leaves". We will get the correct ans even without adding this line.
        
        return level;
    }
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
