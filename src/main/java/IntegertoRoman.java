/*
 * Problem 12: Integer to Roman
 * 
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
 */

import java.util.HashMap;

/*
 *  I = 1
 *  V = 5
 *  X = 10
 *  L = 50
 *  C = 100
 *  D = 500
 *  M = 1000
 */

public class IntegertoRoman {

	public static void main(String[] args) {
		String t = intToRoman(650);
		System.out.println(t);
	}

	public static String intToRoman(int num) {
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };  
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" }; 
        StringBuilder result = new StringBuilder();
        for(int i=0; i<values.length;i++)
        {
            while(num>=values[i])
            {
                num-=values[i];
                result.append(numerals[i]);
            }
        }
        return result.toString();
	}

}
