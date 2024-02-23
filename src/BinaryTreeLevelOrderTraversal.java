import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem 102 : Binary Tree Level Order Traversal
 * 
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


****
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
 *
 */

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        levelOrderHelper(root, result, 0);
        return result;
    }

    /*
     * DFS For each call down the tree, we increment the level. For each level a
     * separate list is maintained. We add a node to that particular list for
     * that level.
     */
    public void levelOrderHelper(TreeNode root, List<List<Integer>> result, int level) {
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
        levelOrderHelper(root.left, result, level + 1);
        levelOrderHelper(root.right, result, level + 1);
    }

    // 2nd method : BFS version by using queue. In order to separate the levels,
    // use 'NULL' as the end indicator of one level.
    public List<List<Integer>> levelOrder2(TreeNode root) {
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
                if (q.isEmpty())
                    break;
                q.offer(null);
            }
        }
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
