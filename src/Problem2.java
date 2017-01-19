
public class Problem2 {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(9);
		
		ListNode result = addTwoNumbers(l1, l2);
		while(result != null){
			System.out.println(result.val);
			result = result.next;;
		}
	}
	
	static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		 }
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root1 = l1;
		ListNode root2 = l2;
		ListNode result = null;
		ListNode head = null;
		int carry = 0;
		
		while(root1 != null || root2 != null || carry != 0){
			int data1 = root1 != null ? root1.val : 0;
			int data2 = root2 != null ? root2.val : 0;
			int sum = carry + data1 + data2;
			
			if(sum > 9){
				//carry = Integer.parseInt(Integer.toString(sum).subSequence(0, 1).toString());
				carry = sum / 10;
				sum = sum % 10;
			} else{
			    carry = 0;
			}
			if(result == null){
				result = new ListNode(sum);
				head = result;
			} else{
				result.next = new ListNode(sum);
				result = result.next;
			}
			
			root1 = root1 != null ? root1.next : null;
			root2 = root2 != null ? root2.next : null;
		}
		
        return head;
    }
}
