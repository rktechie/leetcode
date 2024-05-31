import java.util.Stack;

/*
 * Problem 150: Evaluate Reverse Polish Notation
 * 
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 */

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int x = 0, y = 0;
        // Cannot use Character.isDigit because of -ve numbers.
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].equals("+") && !tokens[i].equals("-") && !tokens[i].equals("*") && !tokens[i].equals("/")) {
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                y = stack.pop();
                x = stack.pop();
                if (tokens[i].equals("+"))
                    stack.push(x + y);
                else if (tokens[i].equals("-"))
                    stack.push(x - y);
                else if (tokens[i].equals("*"))
                    stack.push(x * y);
                else if (tokens[i].equals("/")) {
                    int z = y == 0 ? 0 : x / y; 
                    stack.push(z);
                }
            }
        }
        
        return stack.pop();
    }
}
