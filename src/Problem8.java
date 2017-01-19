public class Problem8 {

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
