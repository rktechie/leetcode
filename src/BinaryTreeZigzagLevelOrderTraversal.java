import java.util.ArrayList;
import java.util.List;

/*
 * Problem 103. Binary Tree Zigzag Level Order Traversal.
 * 
 * 

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
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
 *
 */

public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        zigzagLevelOrderHelper(root, result, 0);
        return result;
    }
    
    /*
     * For each call down the tree, we increment the level.
     * For each level a separate list is maintained. We add a node to that particular list for that level.
     * If the level is an even number, we add normally i.e. add a node at the end of the list.
     * If the level is an odd number, we add the node at the beginning position of the list.
     */
    public void zigzagLevelOrderHelper(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null)
            return;
        
        List<Integer> temp = level < result.size() ? result.get(level) : null;
        if (temp == null) {
            temp = new ArrayList<Integer>();
            temp.add(root.val);
            result.add(level, temp);
        } else {
            if (level % 2 == 0)
                temp.add(root.val);
            else
                temp.add(0, root.val);
            result.set(level, temp);
        }
            zigzagLevelOrderHelper(root.left, result, level + 1);
            zigzagLevelOrderHelper(root.right, result, level + 1);
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
