import java.util.ArrayList;
import java.util.List;

public class SalesforcePermutation {

	public static void main(String[] args) {
		String input = "abc";
		List<String> result = getPerms(input);
		System.out.println("Permutations for the input are:" );
		for (String x : result) {
			System.out.println(x);
		}
	}

	/**
	 * Method to generate permutations for the given input.
	 * @param input is the string for which the sequence of permutations has to be generated.
	 * @return the list of permutations.
	 */
	public static List<String> getPerms(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null || input.length() == 0) {
			return result;
		}
		getPermsHelper("", input, result);

		return result;
	}

	 /**
	  * Recursive helper method to generate permutations for the input string.
	  * The base case is when the input has only one character and therefore no more permutations 
	  * can be generated for a single character.
	  * 
	  * @param current is the string containing the current permutation.
	  * @param input is the string with the remaining characters which has to be permuted.
	  * @param result which contains a list of permutations of a string.
	  */
	private static void getPermsHelper(String current, String input, List<String> result) {
		if (input.length() == 1) {
			result.add(current + input);
		} else {
			for (int i = 0; i < input.length(); i++) {
				getPermsHelper(current + input.charAt(i), input.substring(0, i) + input.substring(i + 1), result);
			}
		}
	}
}
