import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

    /*
     * Approach: two-end BFS
     * The idea behind bidirectional search is to run two simultaneous searches—one forward from
     * the initial state and the other backward from the goal—hoping that the two searches meet in
     * the middle. The motivation is that b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth
     *
     * We can considerably cut down the search space of the standard breadth first search algorithm if we
     * launch two simultaneous BFS. One from the beginWord and one from the endWord.
     * We progress one node at a time from both sides and at any point in time if we find a common node
     * in both the searches, we stop the search.
     * This is known as bidirectional BFS and it considerably cuts down on the search space and hence
     * reduces the time and space complexity.
     *
     * Time Complexity:
     * Similar to one directional, bidirectional also takes O(M^2×N) time for finding out all the transformations.
     * But the search time reduces to half, since the two parallel searches meet somewhere in the middle.
     *
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                // Swap two sets
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        if (endSet.contains(target)) {
                            return len + 1;
                        }
                        if (!visited.contains(target) && wordSet.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }

    /*
     * (this soln is TLE now with new examples)
     * This is a BFS approach. Don't be mislead by the word "depth" in the comments.
     * Time Complexity: O(M×N), where M is the length of words and N is the total number of words in the input word list. 
     * Finding out all the transformations takes M iterations for each of the N words. 
     * Also, breadth first search in the worst case might go to each of the N words.
     * 
     * Space Complexity: O(M×N), to store all M transformations for each of the N words, in the all_combo_dict dictionary. 
     * Visited dictionary is of N size. Queue for BFS in worst case would need space for all N words.
     */
    public int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> cur = new LinkedList<String>();

        // if the beginword and endword is the same
        if (beginWord.compareTo(endWord) == 0)
            return 0;

        cur.offer(beginWord); // offer is like add
        int changeLevel = 1; // keep track of the number of changes made
        Set<String> visited = new HashSet<String>();
        
        while (!cur.isEmpty()) {
        	//keep track of all the new words that are created by changing one letter at a time. (For each depth/changeLevel, a new queue is maintained)
            Queue<String> queue = new LinkedList<String>();
            
            /* For every changeLevel/depth, this loop runs for all the words in the cur queue. 
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
