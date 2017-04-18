/*
 * Problem 8: String to Integer (atoi)
 * 
Implement atoi to convert a string to an integer.


Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */
public class StringToInteger {

	public static void main(String[] args) {
		myAtoi("9223372036854775809");
	}

	static public int myAtoi(String str) {
		str = str.trim();
		int i = 0;
		boolean sign = true;
		
		if(str.length() > 0 && (str.charAt(0) == '+' || str.charAt(0) == '-')) {
			if(str.charAt(0) == '+')
				sign = true;
			else 
				sign = false;
			i++;
		}
		
		long res = 0;
		while(i < str.length() && Character.isDigit(str.charAt(i))) {
			res = res * 10 + str.charAt(i) - 48;
			i++;
			
			// This condition is there because there might be value which is too long for the Long to hold Eg: 9223372036854775809
			if(String.valueOf(res).length() > 11) {
				if(sign) {
					return Integer.MAX_VALUE;
				} else {
					return Integer.MIN_VALUE;
				}
			}
		}
		
		if(!sign) {
			res = -1 * res;
		}
		
		if(res <= Integer.MAX_VALUE && res >= Integer.MIN_VALUE) {
			System.out.println((int)res);
			return (int)res;
		} else if(res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.MIN_VALUE;
		}
	}
}
