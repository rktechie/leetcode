import java.util.LinkedList;
import java.util.List;

/*
 * Problem : Insert Interval
 * 
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution 1: Same as solution 2 but a little more optimized as we dont use an external memory
	 */
	public int[][] insert(int[][] intervals, int[] newInterval) {
		int i = 0;
		
		while (i < intervals.length && intervals[i][1] < newInterval[0])
			i++;
		
		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval = new int[] { Math.min(intervals[i][0], newInterval[0]),
					Math.max(intervals[i][1], newInterval[1]) };
			intervals.remove(i);
		}
		
		intervals.add(i, newInterval);
		
		return intervals;
	}
	
	/*
	 * Solution 2: Very straight forward
	 */
	public int[][] insert2(int[][] intervals, int[] newInterval) {
		List<int[]> result = new LinkedList<>();
		int i = 0;
		
		// add all the intervals ending before newInterval starts
		while (i < intervals.length && intervals[i][1] < newInterval[0])
			result.add(intervals[i++]);
		
		// merge all overlapping intervals to one considering newInterval
		// we compare intervals start with targets end
		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval = new int[] { // we could mutate newInterval here also
					Math.min(newInterval[0], intervals[i][0]),
					Math.max(newInterval[1], intervals[i][1])};
			i++;
		}
		
		result.add(newInterval); // add the union of intervals we got
		
		// add all the rest
		while (i < intervals.length)
			result.add(intervals[i++]);
		
		return result.toArray(new int[result.size()][2]);
	}
}
