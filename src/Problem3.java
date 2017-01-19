import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<Character>();
		String current = "";
		String result = "";
		for (int i = 0; i < s.length() - 1 - result.length(); i++) {
			current = String.valueOf(s.charAt(i));
			set.add(s.charAt(i));
			for (int j = i + 1; j < s.length(); j++) {
				if (!set.contains(s.charAt(j))) {
					current = current + s.charAt(j);
					set.add(s.charAt(j));
				} else {
					if (result.length() < current.length()) {
						result = current;
					}
					break;
				}
			}
			set.clear();
		}

		return result.length();
	}

	public int lengthOfLongestSubstring2(String s) {
		final Set<Character> unique = new HashSet<>();
		int max = 0;
		for (int i = 0; i < s.length(); ++i) {
			final char c = s.charAt(i);
			if (!unique.add(c)) {
				for (int j = i - unique.size(); j < i; ++j) {
					if (s.charAt(j) != c) {
						unique.remove(s.charAt(j));
					} else {
						break;
					}
				}
			}
			max = Math.max(max, unique.size());
		}
		return max;
	}
	
	public int lengthOfLongestSubstring3(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		String current = "";
		String result = "";
		for (int i = 0; i < s.length() - 1 - result.length(); i++) {
			current = String.valueOf(s.charAt(i));
			map.put(s.charAt(i), i);
			for (int j = i + 1; j < s.length(); j++) {
				Integer occurence = map.put(s.charAt(j), j);
				if (occurence == null) {
					current = current + s.charAt(j);
				} else {
					if (result.length() < current.length()) {
						result = current;
					}
					i = occurence + 1; 
					break;
				}
			}
			map.clear();
		}

		return result.length();
	}
}
