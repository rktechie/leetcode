/*
 * Problem 109: Convert Sorted List to Binary Search Tree
 * 
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

public class ConvertSortedListtoBinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);

        ListNode mid = calculateMidAndBreakList(head); // the list size constantly keeps updating because we keep breaking the list in the calculateMid method.
        TreeNode p = new TreeNode(mid.val);
        p.left = sortedListToBST(head);
        p.right = sortedListToBST(mid.next);
        
        return p;
    }

    /*
     * Fast-slow pointer is a good tool for find mid of a linked list.
     */
    public ListNode calculateMidAndBreakList(ListNode head) {
        ListNode previousSlow = head;
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            previousSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        previousSlow.next = null; // The most important step. We break the list at the mid value.
        
        return slow;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
