/*
 * Problem 125: Valid Palindrome
 * 
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome. 
 */

public class ValidPalindrome {

    public static void main(String args[]) {
        boolean x = isPalindrome("hih");
        System.out.println(x);
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;

        boolean isP = true;
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if (i < j) {
                if (s.charAt(i) == s.charAt(j)) {
                    continue;
                } else {
                    isP = false;
                    break;
                }
            }
        }

        return isP;
    }
}
