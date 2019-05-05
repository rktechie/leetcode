import java.util.HashMap;
import java.util.Map;

/*
 * Problem 76: Minimum Window Substring
 * 
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * NOTE: (VERY IMPORTANT) THIS IS A TEMPLATE TO SOLVE A LOT OF SUBSTRING PROBLEMS
	 * For most substring problems, we are given a string and need to find a substring of it which satisfy some restrictions.
	 * A general way is to use a hashmap assisted with two pointers. 
	 * 
	 * 
	 * 1. Use two pointers: start and end to represent a window.
	 * 2. Move end to find a valid window.
	 * 3. When a valid window is found, move start to find a smaller window.
	 * 
	 * To check if a window is valid, we use a map to store (char, count) for chars in t. 
	 * And use counter for the number of chars of t to be found in s. 
	 * The key part is map[c1]--;. We decrease count for each char in s. 
	 * If it does not exist in t, the count will be negative.
	 */
	public String minWindow(String s, String t) {
		Map<Character, Integer> map = new HashMap<>(128);
	    for (char c : s.toCharArray()) 
	    	map.put(c, 0);

	    for (char c : t.toCharArray()) {
	    	if (!map.containsKey(c)) // if a char in t is not found in s, then return empty string
	    		return "";
	    	map.compute(c, (k, v) -> (v + 1)); // java 8 way of doing if(map.containsKey(c)) { map.put(c,map.get(c)+1); }
	    }

	    int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
	    
	    // Move end to find a valid window.
	    while (end < s.length()) {
	    	final char c1 = s.charAt(end);
	    	if (map.get(c1) > 0)  // If char in s exists in t, decrease counter
	    		counter--;
	    	map.compute(c1, (key, val) -> val - 1); // Decrease the val. If char does not exist in t, m[c1] will be negative.
	      
	    	end++;
	    	
	    	// When we found a valid window, move start to find *smaller* window.
	    	while (counter == 0) { // it means we have all characters between start and end
	    		if (minLen > end - start) {
	    			minLen = end - start;
	    			minStart = start;
	    		}

	    		final char startChar = s.charAt(start);
	    		map.compute(startChar, (key, val) -> val + 1);
	    		if (map.get(startChar) > 0) // When char exists in t, increase counter. 
	    			counter++;
	        
	    		start++;
	    	}
	    }

	    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
	  }
}
