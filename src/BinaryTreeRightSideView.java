import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> rt = new ArrayList<>();
        rt.add(root.val);
        
        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);
        rt.addAll(right);
        
        // To handle cases where the right tree is shorter than the left tree so we have to show that longer part of the left tree as it is visible from the Right Side View.
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
