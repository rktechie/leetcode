/*
 * Problem 71: Simplify Path
 * 
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */
import java.util.Stack;

public class SimplifyPath {

	public static void main(String[] args) {
		String t = simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///");
		System.out.println(t);
	}

	public static String simplifyPath(String path) {
		Stack<String> stack = new Stack<String>();
		String[] pathSplit = path.split("/");
		StringBuilder builder = new StringBuilder();
		
		if (pathSplit.length == 0)
			return "/";
		for (int i = 0; i < pathSplit.length; i++) {
			if (pathSplit[i].equals("..")) {
				if (stack.isEmpty())
					continue;
				else
					stack.pop();
			} else if (pathSplit[i].equals(".") || pathSplit[i].equals("")) {
				continue;
			} else {
				stack.push(pathSplit[i]);
			}
		}
		
		if (stack.isEmpty()) 
			return "/";
		while (!stack.isEmpty()) {
			builder.insert(0, "/" + stack.pop());
		}
		
		return builder.toString();
	}
}
