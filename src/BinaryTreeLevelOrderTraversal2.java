import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem 107: Binary Tree Level Order Traversal II
 * 
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

OJ's Binary Tree Serialization:

The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:

   1
  / \
 2   3
    /
   4
    \
     5

The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}". 
 */

public class BinaryTreeLevelOrderTraversal2 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        levelOrderBottomHelper(root, result, 0);
        Collections.reverse(result);
        return result;
    }

    public void levelOrderBottomHelper(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null)
            return;

        List<Integer> temp = level < result.size() ? result.get(level) : null;
        if (temp == null) {
            temp = new ArrayList<Integer>();
            temp.add(root.val);
            result.add(level, temp);
        } else {
            temp.add(root.val);
            result.set(level, temp);
        }
        levelOrderBottomHelper(root.left, result, level + 1);
        levelOrderBottomHelper(root.right, result, level + 1);
    }

    // 2nd method: Queue version. On the basis of 'Binary Tree Level Order Traversal', reverse the final vector.
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        List<Integer> level = new ArrayList<Integer>();

        while (true) {
            TreeNode node = q.poll();
            if (node != null) {
                level.add(node.val);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            } else {
                res.add(level);
                level = new ArrayList<Integer>();
                if (q.isEmpty() == true)
                    break;
                q.offer(null);
            }
        }
        Collections.reverse(res);
        return res;
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
