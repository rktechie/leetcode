import java.util.HashSet;

/*
Question:
 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3. 
 */

public class Problem82 {

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

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode previous = null;
		ListNode current = head;
		HashSet<String> s = new HashSet<String>();

		if (head == null || head.next == null)
			return head;

		// Store all the repeated numbers
		while (current != null && current.next != null) {
			if (current.val == current.next.val)
				s.add(String.valueOf(current.val));
			current = current.next;
		}

		// Compare the current element with the elements in the set and remove the repeated numbers
		current = head;
		while (current != null) {
			if (previous == null) {
				if (s.contains(String.valueOf(current.val)))
					head = current.next;
				else
					previous = current;
			} else {
				if (s.contains(String.valueOf(current.val)))
					previous.next = current.next;
				else
					previous = current;
			}
			current = current.next;
		}

		return head;
	}

	// Solution on the internet: done in a single loop
	public ListNode deleteDuplicates_1(ListNode head) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (head != null) {
			if (head.next != null && head.val == head.next.val) {
				while (head.next != null && head.val == head.next.val)
					head = head.next;
			} else {
				cur.next = head;
				cur = cur.next;
			}
			head = head.next;
		}
		cur.next = null;
		return dummy.next;
	}
}
