/*
 * Problem 61: Rotate List
 * 
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */

public class RotateList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		
		ListNode t = rotateRight(head, 2);
		while (t != null) {
			System.out.println(t.val);
			t = t.next;
		}
	}
	
	// We get the length of the linked list.
	// Then move the current pointer such that it is k - 1 nodes away from the last node.
	public static ListNode rotateRight(ListNode head, int k) {
		ListNode current = head;
		ListNode last = head;
		int length = 1;
		
		if (head == null || head.next == null)
            return head;
		while (last.next != null) {
			last = last.next;
			length++;
		}
		k = k % length;
		for (int i = 0; i < length - k - 1; i++)
			current = current.next;
		last.next = head;
		head = current.next;
		current.next = null;
		
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