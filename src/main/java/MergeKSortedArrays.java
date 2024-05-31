import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem : Merge k sorted arrays
 * 
Given k sorted arrays of size n each, merge them and print the sorted output.
Example:

Input:
k = 3, n =  4
arr[][] = { {1, 3, 5, 7},
            {2, 4, 6, 8},
            {0, 9, 10, 11}} ;

Output: 0 1 2 3 4 5 6 7 8 9 10 11
 */
public class MergeKSortedArrays {

	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };

		int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
		System.out.println(Arrays.toString(result));
	}

	/*
	 * Solution: Heap
	 * The time complexity is O(n*k*log(k)), where n is the total number of
	 * elements and k is the number of arrays. 
	 * 
	 * The loop runs n*k times. 
	 * In every iteration of loop, we call heapify which takes O(Logk) time.
	 * 
	 * It takes O(log(k)) to insert an element to the heap and 
	 * it takes O(log(k)) to delete the minimum element.
	 * 
	 * (Solution is similar to merge sorted lists problem)
	 */
	public static int[] mergeKSortedArray(int[][] arr) {
		// PriorityQueue is heap in Java
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>(
				(a,b) -> a.arr[a.index] - b.arr[b.index]
		);
		int totalNumbers = 0;

		// add arrays to heap
		for (int i = 0; i < arr.length; i++) {
			queue.add(new ArrayContainer(arr[i], 0));
			totalNumbers = totalNumbers + arr[i].length;
		}

		int m = 0;
		int result[] = new int[totalNumbers];

		// while heap is not empty
		while (!queue.isEmpty()) {
			ArrayContainer ac = queue.poll();
			result[m++] = ac.arr[ac.index];

			if (ac.index < ac.arr.length - 1) {
				queue.add(new ArrayContainer(ac.arr, ac.index + 1));
			}
		}

		return result;
	}
}

class ArrayContainer {
	int[] arr;
	int index;

	public ArrayContainer(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
}
