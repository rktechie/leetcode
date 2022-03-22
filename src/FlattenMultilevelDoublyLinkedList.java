import java.util.Stack;

/*
 * Problem 430 : Flatten a Multilevel Doubly Linked List
 * 
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation:

The multilevel linked list in the input is as follows:



After flattening the multilevel linked list it becomes:


Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
Example 3:

Input: head = []
Output: []
 

How multilevel linked list is represented in test case:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 

Constraints:

Number of Nodes will not exceed 1000.
1 <= Node.val <= 10^5
 */
public class FlattenMultilevelDoublyLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Stack approach
	 * 
	 * Time Complexity: O(N) where N is the number of nodes in the list. 
	 * The DFS algorithm traverses each node once and only once.
	 * 
	 * Space Complexity: O(N) where N is the number of nodes in the list. 
	 * In the worst case, the binary tree might be extremely unbalanced (i.e. the tree leans to the left), 
	 * which corresponds to the case where nodes are chained with each other only with the child pointers. 
	 * In this case, the recursive calls would pile up, and it would take NN space in the function call stack.
	 * 
	 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/discuss/150321/Easy-Understanding-Java-beat-95.7-with-Explanation
	 */
	public Node flatten(Node head) {
		Node curt = head;
		Stack<Node> stack = new Stack<>(); // store curt.next when curt.child is not null

		while (curt != null) {
			if (curt.child != null) {
				stack.push(curt.next); // might be null
				curt.next = curt.child;
				if (curt.next != null)
					curt.next.prev = curt;
				curt.child = null;
			} else if (curt.next == null && !stack.isEmpty()) { // reached tail of child, reconnect the next of parent
				curt.next = stack.pop();
				if (curt.next != null)
					curt.next.prev = curt;
			}

			curt = curt.next;
		}

		return head;
	}

	/*
	 * Iterative approach
	 * Basic idea is straight forward:
	 * - Start form the head , move one step each time to the next node
	 * - When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, 
	 * by doing this we merged the child chain back to the main thread
	 * - Return to p and proceed until you find next node with child.
	 * - Repeat until reach null
	 * 
	 * Time and Space complexity is same as the above stack approach
	 */
	public Node flatten2(Node head) {
		if (head == null)
			return head;
		// Pointer
		Node p = head;
		while (p != null) {
			/* CASE 1: if no child, proceed */
			if (p.child == null) {
				p = p.next;
				continue;
			}
			/* CASE 2: got child, find the tail of the child and link it to p.next */
			Node temp = p.child;
			// Find the tail of the child
			while (temp.next != null)
				temp = temp.next;
			// Connect tail with p.next, if it is not null
			temp.next = p.next;
			if (p.next != null)
				p.next.prev = temp;
			// Connect p with p.child, and remove p.child
			p.next = p.child;
			p.child.prev = p;
			p.child = null;
		}
		return head;
	}
}

class Node {
	public int val;
	public Node prev;
	public Node next;
	public Node child;
}
