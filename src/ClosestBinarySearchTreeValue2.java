import java.util.LinkedList;
import java.util.List;

/*
 * Problem 272 : Closest Binary Search Tree Value II
 * 
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class ClosestBinarySearchTreeValue2 {

	/*
	 * We just do a single in order traversal, and maintain a window size K.
	 * 
	 * We are doing an inorder traversal here, which guarantees that if we have to 
	 * remove an element from queue, we'll always the element with largest difference
	 * 
	 * if (root.val - target) is bigger than (list.peek().val - target), 
	 * then all of root's right child also has (child.val - target) > (list.peek().val - target)
	 * 
	 * Time complexity - O(N)
	 * We visit each node at most once during the traversal.
	 */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        collect(root, target, k, res);
        
        return res;
    }

    public void collect(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) 
        	return;
        
        collect(root.left, target, k, res);

				// if size k, add current and remove head if it's optimal, otherwise return
        if (res.size() == k) {
            if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst())) 
                res.removeFirst(); // as we traverse the tree inorder, the first node will always be the node with the largest diff, so we remove it if the list is of size k
            else 
            	return; // so that we dont add the current node or go to the right child as the difference with the target will keep getting bigger
        }
        
        res.add(root.val);
        
        collect(root.right, target, k, res);
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
