/*
 * Problem 208: Implement Trie (Prefix Tree)
 * 
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

Your Trie object will be instantiated and called as such:
Trie trie = new Trie();
trie.insert("somestring");
trie.search("key");
 */

public class Problem208 {

}

class TrieNode {

    TrieNode[] nodes;
    boolean isEnd;

    public TrieNode() {
        nodes = new TrieNode[26];
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode parent = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (parent.nodes[index] == null) {
                TrieNode temp = new TrieNode();
                parent.nodes[index] = temp;
                parent = temp;
            } else {
                parent = parent.nodes[index];
            }
        }
        parent.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode parent = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (parent.nodes[index] == null) {
                return false;
            } else {
                parent = parent.nodes[index];
            }
        }
        if (parent.isEnd == true)
            return true;
        else
            return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode parent = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (parent.nodes[index] == null) {
                return false;
            } else {
                parent = parent.nodes[index];
            }
        }
        return true;
    }
}
