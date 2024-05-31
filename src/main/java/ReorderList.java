/*
 * Problem 143: Reorder List
 * 
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}. 
 */
public class ReorderList {

    public static void main(String args[]) {
        ListNode x = new ListNode(1);
        x.next = new ListNode(2);
        x.next.next = new ListNode(3);
        x.next.next.next = new ListNode(4);
        reorderList(x);
    }

    /*
     * Reorder can be achieved via
     * 1. cut from the middle into left and right
     * 2. reverse the right part
     * 3. join the linked list
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        // Find the middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;

        // Reverse the list middle onwards
        ListNode prev = null;
        while (fast != null) {
            ListNode next = fast.next;
            fast.next = prev;
            prev = fast;
            fast = next;
        }

        // Join the list in the desired way
        slow = head;
        fast = prev;
        while (fast != null) {
            ListNode slowTemp = slow.next;
            slow.next = fast;
            ListNode fastTemp = fast.next;
            fast.next = slowTemp;
            fast = fastTemp;
            slow = slowTemp;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
