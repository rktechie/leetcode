import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem 56: Merge Intervals
 * 
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * The idea is to sort the intervals by their starting points. 
	 * Then, we take the first interval and compare its end with the next intervals starts. 
	 * As long as they overlap, we update the end to be the max end of the overlapping intervals. 
	 * Once we find a non overlapping interval, we can add the previous "extended" interval and start over.
	 * 
	 * Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n)).
	 * 
	 * I used an a lambda comparator (Java 8) and a for-each loop to try to keep the code clean and simple.
	 */
	public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if(intervals.length == 0 || intervals == null) 
        	return res.toArray(new int[0][]);
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for(int[] i : intervals) {
            if(i[0] <= end) {
                end = Math.max(end, i[1]);
            }
            else {
                res.add(new int[]{start, end});
                start = i[0];
                end = i[1];
            }
        }
        res.add(new int[]{start, end});
       return res.toArray(new int[0][]);
         
    }

}
