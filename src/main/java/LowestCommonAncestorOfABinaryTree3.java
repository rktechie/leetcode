import java.util.HashSet;
import java.util.Set;

/*
1650. Lowest Common Ancestor of a Binary Tree III
Medium
Topics
Companies
Hint
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
 */
public class LowestCommonAncestorOfABinaryTree3 {

  /*
   * Approach: concept of two runners on the circle track
   *
   * While iterating, there is a possibility that one runner will reach the parent faster and then null
   * Thats why we reset faster runner to the slower runners start posn
   * After the reset, the original slow runner is still running and will eventually reach parent and then null
   * We will reset slow runners posn to the fast runners original posn
   * Now the original gap is bridged/gone and thats why this time both the runners will hit the LCA at the same time.
   *
   *
   */
  public Node lowestCommonAncestor(Node p, Node q) {
    Node a = p, b = q;
    while (a != b) {
      a = a == null ? q : a.parent;
      b = b == null ? p : b.parent;
    }
    return a;
  }

  // much easier to understand but this uses extra space - HashSet
  public Node lowestCommonAncestor2(Node p, Node q) {
    final Set<Node> set = new HashSet<>();
    while (p != null && set.add(p)) {
      p = p.parent;
    }
    while (q != null && !set.contains(q)) {
      q = q.parent;
    }
    return q;
  }

  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  };
}
