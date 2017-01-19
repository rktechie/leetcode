/*
 * Problem 231: Power of Two
 * 
Given an integer, write a function to determine if it is a power of two. 
 */
public class Problem231 {

    // Solution 1: Using the log property
    // if n has to be a power of 2 then n = 2^x
    // this means log n to the base 2 is equal to x.
    public boolean isPowerOfTwo(int n) {
        double value = Math.log10(n) / Math.log10(2);
        return value % 1 == 0; // this is to check if the result is an int or not. If its not an int then n is not a power of 2.
    }

    // Solution 2: Power of 2 means only one bit of n is '1', so use the trick
    // n&(n-1)==0 to judge whether that is the case.
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0)
            return false;
        return (n & (n - 1)) == 0;
    }
}
