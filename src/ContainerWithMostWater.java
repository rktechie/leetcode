/*
 * Problem 11: Container With Most Water
 * 
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
public class ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Variables i and j define the container under consideration. 
	 * We initialize them to first and last line, meaning the widest container. 
	 * Variable water will keep track of the highest amount of water we managed so far. 
	 * We compute j - i, the width of the current container, and min(height[i], height[j]), 
	 * the water level that this container can support. 
	 * 
	 * Multiply them to get how much water this container can hold, and update water accordingly. 
	 * Next remove the smaller one of the two lines from consideration, Continue until there is nothing left to consider, then return the result.
	 * 
	 * Now, to maximize the area, we need to consider the area between the lines of larger lengths. 
	 * If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is 
	 * limited by the shorter line. So we must move the shorter one because moving the larger one cannot give an 
	 * increase in area.
	 */
	public int maxArea(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int water = 0;
		
		while (i < j) {
			water = Integer.max(water, (j-i) * Integer.min(height[i], height[j]));
			if (height[i] < height[j])
				i++;
			else
				j--;
		}
		
		return water;
	}
}
