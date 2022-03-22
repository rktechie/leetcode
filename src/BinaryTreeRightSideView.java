import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem 199: Binary Tree Right Side View
 * 
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

You should return [1, 3, 4]. 
 */
public class BinaryTreeRightSideView {
	
	// Read solution 1 and 2 to understand DFS and BFS application

	/*
	 * Solution 1: DFS
	 * 
	 * The core idea of this algorithm:
	 * 1. At each depth of the tree, only select one node.
	 * 2. View depth is current size of result list.
	 * 
	 * Note: 
	 * - The traversal of the tree is NOT a standard pre-order traverse. It checks the RIGHT node first and then the LEFT
	 * - If reverse the visit order, that is first LEFT and then RIGHT, it will return the left view of the tree.
	 */
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		rightView(root, result, 0);
		return result;
	}

	public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
		if (curr == null) {
			return;
		}
		if (currDepth == result.size()) {
			result.add(curr.val);
		}

		rightView(curr.right, result, currDepth + 1);
		rightView(curr.left, result, currDepth + 1);
	}
    
    /*
     * Solution 2: BFS
     * 
     * Algo: Do the level order traversal, and add the last node on every layer.
     */
	public List<Integer> rightSideView2(TreeNode root) {
		if (root == null)
			return new ArrayList<>();

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		List<Integer> res = new ArrayList<>();

		while (!queue.isEmpty()) {
			int size = queue.size(); // this will capture the number of nodes at each level
			while (size-- > 0) { // run the loop till all the nodes are visited at that level
				TreeNode cur = queue.poll();
				if (size == 0) // this means we are at the last node at that level, so we add that node as its the right most node
					res.add(cur.val);

				if (cur.left != null)
					queue.offer(cur.left);
				if (cur.right != null)
					queue.offer(cur.right);
			}
		}

		return res;
	}
	
	/*
	 * Solution 3
	 */
    public List<Integer> rightSideView3(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> rt = new ArrayList<>();
        rt.add(root.val);
        
        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);
        rt.addAll(right);
        
        // To handle cases where the right tree is shorter than the left tree so we have to show that longer part of 
        // the left tree as it is visible from the Right Side View.
        if (left.size() > right.size()) {
            rt.addAll(left.subList(right.size(), left.size()));
        }

        return rt;
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
