
public class Problem38 {

	public static void main(String[] args) {

	}

	public String countAndSay(int n) {
		StringBuffer s = new StringBuffer("1");
		StringBuffer res = new StringBuffer();
		while ((--n) != 0) {
			res.setLength(0);
			int size = s.length();
			int cnt = 1;
			char cur = s.charAt(0);
			// Keep calculating the count and then append the count and the number
			for (int i = 1; i < size; i++) {
				if (s.charAt(i) != cur) {
					res.append(cnt);
					res.append(cur);
					cur = s.charAt(i);
					cnt = 1;
				} else
					++cnt;
			}
			res.append(cnt);
			res.append(cur);
			StringBuffer tmp = s;
			s = res;
			res = tmp;
		}
		return s.toString();
	}
}
