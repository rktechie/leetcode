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
		ListNode tempHead = head;
		ListNode runner = head;
		ListNode previous = null;

		for (int i = 0; i < n; i++) {
			runner = runner.next;
		}
		while (runner != null) {
			previous = head;
			head = head.next;
			runner = runner.next;
		}
		if (previous == null) {
			tempHead = head.next;
		} else {
			previous.next = head.next;
		}

		return tempHead;
	}
}
