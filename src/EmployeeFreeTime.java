import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem 759: Employee Free Time
 * 
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Algo: The idea is to just add all the intervals to the priority queue. (NOTE that it is not matter how many different people 
	 * are there for the algorithm because we just need to find a gap in the time line. 
	 * 
	 * 1. Priority queue - sorted by start time
	 * 2. Every time you poll from priority queue, just make sure it doesn't intersect with previous interval.
	 * This mean that there is no common interval. Everyone is free time.
	 * 
	 * Time complexity of the sort algorithm is O(nlogn) where n is the total number of intervals.
	 * Because each person's time schedule is already sorted, we can use that info and optimize the algorithm by 
	 * introducing a PriorityQueue storing a pointer to the person's schedule. Then the overall time complexity can be 
	 * reduced to min(O(n log K), where K is the total number of people.
	 */
	public List<Interval> employeeFreeTime(List<List<Interval>> avails) {

        List<Interval> result = new ArrayList<>();

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avails.forEach(e -> pq.addAll(e));

        Interval temp = pq.poll();
        while(!pq.isEmpty()) {
            if(temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll(); // becomes the next temp interval
            } else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    }

	/*
	 * Same as above but without PQ
	 */
	public List<Interval> employeeFreeTime2(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        avails.forEach(e -> timeLine.addAll(e));
        Collections.sort(timeLine, ((a, b) -> a.start - b.start));

        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            if(temp.end < each.start) {
                result.add(new Interval(temp.end, each.start));
                temp = each;
            }else{
                temp = temp.end < each.end ? each : temp;
            }
        }
        return result;
    }
	
	class Interval {
	    public int start;
	    public int end;

	    public Interval() {}

	    public Interval(int _start, int _end) {
	        start = _start;
	        end = _end;
	    }
	}
}
