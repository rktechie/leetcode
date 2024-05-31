import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Problem 621 : Task Scheduler
 * 
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class TaskScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Idea is to add them to a priority Q and sort based on the highest frequency.
	 * And pick the task in each round of 'n' with highest frequency. 
	 * As you pick the task, decrease the frequency, and put them in another list instead of adding them back in the queue immediately.
	 * As it is removed from the queue at the moment, this ensures that we dont keep iterating over the same task in the same interval again and again. 
	 * 
	 * After the inner while loop, we now loop through the list to see which tasks are still left and then we add them back to the queue.
	 * 
	 * The idea used here is similar to - https://leetcode.com/problems/rearrange-string-k-distance-apart
	 * We need to arrange the characters in string such that each same character is K distance apart, where distance in this problems is time b/w two similar task execution.
	 */
	public int leastInterval(char[] tasks, int n) {
	     Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < tasks.length; i++) {
	        map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
	    }
	    
	    PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
	            (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

	    q.addAll(map.entrySet());

	    int count = 0;
	    while (!q.isEmpty()) { // when queue is not empty, there are remaining tasks
	        int interval = n + 1; // for each interval
	        List<Map.Entry> tempList = new ArrayList<>(); // list used to update queue
	        
	        while (interval > 0 && !q.isEmpty()) { // fill the intervals with the next high freq task
	            Map.Entry<Character, Integer> top = q.poll(); // most frequency task
	            top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
	            tempList.add(top); // collect task to add back to queue
	            interval--; // interval shrinks
	            count++; //one slot is taken i.e. successfully executed task
	        }

	        for (Map.Entry<Character, Integer> e : tempList) { // NOTE: the list contains the whole entry itself and not just the task name or val
	            if (e.getValue() > 0) // when there are tasks left
	            	q.add(e); // add valid tasks 
	        }

	        if (q.isEmpty()) // job done 
	        	break;
	        
	        count = count + interval; // if interval > 0, then it means we need to be idle
	    }
	    
	    return count;
	}
}
