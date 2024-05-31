/*
708. Insert into a Sorted Circular Linked List
Medium
Topics
Companies
Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.



Example 1:



Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.



Example 2:

Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
Example 3:

Input: head = [1], insertVal = 0
Output: [1,0]


Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-106 <= Node.val, insertVal <= 106
 */
public class InsertIntoASortedCircularLinkedList {

  /*
   * Approach: Two-Pointers Iteration
   *
   * Time Complexity: O(N) where N is the size of list. In the worst case, we would iterate through the entire list.
   * Space Complexity: O(1). It is a constant space solution
   */
  public Node insert(Node head, int insertVal) {
    if (head == null) {
      Node newNode = new Node(insertVal, null);
      newNode.next = newNode;
      return newNode;
    }

    Node prev = head;
    Node curr = head.next;
    boolean toInsert = false;

    do {
      if (prev.val <= insertVal && insertVal <= curr.val) {
        // Case 1: The value of new node sits between the minimal and maximal values of the current list. As a result, it
        // should be inserted within the list
        toInsert = true;
      } else if (prev.val > curr.val) {
        // Case 2:  The value of new node goes beyond the minimal and maximal values of the current list, either less than the
        // minimal value or greater than the maximal value. In either case, the new node should be added right after the tail node
        if (insertVal >= prev.val || insertVal <= curr.val)
          toInsert = true;
      }

      if (toInsert) {
        prev.next = new Node(insertVal, curr);
        return head;
      }

      prev = curr;
      curr = curr.next;
    } while (prev != head);

    // Case 3:  Finally, there is one case that does not fall into any of the above two cases. This is the case where the list contains uniform values.
    prev.next = new Node(insertVal, curr);
    return head;
  }

  /*
   * Approach: Same as above but without 2 pointers
   *
   * Time Complexity: O(N) where N is the size of list. In the worst case, we would iterate through the entire list.
   * Space Complexity: O(1). It is a constant space solution
   */
  public Node insert2(Node head, int insertVal) {
    if (head == null) {
      Node newNode = new Node(insertVal);
      newNode.next = newNode;
      return newNode;
    }
    Node node = head;

    while (node.next != head) {
      if (node.val <= node.next.val) {
        if (insertVal >= node.val && insertVal <= node.next.val) {
          break;
        }
      } else {
        if (insertVal >= node.val || insertVal <= node.next.val) {
          break;
        }
      }
      node = node.next;
    }

    Node next = node.next;
    node.next = new Node(insertVal, next);
    return head;
  }

  class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _next) {
      val = _val;
      next = _next;
    }
  }
}
