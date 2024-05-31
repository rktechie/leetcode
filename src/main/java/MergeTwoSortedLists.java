/*
 * Problem 21: Merge Two Sorted Lists
 * 
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 */

public class MergeTwoSortedLists {

	public static void main(String[] args) {

	}
	
	// Solution found on the internet
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}

		// To add the ending remaining elements of the list that is not empty
		if (l1 != null)
			cur.next = l1;
		if (l2 != null)
			cur.next = l2;

		return head.next; // head.next as we dont want the first node that we added i.e 0.
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}