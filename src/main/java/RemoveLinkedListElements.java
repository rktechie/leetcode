/*
 * Problem 203: Remove Linked List Elements
 * 
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5 
 */

public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return head;

        ListNode current = head;
        ListNode previous = null;
        while (current != null) {
            if (current.val == val) {
                if (previous == null)
                    head = current.next;
                else
                    previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }

        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
