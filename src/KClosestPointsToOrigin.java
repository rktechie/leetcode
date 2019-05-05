import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem 973: K Closest Points to Origin
 * 
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

Note: Go through both the solutions given below - they are very helpful in understanding pros and cons

Note: That while calculating the euclidean distance, calculate the distance of point from origin i.e. (0,0)
 */
public class KClosestPointsToOrigin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * 1st Solution: (most efficient) Based on Heap Sort 
	 * We don't have to sort all points.
	 * Instead, we can maintain a max-heap with size K. Then for each point, we add it to the heap. 
	 * Once the size of the heap is greater than K, we are supposed to extract one from the max heap to 
	 * ensure the size of the heap is always K. 
	 * Thus, the max heap is always maintain top K smallest elements from the first one to current one. 
	 * Once the size of the heap is over its maximum capacity, it will exclude the maximum element in it, 
	 * since it can not be the proper candidate anymore.
	 * 
	 * Theoretically, the time complexity is O(NlogK)
	 * 
	 * The advantage of this solution is it can deal with real-time(online) stream data. It does not have to know the size of the data previously.
	 * The disadvatage of this solution is it is not the most efficient solution.
	 */
	public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] p1, int[] p2) {
				return (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]);
			}
		});
        
        for (int[] point : points) {
        	heap.add(point);
        	if (heap.size() > K) {
        		heap.poll();
        	}
        }
        
        int[][] result = new int[K][2];
        while(K > 0) {
        	result[--K] = heap.poll();
        }
        
        return result;
    }
	
	/*
	 * Solution 2: 
	 * The very naive and simple solution is sorting the all points by their distance to the 
	 * origin point directly, then get the top k closest points. 
	 * We can use the sort function and the code is very short.
	 * 
	 * Theoretically, the time complexity is O(NlogN)
	 * 
	 * The advantages of this solution are short, intuitive and easy to implement.
	 * The disadvantages of this solution are not very efficient and have to know all of the points previously, and it is unable to deal with real-time(online) case, it is an off-line solution.
	 */
	public int[][] kClosest2(int[][] points, int K) {
	    Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]); //using lambda function the code is so short
	    return Arrays.copyOfRange(points, 0, K);
	}

}
