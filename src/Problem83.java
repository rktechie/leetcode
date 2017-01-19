/*
Question:
 Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3. 
 */

public class Problem83 {

	public static void main(String[] args) {

	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode current = head;
		
		if (head == null || head.next == null)
			return head;
		while (current != null) {
			while (current.next != null && current.val == current.next.val) 
				current.next = current.next.next;
			current = current.next;
		}
		
		return head;
	}
}
