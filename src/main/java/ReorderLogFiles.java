import java.util.Arrays;
import java.util.Comparator;

/*
 * Problem : Reorder Log Files
 * 
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 

Note:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderLogFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Solution: We implement our own comparator
	 * 
	 * 1. guaranteed to have a word following an identifier (allows me to use indexOf ' ' freely).
	 * 
	 * 2. letter logs need to be ordered lexicographically, so we can use built in 
	 * compare function when we know we have two.
	 * 
	 * 3. number logs need to be sorted naturally, so we just say they're all "equal" 
	 * to each other and trust java's built in sort feature to be stable.
	 * 
	 * 4. number logs need to be after letter logs, so once we find out they're different, 
	 * we return one of the other and short-circuit.
	 */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2); // to split the string into 2 parts. the identifier will be the first part, log in the second part.
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            // if both are letter logs
            if (!isDigit1 && !isDigit2) { 
                int cmp = split1[1].compareTo(split2[1]); //compare substring after identifier and space
                if (cmp != 0) 
                	return cmp;
                return split1[0].compareTo(split2[0]); // if substrings are identical i.e. cmp = 0, then compare the identifiers
            }
            // if both are digit logs then return in order received i.e. return 0. Else if one digit and other letter log then return either 1 or -1
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        
        return logs;
    }
}
