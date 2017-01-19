public class Problem24 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		
		ListNode t = swapPairs(head);
		while(t != null) {
			System.out.println(t.val);
			t = t.next;
		}
	}

	public static ListNode swapPairs(ListNode head) {
		// Eg: 1->2->3->4
		// After 1st loop : 2->1->3->4
		// After 2nd loop : 2->1->4->3
		ListNode currentNode = head;
		ListNode previousNode = null;
		ListNode nextNode = null;
		while (currentNode != null && currentNode.next != null) {
			nextNode = currentNode.next;
			if (previousNode == null) {  // this is only for first loop
				head = nextNode;
				previousNode = nextNode;
			} else { // This is for all the other loops after the first one
				
				// Assume now the list is : 2->1->3->4 
				previousNode = currentNode; // previous node = 1 
				currentNode = nextNode; // current node = 3
				nextNode = currentNode.next; // next node = 4

				// This condition is there for cases such as : 1->2->3
				if (nextNode == null)
					break;
				
				previousNode.next = nextNode; // 1->4
			}
			
			// This code is for swapping
			currentNode.next = nextNode.next; // 4->null
			nextNode.next = currentNode;	// 3->4
			
			// Therefore after the 2nd loop in our example we get : 1->2->3->4
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
