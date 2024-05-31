import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Problem 187: Repeated DNA Sequences
 * 
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

 */

public class RepeatedDNASequences {

    public static void main(String args[]) {
//        findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
    
    /*
     * We create substrings of size 10 and add them in the hashmap if they are not in it.
     * If we come across a substring which is already there in a hashmap, that means it has occurred more than once, so we add it in the result list. 
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() <= 10)
            return result;
        
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String temp = s.substring(i, i + 10);
            if (hashMap.containsKey(temp)) {
                if (!result.contains(temp)) // So we add only unique substring in the result. no use of adding same substring again and again.
                    result.add(temp);
            } else {
                hashMap.put(temp, 1);
            }
        }
        
        return result;
    }
}
