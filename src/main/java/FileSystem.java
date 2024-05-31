import java.util.HashMap;
import java.util.Map;

/*
1166. Design File System
Medium
Topics
Companies
Hint
You are asked to design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:

bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.


Example 1:

Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.


Constraints:

2 <= path.length <= 100
1 <= value <= 109
Each path is valid and consists of lowercase English letters and '/'.
At most 104 calls in total will be made to createPath and get.
 */
public class FileSystem {

  // The TrieNode data structure.
  static class TrieNode {
    int val = -1;
    Map<String, TrieNode> map = new HashMap<>();
  }

  TrieNode root;

  // Root node contains the empty string.
  public FileSystem() {
    this.root = new TrieNode();
  }

  public boolean createPath(String path, int value) {
    // Obtain all the components
    String[] components = path.split("/");
    // Start "curr" from the root node.
    TrieNode cur = root;

    // Iterate over all the components.
    for (int i = 1; i < components.length; i++) {
      String currentComponent = components[i];
      // For each component, we check if it exists in the current node's dictionary.
      if (!cur.map.containsKey(currentComponent)) {
        // If it doesn't and it is the last node, add it to the Trie.
        if (i == components.length - 1) {
          cur.map.put(currentComponent, new TrieNode());
        } else {
          return false;
        }
      }
      cur = cur.map.get(currentComponent);
    }

    // Value not equal to -1 means the path already exists in the trie.
    if (cur.val != -1) {
      return false;
    }

    cur.val = value;
    return true;
  }

  public int get(String path) {
    // Obtain all the components
    String[] components = path.split("/");

    // Start "curr" from the root node.
    TrieNode cur = root;

    // Iterate over all the components.
    for (int i = 1; i < components.length; i++) {
      String currentComponent = components[i];
      // For each component, we check if it exists in the current node's dictionary.
      if (!cur.map.containsKey(currentComponent)) {
        return -1;
      }
      cur = cur.map.get(currentComponent);
    }

    return cur.val;
  }

}
