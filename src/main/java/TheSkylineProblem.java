import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
 * Problem 218: The Skyline Problem
 * 
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class TheSkylineProblem {

	/*
	 * READ ALL 3 SOLUTIONS - THEY ARE ALL BASED ON https://www.youtube.com/watch?v=GSBLe8cKu0s
	 * (must watch the video link to understand all 3 solutions)
	 * Solution 1: Creates a separate class and exactly follows the above video
	 * Solution 2: Negatives the height (still seems strange to me) 
	 *             instead of creating a new class and follows the above video
	 * Solution 3: Same as solution 2 but instead of PriorityQueue they have used TreeMap because
	 *             pq.remove() is O(n) hence make it slower. this is overcome by using a TreeMap.
	 */
	
	/*
	 * Solution 1: https://www.youtube.com/watch?v=GSBLe8cKu0s
	 */
	class Solution1 {
		private class Point implements Comparable<Point>{
	        private int x;
	        private int height;
	        private boolean isStart;
	        
	        public Point(int x, int height, boolean isStart) {
	            this.x = x;
	            this.height = height;
	            this.isStart = isStart;
	        }
	        
	        public int compareTo(Point p) {
	            if (this.x != p.x) {
	                return this.x - p.x;
	            } else {
	                if (this.isStart && p.isStart) {
	                    return p.height - this.height;
	                } 
	                if (!this.isStart && !p.isStart) {
	                    return this.height - p.height;
	                }
	                return this.isStart ? -1 : 1;
	            }
	        }  
	    }
	    
	    public List<int[]> getSkyline(int[][] buildings) {
	        int len = buildings.length;
	        if (len == 0 || buildings[0].length == 0) {
	            return new ArrayList<int[]>();
	        }
	        
	        Point[] points = new Point[len * 2];
	        int index = 0;
	        
	        for (int[] building : buildings) {
	            int start = building[0];
	            int end = building[1];
	            int h = building[2];
	            points[index++] = new Point(start, h, true);
	            points[index++] = new Point(end, h, false);
	            
	        }
	        
	        Arrays.sort(points);
	        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
	        queue.offer(0);
	        List<int[]> res = new ArrayList<>();
	        
	        int prevMaxVal = 0;
	        for (Point point : points) {
	            if (point.isStart) {
	                queue.offer(point.height);
	                int curMaxVal = queue.peek();
	                if (curMaxVal > prevMaxVal) {
	                    res.add(new int[]{point.x,point.height});
	                    prevMaxVal = curMaxVal;
	                }
	                
	            } else {
	                queue.remove(point.height);
	                int curMaxVal = queue.peek();
	                if (curMaxVal < prevMaxVal) {
	                    res.add(new int[]{point.x,curMaxVal});
	                    prevMaxVal = curMaxVal;
	                }
	                
	            }
	        }
	        return res;
	    }
	}
	

    
    
	/*
	 * Solution 2
	 */
	class Solution2 {
		public List<List<Integer>> getSkyline2(int[][] buildings) {
	        List<int[]> heights = new ArrayList<>();
	        for (int[] b : buildings) {
	            // Nice trick to put negative height for the left edge
	            heights.add(new int[] {b[0], -b[2]}); // start of a building, height stored as negative
	            heights.add(new int[] {b[1], b[2]}); // end of a building, height stored as positive
	        }
	        
	        /* Because we use negative heights here we get the correct sorting order*/
	        Collections.sort(heights, 
	        		(a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
	        );
	        
	        // Max heap of heights
	        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a)); // stores all the encountered buildings' heights in descending order
	        pq.offer(0);
	        
	        int prev = 0; // Height of the previous *critical* point
	        List<List<Integer>> result = new ArrayList<>();
	        
	        for (int[] h : heights) {
	            if (h[1] < 0) { // h[1] < 0, that means it meets a new building, so add it to pq
	                pq.offer(-h[1]);
	            } else { // h[1] >=0, that means it has reached the end of the building, so remove it from pq
	                pq.remove(h[1]);
	            }
	            int cur = pq.peek(); // the current max height in all encountered buildings still in the queue
	            
	            // if the max height is different from the previous one, that means a critical point is met, add to result list
	            if (prev != cur) {
	                result.add(Arrays.asList(h[0], cur));
	                prev = cur;
	            }
	        }
	        
	        return result;
	    }
	}
	
	
	/*
	 * Solution 3
	 */
	class Solution3 {
	    public List<int[]> getSkyline(int[][] buildings) {
	        List<int[]> heights = new ArrayList<>();
	        for (int[] b: buildings) {
	            heights.add(new int[]{b[0], - b[2]});
	            heights.add(new int[]{b[1], b[2]});
	        }
	        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
	        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
	        heightMap.put(0,1);
	        int prevHeight = 0;
	        List<int[]> skyLine = new LinkedList<>();
	        for (int[] h: heights) {
	            if (h[1] < 0) {
	                Integer cnt = heightMap.get(-h[1]);
	                cnt = ( cnt == null ) ? 1 : cnt + 1;
	                heightMap.put(-h[1], cnt);
	            } else {
	                Integer cnt = heightMap.get(h[1]);
	                if (cnt == 1) {
	                    heightMap.remove(h[1]);
	                } else {
	                    heightMap.put(h[1], cnt - 1);
	                }
	            }
	            int currHeight = heightMap.firstKey();
	            if (prevHeight != currHeight) {
	                skyLine.add(new int[]{h[0], currHeight});
	                prevHeight = currHeight;
	            }
	        }
	        return skyLine;
	    }
	}
}
