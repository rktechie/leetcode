import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Problem 127: Word Ladder
 * 
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each intermediate word must exist in the word list

For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.

 */

public class WordLadder {

    public static void main(String[] args) {

    }

    // Work this with a paper and pencil to understand this properly. This is a BFS approach. Don't be mislead by the word "depth" in the comments.
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> cur = new LinkedList<String>();

        // if the beginword and endword is the same
        if (beginWord.compareTo(endWord) == 0)
            return 0;

        cur.offer(beginWord); // offer is like add
        int changeLevel = 1; // keep track of the number of changes made
        Set<String> visited = new HashSet<String>();
        
        while (!cur.isEmpty()) {
            Queue<String> queue = new LinkedList<String>(); //keep track of all the new words that are created by changing one letter at a time. (For each depth/changeLevel, a new queue is maintained)
            
            /* For every changeLevel/depth, this loop runs for all the words in a queue. 
             * After this loop ends, cur points to the updated queue, therefore we basically run for all the words in cur.
             */
            while (!cur.isEmpty()) {
                String str = cur.poll();
                char[] word = str.toCharArray();
                /* 
                 * For each letter of the string (i.e. each char in the word), we will replace it from a-z and 
                 * check if it creates a word which is in the word list and has not been visited. We then add it in the queue and the visited set.
                 * Also at the end of the loop, we replace the letter back to the original (i.e. before) to get the original word.
                 * So ultimately, all the words we add in the queue, have only one letter changed from the word array.
                */
                for (int i = 0; i < word.length; ++i) {
                    char before = word[i];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        word[i] = ch;
                        String temp = new String(word);
                        if (endWord.compareTo(temp) == 0)
                            return changeLevel + 1;
                        if (wordList.contains(temp) == true && visited.contains(temp) == false) {
                            queue.offer(temp);
                            visited.add(temp);
                        }
                    }
                    word[i] = before;
                }
            }
            
            cur = queue; // cur now will point to the new words created in the above while loop.
            ++changeLevel;
        }
        return 0;
    }
}
