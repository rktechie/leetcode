/*
 * Problem: Roman to Integer
 * 
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 */

import java.util.HashMap;

public class RomantoInteger {

	public static void main(String[] args) {
		int t = romanToInt("XV");
		System.out.println(t);
	}
	
	// My solution is easier to understand
	public static int romanToInt(String s) {

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		hashMap.put("I", 1);
		hashMap.put("IV", 4);
		hashMap.put("V", 5);
		hashMap.put("IX", 9);
		hashMap.put("X", 10);
		hashMap.put("XL", 40);
		hashMap.put("L", 50);
		hashMap.put("XC", 90);
		hashMap.put("C", 100);
		hashMap.put("CD", 400);
		hashMap.put("D", 500);
		hashMap.put("CM", 900);
		hashMap.put("M", 1000);

		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			String letter = s.charAt(i) + "";
			// If i+1 is less than s.length
			if (i + 1 < s.length()) {
				String nextLetter = s.charAt(i + 1) + ""; 
				
				// This hashmap check is there because if its a small number like IX so we have to take care of that.
				// If we see only the current element, then we would get the result for IX as 11 but this is wrong.
				// So first check if the letter+nextLetter is there in the hashmap.
				
				if (hashMap.get(letter + nextLetter) != null) {
					result = result + hashMap.get(letter + nextLetter);
					i = i + 1; // i+1 because the next letter has already been included in our calculation
				} else {
					result = result + hashMap.get(letter);
				}
			} else {
				result = result + hashMap.get(letter);
			}
		}
		
		return result;
	}
	
	// Solution found on the internet.
	public int romanToInt1(String s) {
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();                
        map.put('M', 1000);    
        map.put('D', 500);    
        map.put('C', 100);    
        map.put('L', 50);
        map.put('X', 10);    
        map.put('V', 5);
        map.put('I',1);
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1)))
            	res -= map.get(s.charAt(i));
            else
            	res += map.get(s.charAt(i));
        }
        return res;
    }
}
