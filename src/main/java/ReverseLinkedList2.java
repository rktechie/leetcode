/*
 * Problem 92: Reverse Linked List II
 * 
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedList2 {

	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		head.next = new ListNode(5);
		// head.next.next = new ListNode(3);
		// head.next.next.next = new ListNode(4);
		// head.next.next.next.next = new ListNode(5);

		ListNode t = reverseBetween(head, 1, 2);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
	}

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode first = head;
		ListNode last = head;
		ListNode current = head;
		int i = 1;

		if ((m == 0 && n == 0) || m > n || m == n)
			return head;

		// keep iterating till first points to previous of left (m) and last points to right (n)
		while (true) {
			if (i == m - 1)
				first = current;
			if (i == n) {
				last = current;
				break;
			}
			current = current.next;
			i++;
		}

		ListNode end = last.next;
		ListNode previous = null;
		ListNode temp;
		current = first.next;
		System.out.println("First: " + first.val);
		System.out.println("Current:" + current.val);

		// Iteratively reverse the nodes starting from left
		while (current != end) {
			temp = current.next;
			if (previous == null) { // first time -> disconnect left and attach it to end (i.e. next of right)
				current.next = end;
			} else {
				current.next = previous;
			}
			previous = current;
			current = temp;
		}

		first.next = previous;

		return head;
	}
}
