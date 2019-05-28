import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem 297 : Serialize and Deserialize Binary Tree
 * 
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * The idea is simple: 
	 * print the tree in pre-order traversal and use "X" to denote null node and split node with ",". 
	 * We can use a StringBuilder for building the string on the fly. For deserializing, 
	 * we use a Queue to store the pre-order traversal and since we have "X" as null node, 
	 * we know exactly how to where to end building subtress.
	 */
	public String serialize(TreeNode root) {
		return serial(new StringBuilder(), root).toString();
	}

	// Generate preorder string
	private StringBuilder serial(StringBuilder str, TreeNode root) {
		if (root == null)
			return str.append("#");
		str.append(root.val).append(",");
		serial(str, root.left).append(",");
		serial(str, root.right);
		return str;
	}

	public TreeNode deserialize(String data) {
		return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
	}

	// Use queue to simplify position move
	private TreeNode deserial(Queue<String> q) {
		String val = q.poll();
		if ("#".equals(val))
			return null;
		TreeNode root = new TreeNode(Integer.valueOf(val));
		root.left = deserial(q);
		root.right = deserial(q);
		return root;
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
