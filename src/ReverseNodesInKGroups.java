/*
 * Problem 25: Reverse Nodes in k-Group
 * 
 * 
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroups {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		 head.next.next = new ListNode(3);
		// head.next.next.next = new ListNode(4);

		ListNode t = reverseKGroup(head, 2);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
	}

	// This is my solution but it is incomplete. I'm trying to reverse the list in the inner while loop.
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode tempHead = head;
		int length = 0;
		int sets = 0;
		ListNode result = head;
		ListNode currentNode = head;
		ListNode previousNode = null;
		ListNode nextNode = null;

		while (tempHead != null) {
			tempHead = tempHead.next;
			length++;
		}
		sets = length / k;
		while ((sets--) != 0) {
			int i = k;
			while ((i--) != 0) {
				nextNode = currentNode.next;
				currentNode.next = previousNode;
				if (previousNode == null)
					head = currentNode;
				previousNode = currentNode;
				currentNode = nextNode;
			}
			previousNode.next = currentNode;
			System.out.println("PV: " + previousNode.val);
			System.out.println("CV: " + currentNode.val);
		}
		return head;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
