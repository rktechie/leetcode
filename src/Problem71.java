import java.util.Stack;

public class Problem71 {

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
