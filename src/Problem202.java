import java.util.HashSet;

/*
 * Problem 202: Happy Number
 * 
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1
 */

public class Problem202 {

    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        while (true) {
            n = check(n);
            if (n == 1)
                return true;
            if (hashSet.contains(n))
                return false;
            hashSet.add(n);
        }
    }
    
    public static int check (int n) {
        int sum = 0;
        
        do {
            int temp = n % 10;
            sum += temp * temp;
            n = n / 10;
        } while (n > 0);
        
        return sum;
    }
}
