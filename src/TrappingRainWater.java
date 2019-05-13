/*
 * Problem 42 : Trapping Rain Water
 * 
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * 1. Use two pointers to scan the entire array until they meet with each other.
	 * 2. Move the point where the wall is lower (because taller wall may leak water on the lower side)
	 * 3. leftMax and rightMax is used to record walls. If the land is lower than the wall, means it can hold water.
	 * 4. if a wall is found taller than the max height wall in the other side, stop there (because taller wall may
	 *    leak water in the lower side). Then move the lower side point.
	 * 
	 * Key points: any bars in the middle of leftMax bar and rightMax bar will not influence
	 * how much water can current position trap
	 * 
	 */
	public int trap(int[] height) {
		int left = 0;
	    int right = height.length-1;
	    int max = 0;
	    int leftmax = Integer.MIN_VALUE; // leftMax represents the highest bar from left
	    int rightmax = Integer.MIN_VALUE; // rightMax represents the highest bar from right
	    while (left <= right) {
	        leftmax = Math.max(leftmax, height[left]);
	        rightmax = Math.max(rightmax, height[right]);
	        if (leftmax < rightmax) { // how much water can current position trap depends on the shorter bar
	            max += (leftmax - height[left]); // leftmax is smaller than rightmax, so the (leftmax-height[a]) water can be stored
	            left++;
	        } else {
	            max += (rightmax - height[right]);
	            right--;
	        }
	    }
	    
	    return max;
    }

}
