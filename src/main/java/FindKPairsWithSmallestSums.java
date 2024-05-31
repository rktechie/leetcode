import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem : Find K Pairs with Smallest Sums
 * 
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a
* pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution: Use PQ (very easy to understand description for such a hard problem. see the example below very carefully)
	 * 
	 * It is actually the same as how we merge k sorted list, in this question, the following are the k sorted list(each 
	 * number in nums1[] full-mesh with the numbers in nums2[].
	 * (1,2) -> (1,9) -> (1,10) -> (1,15)
	 * (7,2) -> (7,9) -> (7,10) -> (7,15)
	 * (11,2) -> (11,9) -> (11,10) -> (11,15)
	 * (16,2) -> (16,9) -> (16,10) -> (16,15)
	 * 
	 * Remember how we do in "merge k sorted list"? We simply add the head of the list into the heap and when a 
	 * node is poll(), we just add the node.next
	 */
  static class Node {
        int idx1;
        int idx2;
        int sum;
        public Node(int idx1, int idx2, int sum) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.sum = sum;
        }
    }
    
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0)
            return res;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> {
            return node1.sum - node2.sum;
        });
        
        // initialize pq
        for (int i = 0; i < nums1.length; i++) {
            pq.add(new Node(i, 0, nums1[i] + nums2[0])); // see the example in the soln to understand this. This is to recreate first node of each list as shown in the example
        }
        
        // get the k pairs!
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            // 1. pop from pq - we will always get the lowest sum pair
            Node cur = pq.remove();
            res.add(Arrays.asList(nums1[cur.idx1], nums2[cur.idx2]));
            // 2. push next node into pq - idx1 remains constant, but we incr idx2 so we can match idx1 with the next element in array nums2
            if (cur.idx2 < nums2.length - 1) {
                pq.add(new Node(cur.idx1, cur.idx2 + 1, nums1[cur.idx1] + nums2[cur.idx2 + 1]));
            }
        }
        
        return res;
    }
}
