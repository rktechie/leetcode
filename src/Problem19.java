class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Problem19 {

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
