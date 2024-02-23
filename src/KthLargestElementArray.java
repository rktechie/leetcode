import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem 215: Kth Largest Element in an Array
 * 
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class KthLargestElementArray {

    /*
     * Solution 1 is Quick Select. Time complexity is O(n).  If that would be a quicksort algorithm,
     * one would proceed recursively to use quicksort for the both parts that would result in O(NlogN) time complexity.
     * Here there is no need to deal with both parts since now one knows in which part to search for N - kth smallest element,
     * and that reduces average time complexity to O(N).
     *
     * Solution 2 and 3 both have the same running time i.e 17 ms. Time complexity is O(nlog(k)) and Space complexity is O(k).
     * In solution 2, we keep removing elements till only k elements are left.
     * In solution 3, we insert the elements in an descending sorted way. So we only pop k elements.
     */
    
    public int findKthLargest1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargestHelper(nums, 0, nums.length - 1, nums.length - k);
    }    

    public int findKthLargestHelper(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) return Integer.MAX_VALUE;
        
        int pivot = nums[end];// Take A[end] as the pivot, 
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);          
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]
        
        if (left == k)// Found kth smallest number
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargestHelper(nums, left + 1, end, k);
        else // Check left part
            return findKthLargestHelper(nums, start, left - 1, k);
    } 

    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;             
    }
    
    
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int x : nums)
            minHeap.add(x);
        
        while (minHeap.size() != k ) {
            minHeap.poll();
        }
        
        return minHeap.poll();
    }
    
    public int findKthLargest3(int[] nums, int k) {
    	  // in Java 8, the below code can be written as PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> a - b);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        
        for (int x : nums)
            maxHeap.add(x);
        
        while (k - 1 > 0) {
            maxHeap.poll();
            k--;
        }
        
        return maxHeap.poll();
    }
}
