/*
Problem 427. Construct Quad Tree
Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.

Return the root of the Quad-Tree representing grid.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.

Example 2:



Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:



Constraints:

n == grid.length == grid[i].length
n == 2x where 0 <= x <= 6
 */
public class ConstructQuadTree {

  public Node construct(int[][] g) {
    return build(0, 0, g.length - 1, g.length - 1, g);
  }

  // r1 is start index of the row, r2 is last index of the row
  // c1 is start index of the column, c2 is last index of the column
  Node build(int r1, int c1, int r2, int c2, int[][] g) {
    if (r1 > r2 || c1 > c2)
      return null;

    boolean isLeaf = true; // start with the assumption its a leaf block
    int val = g[r1][c1];

    // if all the values are not same, then its not a leaf
    for (int i = r1; i <= r2; i++)
      for (int j = c1; j <= c2; j++)
        if (g[i][j] != val) {
          isLeaf = false;
          break;
        }

    // If current block is identified as Leaf Node then return it and set all the children values to null.
    if (isLeaf)
      return new Node(val == 1, true, null, null, null, null);

    int rowMid = (r1 + r2) / 2;
    int colMid = (c1 + c2) / 2;

    // Break current block into four parts and construct the Node.
    // By definition, non-leaf nodes val can be arbitrary. We can randomly set to true or false if its not a leaf
    // And hence here we can put either true or false.
    return new Node(
        false,
        false,
        build(r1, c1, rowMid, colMid, g), // top left
        build(r1, colMid + 1, rowMid, c2, g), // top right
        build(rowMid + 1, c1, r2, colMid, g), // bottom left
        build(rowMid + 1, colMid + 1, r2, c2, g) // bottom right
    );
  }

  class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
      val = _val;
      isLeaf = _isLeaf;
      topLeft = _topLeft;
      topRight = _topRight;
      bottomLeft = _bottomLeft;
      bottomRight = _bottomRight;
    }
  }
}
