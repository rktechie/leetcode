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
     * Solution 1 is Quick Select. Time complexity is O(n).  Quickselect, also known as Hoare's selection algorithm,
     * is an algorithm for finding the k'th smallest element in an unordered list.
     *
     * Quickselect uses the same idea as Quicksort:
     * First, we choose a pivot index. The most common way to choose the pivot is randomly. We partition nums
     * into 3 sections: elements equal to the pivot, elements greater than the pivot, and elements less than the pivot.
     * Next, we count the elements in each section
     *
     * If this would be a quicksort algorithm, one would proceed recursively to use quicksort for the
     * both parts that would result in O(NlogN) time complexity.
     * But here, there is no need to deal with both parts since we know in which part to search for N - kth smallest element,
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

    /*
     * Approach 4: Counting Sort
     * Counting sort is a non-comparison sorting algorithm. It can be used to sort an array of positive integers.
     * In this approach, we will sort the input using a slightly modified counting sort, and then return the k'th element from the end
     *
     * Actual Counting Sort algo:
     * - First, find the maxValue in the array. Create an array count with size maxValue + 1.
     * - Iterate over the array and find the frequency of each element. For each element num, increment count[num].
     * - Now we know the frequency of each element. Each index of count represents a number. Create a
     * new array sortedArr and iterate over count. For each index num, add count[num] copies of num to
     * sortedArr. Because we iterate over the indices in sorted order, we will also add elements to sortedArr in sorted order
     *
     * BUT: as we are associating indices with numbers, this algorithm will not work if negative numbers are in the input.
     * The constraints state that negative numbers can be in the input, so we need to account for this.
     *
     * Let's also find minValue, the minimum value in the array. Instead of count having a size of maxValue + 1,
     * we will make it have a size of maxValue - minValue + 1 (if minValue < 0, then this will appropriately
     * increase the size of count).
     * Now, we can just apply an offset of minValue when mapping numbers to indices and vice-versa.
     * When we populate count, given a num we will increment count[num - minValue]. count[num] will
     * represent the frequency of num + minValue.
     *
     * Optimization: Since we don't actually need to sort the array but only need to return the k'th largest value,
     * we will iterate over the indices of count in reverse order (get the larger numbers first).
     * At each number, we will decrement k (or a variable we initialize as remain = k to avoid modifying the input)
     * by the frequency. If remain <= 0, we can return the current number. With this optimization, we don't
     * need to create sortedArr, saving us O(n) space.
     *
     * Given n as the length of nums and m as maxValue - minValue
     * Time complexity: O(n+m)
     * Space complexity: O(m) - We create an array count with size O(m).
     */
    public int findKthLargest4(int[] nums, int k) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int num: nums) {
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
        }

        int[] count = new int[maxValue - minValue + 1];
        for (int num: nums) {
            count[num - minValue]++;
        }

        int remain = k;
        for (int num = count.length - 1; num >= 0; num--) {
            remain -= count[num];
            if (remain <= 0) {
                return num + minValue;
            }
        }

        return -1;
    }
}
