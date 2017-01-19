import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Letter Combinations of a Phone Number:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

public class Problem17 {

	public static void main(String[] args) {
		List<String> t = letterCombinations("");
		for (String x : t)
			System.out.println("list: " + x);
	}

	// Used backtracking.
	public static List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("2", "abc");
		hashMap.put("3", "def");
		hashMap.put("4", "ghi");
		hashMap.put("5", "jkl");
		hashMap.put("6", "mno");
		hashMap.put("7", "pqrs");
		hashMap.put("8", "tuv");
		hashMap.put("9", "wxyz");
		
		if (digits.trim().length() == 0)
			return result;
		letterCombinationsHelper(digits, hashMap, result, "", 0);
		return result;
	}
	
	// call the helper function recursively for each letter of each digit. The letters representing each digit is obtained from the hashmap.
	// "i" is used to keep track of the the digit which has to be expanded. 
	public static void letterCombinationsHelper(String digits, HashMap<String, String> hashMap, List<String> result, String lettersTillNow, int i) {
		if (lettersTillNow.length() == digits.length()) {
			result.add(lettersTillNow);
			return;
		}
		
		String currentDigitLetters = hashMap.get(digits.substring(i, i + 1));
		for (int j = 0; j < currentDigitLetters.length(); j++) {
			letterCombinationsHelper(digits, hashMap, result, lettersTillNow + currentDigitLetters.charAt(j), i + 1);
		}
	}
	
}
