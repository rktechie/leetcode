/**
 * Problem 86: Partition List.
 * 
 * 
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 * 
 * @author rishabh
 *
 */
public class PartitionList {

    public static void main(String[] args) {

    }

    /**
     * Maintain 2 separate lists.
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode leftdummy = new ListNode(-1);
        ListNode rightdummy = new ListNode(-1);
        ListNode lhead = leftdummy;
        ListNode rhead = rightdummy;

        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (cur.val < x) {
                lhead.next = cur;
                lhead = lhead.next;
            } else {
                rhead.next = cur;
                rhead = rhead.next;
            }
        }
        lhead.next = rightdummy.next;
        rhead.next = null;
        
        return leftdummy.next;
    }
}
