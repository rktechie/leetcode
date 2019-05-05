import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem 23: Merge k Sorted Lists
 * 
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

NOTE: Look at both the solutions - they both are really good.

 */
public class MergeKSortedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 2nd solution:  With priority queue (better and easier to understand)
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length, (a,b) -> a.val-b.val);
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;
        
        for (ListNode node:lists)
            if (node!=null)
                queue.add(node); //we are adding the first node of each list to the queue
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            
            if (tail.next!=null)
                queue.add(tail.next); //add the rest of the internal nodes of the list to the queue
        }
        return dummy.next;
    }
	
	/*
	 * 1st solution: Divide and Conquer
	 * Code's complexity is also O(nlogk) where n means the total elements and k means the size of list.
	 * 
	 * So the progress of combination is like a full binary tree, from bottom to top.
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeTwoLists(lists[0], lists[1]);
        
        return mergeTwoLists(mergeKLists2(Arrays.copyOfRange(lists, 0, lists.length/2)), mergeKLists2(Arrays.copyOfRange(lists, lists.length/2, lists.length)));
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public class ListNode {
    	int val;
    	ListNode next;
    	ListNode(int x) { val = x; }
    }
}
