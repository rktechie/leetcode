/*
 * Problem 148: Sort List
 * 
Sort a linked list in O(n log n) time using constant space complexity.
 */

public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
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
