import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Problem 359: Logger Rate Limiter
 * 
Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class LoggerRateLimiter {

	/*
	 * LOOK AT BOTH THE SOLUTIONS
	 * Solution 1 makes sense in terms on competitive programming and quick understanding.
	 * In this the hashmap can blow up and we can get memory issues in real life but we dont
	 * get any errors on leetcode.
	 * 
	 * But, Solution 2 is much better in terms on a interview question as it keeps memory in check.
	 */
	
	/*
	 * Solution 1: 
	 * Instead of logging print times, I store when it's ok for a message to be printed again
	 */
	class Logger1 {
	    private Map<String, Integer> ok;
	    
	    public Logger1() {
	    	ok = new HashMap<>();
	    }

	    public boolean shouldPrintMessage(int timestamp, String message) {
	        if (timestamp < ok.getOrDefault(message, 0))
	            return false;
	        ok.put(message, timestamp + 10);
	        return true;
	    }
	}
	
	/*
	 * Solution 2:
	 * 
	 * I keep a heap to get rid of the old message and set of String to keep the recent messages only.
	 * 
	 */
	class Logger2 {
	    Queue<Tuple> queue = new ArrayDeque<>();
	    Set<String> dict = new HashSet<>();
	  
	    public Logger2() {}
	    
	    public boolean shouldPrintMessage(int timestamp, String message) {
	        while (!queue.isEmpty() && queue.peek().time <= timestamp - 10) { // clear the queue of old messages
	            Tuple next = queue.poll();
	            dict.remove(next.msg);
	        }
	        if (!dict.contains(message)) { // check after clearing old messages, if current message is still there then you cannot print it else you can print it 
	            queue.offer(new Tuple(timestamp, message));
	            dict.add(message);
	            return true;
	        }
	        
	        return false;
	    }
	    
	    private class Tuple {
	        int time; 
	        String msg;
	        public Tuple(int t, String msg) {
	            this.time = t;
	            this.msg = msg;
	        }
	    }
	}
}
