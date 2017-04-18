/*
    You are given a string containing a comma-separated list of
    tokens. Tokens can have zero length, contain internal commas,
    or be surrounded by double quotes ("). Write a function that
    parses the string character-by-character and returns a list
    of tokens, preserving any quoted tokens.

    Notes:
    - All input will be well-formed.
    - Quote characters will never appear internally within tokens.
    - Quote characters should not appear in the final output.

    Examples:
    1) parse('apple,banana') = ['apple', 'banana']
    2) parse('"a",b,"c,d"') = ['a', 'b', 'c,d']
    3) parse(',,') = ['', '', '']
    4) parse('') = ['']
*/

import java.util.ArrayList;
import java.util.List;

public class SalesforceTokens {

	public static void main(String[] args) {
		List<String> tokens = getTokens("");
		for (String token : tokens) {
			System.out.println("Token: " + token);
		}
	}

	public static List<String> getTokens(String input) {
	    List<String> result = new ArrayList<String>();
	    
	    if (input == null) {
	        return result;
	    }
	    if (input.length() == 0) {
	        result.add("");
	        return result;
	    }
	    
	    String previous = "";
	    boolean isDouble = false;
	    for (int i = 0; i < input.length(); i++) {
	        if (!isDouble && input.charAt(i) == ',') {
	            result.add(previous);
	            previous = "";
	        } else if (input.charAt(i) == '"') {
	            if (isDouble == false) {
	            	isDouble = true;
	            } else {
	            	isDouble = false;
	            }
	        } else {
	            previous = previous + input.charAt(i);
	        }
	    }
	    result.add(previous);
	    
	    return result;
	}
}
