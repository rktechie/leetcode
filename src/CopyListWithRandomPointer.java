import java.util.HashMap;
import java.util.Map;

/*
 * Problem 138 : Copy List with Random Pointer
 * 
A linked list is given such that each node contains an additional random pointer which could point 
to any node in the list or null.

Return a deep copy of the list.

Example 1:

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 
Note:
You must return the copy of the given head as a reference to the cloned list.
 */
public class CopyListWithRandomPointer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	// BOTH THE SOLUTIONS ARE REALLY FAST AND PERFECT. SO UNDERSTAND BOTH.

	class Node {
		public int val;
		public Node next;
		public Node random;

		public Node() {
		}

		public Node(int _val, Node _next, Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}

	/*
	 * Solution 1: iterating the list 3 times
	 * 
	 * THIS SOLUTION IS FASTER THAN SOLUTION 2 (by a small amount)
	 *
	 * Time Complexity : O(N)
	 * Space Complexity : O(1)
	 */
	public Node copyRandomList(Node head) {
		if (head == null)
			return null;

		// First round: make copy of each node,
		// and link them together side-by-side in a single list.
		Node cur = head;
		while (cur != null) {
			Node next = cur.next;
			cur.next = new Node(cur.val, next, null);
			cur = next;
		}

		// Second round: assign random pointers for the copy nodes.
		cur = head;
		while (cur != null) {
			if (cur.random != null)
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}

		// Third round: restore the original list, and extract the copy list.
		cur = head;
		Node copyHead = head.next;
		while (cur != null) {
			Node next = cur.next.next;
			Node copy = cur.next;
			cur.next = next;
			if (next != null)
				copy.next = next.next;
			cur = next;
		}

		return copyHead;
	}

	/*
	 * Solution 2: using hashmap
	 * 
	 * HashMap which holds old nodes as keys and new nodes as its values.
	 *
	 * Time Complexity : O(N)
	 * Space Complexity : O(N)
	 */
	public Node copyRandomList2(Node head) {
		if (head == null)
			return null;

		Map<Node, Node> map = new HashMap<Node, Node>();

		// loop 1. copy all the nodes
		Node node = head;
		while (node != null) {
			map.put(node, new Node(node.val, null, null));
			node = node.next;
		}

		// loop 2. assign next and random pointers
		node = head;
		while (node != null) {
			map.get(node).next = map.get(node.next);
			map.get(node).random = map.get(node.random);
			node = node.next;
		}

		return map.get(head);
	}
}
