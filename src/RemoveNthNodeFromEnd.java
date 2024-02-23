/*
 * Problem 19: Remove Nth Node From End of List
 * 
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	ListNode(int x, ListNode node) {
		val = x;
		next = node;
	}
}

public class RemoveNthNodeFromEnd {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		// head.next = new ListNode(2);
		ListNode t = removeNthFromEnd(head, 1);
		for (int i = 0; t != null; t = t.next) {
			System.out.println(t.val);
		}
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode fast = head;
		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}

		ListNode prev = null;
		ListNode slow = head;
		while (fast != null) {
			if (prev == null) { // if initializing prev for the 1st time
				prev = head;
			} else {
				prev = prev.next;
			}
			fast = fast.next;
			slow = slow.next;
		}

		if (prev == null) { // if the element to be removed is the 1st node
			return head.next;
		} else {
			prev.next = slow.next;
			return head;
		}
	}
}
