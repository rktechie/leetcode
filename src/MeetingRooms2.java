import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem 253: Meeting Rooms II
 * 
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRooms2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Solution 1
	 * Just add the meeting room to the queue, if no overlap, poll it and add the next meeting. 
	 * Whenever there's an overlap, add the next meeting to create an additional room. 
	 * Finally, just return the size of the priority queue for the total # of meeting rooms.
	 */
	public int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        
        PriorityQueue<int[]> heap = new PriorityQueue<>(intervals.length, (a, b) -> (a[1] - b[1]));
        heap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
        	// interval.start >= queue.interval.end
        	if(intervals[i][0] >= heap.peek()[1]) { // no overlap, then should update smallest end.
        		heap.poll();
            }
        	heap.offer(intervals[i]);
        }
        
        return heap.size(); // finally the heap contains all the meetings which are overlapping.
    }
	
	/*
	 * Solution 2: Explanation is at https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda
	 */
	public int minMeetingRooms2(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}
