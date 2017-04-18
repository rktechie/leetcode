/*
 * Problem 29: Divide Two Integers
 * 
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {

	}
	
	// This is correct BUT this causes TLE
	public int divide(int dividend, int divisor) {
		long quotient = 0;
		boolean b = false;
		
		if (dividend < 0 || divisor < 0) {
			b = true;
			dividend = dividend < 0 ? -1 * dividend : dividend;
			divisor = divisor < 0 ? -1 * divisor : divisor;	
		}
		while (dividend > 0) {
			dividend = dividend - divisor;
			quotient++;
		}
		if (b) {
			quotient = -1 * quotient;
		}
		if (quotient > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return (int) quotient;
		}
	}
	
	// Didnt understand this solution
	//Solution from the internet : the number can be thought of as an*2n+an−1*2n−1+⋯+a1∗2+a0, with ai∈{0,1}
	/* 1. Keep  multiply 2 (<<1) to the divisor, until it is greater than the dividend. Store the times of shift operation.
	   2. if dividend > divisor, then dividend = dividend - divisor/2(>>1). Until dividend< original divisor. Store the   result.
	   3. Output the result.
	 */
    public int divide1(int dividend, int divisor) {
        boolean flag = dividend < 0 ^ divisor < 0;
        long Dividend = Math.abs((long)dividend);
        long Divisor = Math.abs((long)divisor);
        long res = 0;
        while (Dividend >= Divisor) {
            long c = Divisor;
            for (int i = 0; (c << i) <= Dividend; ++i) {
                Dividend -= (c << i);
                res += (1 << i);
            }
        }
        if (flag == true) res = -res;
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)res;
    }
}
