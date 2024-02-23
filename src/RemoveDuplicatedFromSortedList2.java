import java.util.HashSet;

/*
 * Problem 82: Remove Duplicates from Sorted List II
 * 
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct 
numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3. 
 */

public class RemoveDuplicatedFromSortedList2 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);

		ListNode t = deleteDuplicates(head);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
	}

	// Solution on the internet: done in a single loop
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1, head);
		ListNode cur = dummy;
		while (head != null) {
			if (head.next != null && head.val == head.next.val) {
				while (head.next != null && head.val == head.next.val) // Move till the end of the duplicates sublist
					head = head.next;
				cur.next = head.next; // Skip all duplicates
			} else {
				cur = cur.next;
			}
			head = head.next;
		}

		return dummy.next;
	}
}
