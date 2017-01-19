public class Problem25 {

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
		ListNode tempHead = head;
		int length = 0;
		int sets = 0;
		ListNode result = head;
		ListNode currentNode = head;
		ListNode previousNode = null;
		ListNode nextNode = null;

		while (tempHead != null) {
			tempHead = tempHead.next;
			length++;
		}
		sets = length / k;
		while ((sets--) != 0) {
			int i = k;
			while ((i--) != 0) {
				nextNode = currentNode.next;
				currentNode.next = previousNode;
				if (previousNode == null)
					head = currentNode;
				previousNode = currentNode;
				currentNode = nextNode;
			}
			previousNode.next = currentNode;
			System.out.println("PV: " + previousNode.val);
			System.out.println("CV: " + currentNode.val);
		}
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
