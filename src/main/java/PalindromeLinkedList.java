/*
 * Problem 234: Palindrome Linked List
 * 
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */

public class PalindromeLinkedList {

    //Solution 1: Reverse first half of the list and then compare.
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast != null) slow = slow.next;
        
        slow = reverse(slow);
        while(slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        return slow == null;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    //Solution 2: Reverse first half of the list and then compare. Solution 1 and 2 are the same the only difference is solution 1 uses fast-slow pointer trick and solution 2 counts the number of elements.
    public boolean isPalindrome2(ListNode head) {
        // traverse the list once to count the number of elements and determine the midpoint
        ListNode current = head;
        int nodeCount = 0;
        while (current != null) {
            nodeCount++;
            current = current.next;
        }
        
        int midCount = nodeCount / 2;
        
        // reverse the first half of the list
        current = head;
        ListNode previous = null;
        ListNode temp = null;
        for (int ii = 0; ii < midCount; ii++) {
            temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        
        // if there are an odd number of elements, skip the middle element
        if (nodeCount % 2 == 1) {
            current = current.next;
        }
        
        // compare the second half of the list to the reversed first half 
        // "previous" points to the head of the reversed first half
        for (int jj = 0; jj < midCount; jj++) {
            if (current.val != previous.val) {
                return false;
            } else {
                current = current.next;
                previous = previous.next;
            }
        }
        
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
