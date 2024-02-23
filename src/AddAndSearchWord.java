/*
 * Problem 211: Design Add and Search Words Data Structure
 * 
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
 */

public class AddAndSearchWord {
    public static void main (String args[]) {
        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("a");
//        boolean x = wordDictionary.search("a");
//        System.out.println("Search: " + x);
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
//        x = wordDictionary.search("a");
//        System.out.println("Search: " + x);
//        x = wordDictionary.search(".at");
//        System.out.println("Search: " + x);
        wordDictionary.addWord("bat");
//        x = wordDictionary.search(".at");
//        System.out.println("Search: " + x);
//        x = wordDictionary.search("an.");
//        System.out.println("Search: " + x);
//        x = wordDictionary.search("a.d.");
//        System.out.println("Search: " + x);
        boolean x = wordDictionary.search("b.");
//        System.out.println("Search: " + x);
//        x = wordDictionary.search("a.d");
//        System.out.println("Search: " + x);
//        boolean x = wordDictionary.search(".");
        System.out.println("Search: " + x);
    }
}

class WordDictionary {

    WordNode root = new WordNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        WordNode parent = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (parent.wordNode[index] == null) {
                WordNode temp = new WordNode();
                parent.wordNode[index] = temp;
                parent = temp;
            } else {
                parent = parent.wordNode[index];
            }
        }
        parent.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        WordNode parent = root;
        return searchHelper(word, 0, parent);
    }
    
    public boolean searchHelper(String word, int index, WordNode parent) {
        if (index == word.length())
            return false;
        
        char c = word.charAt(index); 
        if (c == '.') {
            // "." can mean anything. So we have to check all indexes.
            // Keep checking all 26 indexes of an array one by one and then backtrack and go to the next index if the condition is not satisfied. 
            for (int i = 0; i < 26; i++) {
                if (parent.wordNode[i] != null) {
                    if (index == word.length() - 1 && parent.wordNode[i].isWord == true)
                        return true;
                    boolean b = searchHelper(word, index + 1, parent.wordNode[i]);
                    if (b)
                        return true;
                }
            }
        } else {
            int charValue = c - 'a';
            // if the end of the word is reached and the node is not null and its a word then we have found the word.
            // If the end of the word is not reached so we must check the node. If it is not null then we must check it.
            // If it is null that means the word doesn't exist and we must return false
            if (index == word.length() - 1 && parent.wordNode[charValue] != null && parent.wordNode[charValue].isWord == true)
                return true;
            else if (index != word.length() - 1 && parent.wordNode[charValue] != null)
                return searchHelper(word, index + 1, parent.wordNode[charValue]);
            else
                return false;
        }
        
        return false;
    }
}

class WordNode {
    WordNode[] wordNode = new WordNode[26];
    boolean isWord = false; 
}
