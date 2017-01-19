
public class Problem58 {

	public static void main(String[] args) {

	}

	public int lengthOfLastWord(String s) {
		if(s.trim().length() == 0)
			return 0;
		String[] allWords = s.split(" ");
		return allWords[allWords.length - 1].length();
	}
}
