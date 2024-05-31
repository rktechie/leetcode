import java.util.ArrayList;
import java.util.List;

/*
 * Problem : Word Search 2
 * 
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */
public class WordSearch2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution: Backtracking with Trie (VERY EASY TO UNDERSTAND)
	 * 
	 * The key of the solution lies on how we find the matches of word from the dictionary. 
	 * Intuitively, one might resort to the hashset data structure. This could work.
	 * However, during the backtracking process, one would encounter more often the need to tell if there exists any word 
	 * that contains certain prefix, rather than if a string exists as a word in the dictionary. 
	 * Because if we know that there does not exist any match of word in the dictionary for a given prefix, 
	 * then we would not need to further explore certain direction. 
	 * And this, would greatly reduce the exploration space, therefore improve the performance of the backtracking algorithm.
	 * 
	 * Algo:
	 * - We build a Trie out of the words in the dictionary, which would be used for the matching process later.
	 * - Starting from each cell, we start the backtracking exploration (i.e. backtracking(cell)), if there exists any word 
	 * in the dictionary that starts with the letter in the cell.
	 * - During the recursive function call backtracking(cell), we explore the neighbor cells (i.e. neighborCell) 
	 * around the current cell for the next recursive call backtracking(neighborCell). 
	 * At each call, we check if the sequence of letters that we traverse so far matches any word in the dictionary, 
	 * with the help of the Trie data structure that we built at the beginning.
	 * - Once we encounter a valid word, then we completely remove it from Trie so that we dont encounter it again.
	 * This is a strategy to remove duplicated. By this we dont need to create a hashset
	 * - We store words in Trie leaf node - One benefit is that one would not need to pass the prefix as the parameter in the 
	 * backtracking() call. And this could speed up a bit the recursive call. Similarly, one does not need to reconstruct 
	 * the matched word from the prefix, if we keep the words in Trie.
	 * 
	 * We remove the need to create a visited[m][n] matrix completely by modifying board[i][j] = '#' directly.
	 * 
	 * FOR TIME AND SPACE COMPLEXITY REFER - https://leetcode.com/problems/word-search-ii/solution/
	 */
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		TrieNode root = buildTrie(words);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, root, res);
			}
		}
		
		return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
		char c = board[i][j];
		if (c == '#' || p.next[c - 'a'] == null)
			return;
		
		p = p.next[c - 'a'];
		if (p.word != null) { // found one
			res.add(p.word);
			p.word = null; // optimization technique : If the word is once found, then remove it from the trie so we never encounter it again, its a way to remove duplicates from traversal
		}

		board[i][j] = '#'; // Remove the need to create a visited[m][n] completely by modifying board[i][j] = '#' directly.
		if (i > 0)
			dfs(board, i - 1, j, p, res);
		if (j > 0)
			dfs(board, i, j - 1, p, res);
		if (i < board.length - 1)
			dfs(board, i + 1, j, p, res);
		if (j < board[0].length - 1)
			dfs(board, i, j + 1, p, res);
		board[i][j] = c; // restore the original value i.e. marking it as unvisited as the backtracking is complete for this iteration
	}

	public TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String w : words) {
			TrieNode p = root;
			for (char c : w.toCharArray()) {
				int i = c - 'a';
				if (p.next[i] == null)
					p.next[i] = new TrieNode();
				p = p.next[i];
			}
			p.word = w;
		}
		
		return root;
	}

	class TrieNode {
		TrieNode[] next = new TrieNode[26];
		String word; // store the complete word at the last node as an optimization technique in this situation
	}
}
