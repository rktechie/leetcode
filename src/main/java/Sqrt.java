/*
 * Problem 69: Sqrt(x)
 * 
Implement int sqrt(int x).

Compute and return the square root of x.
 */
public class Sqrt {

	public static void main(String[] args) {

	}
	
	// We use binary search in this.
	// Note: is x/mid > mid, then that means we need to reduce the value of x/mid, which means we need to increase mid.
	// So if we reduce high, then the mid value will also reduce. Therefore we increase the low value in that situation.
	public int mySqrt(int x) {
		if (x < 2)
			return x;
		int low = 1;
		int high = x / 2;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (x / mid == mid)
				return mid;
			if (x / mid > mid)
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		return high; // while loop ended because it is greater than high. So high is closest to the sqrt of x
	}
}
