/*
 * Problem 148: Sort List
 * 
Sort a linked list in O(n log n) time using constant space complexity.
 */

public class SortList {

    /**
     * Merge sort is a popularly known algorithm that follows the Divide and Conquer Strategy.
     * Divide phase: Divide the problem into subproblems.
     * Conquer phase: Repeatedly solve each subproblem independently and combine the result to form the original problem.
     *
     * The Top Down approach for merge sort recursively splits the original list into sublists of
     * equal sizes, sorts each sublist independently, and eventually merge the sorted lists.
     *
     * Time complexity - O(nlogn)
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) { // we do this to find the mid point for the split
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        
        return mergeLists(sortList(head), sortList(fast)); 
    }

    public ListNode mergeLists(ListNode x1, ListNode x2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        
        while (x1 != null && x2 != null) {
            if (x1.val <= x2.val) {
                current.next = x1;
                x1 = x1.next;
            } else {
                current.next = x2;
                x2 = x2.next;
            }
            current = current.next;
        }
        while (x1 != null) {
            current.next = x1;
            x1 = x1.next;
            current = current.next;
        }
        while (x2 != null) {
            current.next = x2;
            x2 = x2.next;
            current = current.next;
        }
        
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
