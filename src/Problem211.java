/*
 * Problem 211: Add and Search Word - Data structure design
 * 
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.
You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.


Your WordDictionary object will be instantiated and called as such:
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("word");
    wordDictionary.search("pattern");
 */

public class Problem211 {
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
