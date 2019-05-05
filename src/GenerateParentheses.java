import java.util.ArrayList;
import java.util.List;

/*
 * Problem 22: Generate Parentheses
 * 
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class GenerateParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Solution 1: Backtracking
	 * 
	 * The idea here is to only add '(' and ')' that we know will guarantee us a solution 
	 * (instead of adding 1 too many close). 
	 * 
	 * Once we add a '(' we will then discard it and try a ')' which can only close a valid '('. 
	 * Each of these steps are recursively called.
	 */
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
	}
	    
    public void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
    
    
    /*
     * Solution 2: Same as above but using Stringbuilder and therefore avoiding expensive string concatenations.
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        helper(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void helper(List<String> res, StringBuilder sb, int open, int close, int n) {
       if(open == n && close == n) {
           res.add(sb.toString());
           return;
       }
       
       if(open < n) {
           sb.append("(");
           helper(res, sb, open+1, close, n);
           sb.setLength(sb.length()-1);
       } 
       if(close < open) {
           sb.append(")");
           helper(res, sb, open, close+1, n);
           sb.setLength(sb.length()-1);
       }
    }

}
