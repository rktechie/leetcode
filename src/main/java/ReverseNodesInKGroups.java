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
		ListNode begin;
		if (head == null || head.next == null || k == 1) {
			return head;
		}
		ListNode dummyhead = new ListNode(-1);
		dummyhead.next = head;
		begin = dummyhead;
		int i = 0;
		while (head != null) {
			i++;
			if (i % k == 0) {
				begin = reverse(begin, head.next);
				head = begin.next;
			} else {
				head = head.next;
			}
		}

		return dummyhead.next;
	}

	public static ListNode reverse(ListNode begin, ListNode end) {
		ListNode curr = begin.next;
		ListNode next, first;
		ListNode prev = begin;
		first = curr;
		while (curr != end) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		begin.next = prev;
		first.next = curr;
		return first;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
