import java.util.LinkedList;
import java.util.Stack;

/*
 * Problem : Asteroid Collision
 * 
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.
Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each other.
Note:

The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000].
 */
public class AsteroidCollision {

	/*
	 * Solution 1: Using stack
	 */
	public int[] asteroidCollision(int[] asteroids) {
		if (asteroids == null) {
			return null;
		} else if (asteroids.length <= 1) {
			return asteroids;
		}
		Stack<Integer> stack = new Stack<>();
		for (int cur : asteroids) {
			if (cur > 0) { // previous one does not matter, no collision forever as greater than 0 means moving right
				stack.push(cur);
			} else {
				while (!stack.isEmpty() && stack.peek() > 0 && -cur > stack.peek()) { // destroy the previous positive one(s)
					stack.pop();
				}
				if (stack.isEmpty() || stack.peek() < 0) { // the current object is stronger
					stack.push(cur);
				} else if (stack.peek() == -cur) { // both the objects are equal in size
					stack.pop();
				}
			}
		}
		return stack.stream().mapToInt(i -> i).toArray();
	}
	
	/*
	 * Solution 2: Using linkedlist to mimic stack
	 */
	public int[] asteroidCollision2(int[] a) {
		LinkedList<Integer> s = new LinkedList<>();
		for (int i : a) {
			if (i > 0) // previous one does not matter, no collision forever as greater than 0 means moving right
				s.add(i);
			else {
				while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
					s.pollLast();
				if (!s.isEmpty() && s.getLast() == -i)
					s.pollLast();
				else if (s.isEmpty() || s.getLast() < 0)
					s.add(i);
			}
		}
		return s.stream().mapToInt(i -> i).toArray();
	}
}
