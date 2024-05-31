import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
588. Design In-Memory File System
Hard
Topics
Companies
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.


Example 1:


Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"


Constraints:

1 <= path.length, filePath.length <= 100
path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
1 <= content.length <= 50
At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */
public class DesignInMemoryFileSystem {

  /**
   * Approach #1 Using separate Directory and File List
   *
   * We start our discussion by looking at the directory structure used.
   * The root directory acts as the base of the directory structure.
   * Each directory contains two hashmaps namely dirs and files.
   *
   *
   * The time complexity of executing an ls command is O(m+n+klog(k)).
   * Here, m refers to the length of the input string. We need to sort these names giving a factor of klog(k)
   * n refers to the depth of the last directory level in the given input for ls.
   * This factor is taken because we need to enter n levels of the tree structure to reach the last level.
   * k refers to the number of entries(files+subdirectories) in the last level directory(in the current input).
   *
   * The time complexity of executing an mkdir command is O(m+n)
   * The time complexity of both addContentToFile and readContentFromFile is O(m+n)
   *
   * The advantage of this scheme of maintaining the directory structure is that it is expandable to include even more commands easily.
   *
   * Note:
   * Instead of hashmap we can also use TreeMap so we dont have to call Collections.sort() everytime "ls" method is called
   * But TreeMap will make all get operations take O(log n) time whereas in hashmap get calls only take O(1) time.
   *
   */
  public static class FileSystem {
    class Dir {
      HashMap<String, Dir> dirs = new HashMap<>(); // all directories in this directory
      HashMap<String, String> files = new HashMap<>(); // map of <file_name, content> in this directory
    }

    Dir root;

    public FileSystem() {
      root = new Dir();
    }

    // If path is a file path, returns a list that only contains this file's name.
    // If path is a directory path, returns the list of file and directory names in this directory.
    public List<String> ls(String path) {
      Dir cur = root;
      List<String> files = new ArrayList<>();

      if (!path.equals("/")) {
        String[] subPath = path.split("/");
        for (int i = 1; i < subPath.length - 1; i++) {
          cur = cur.dirs.get(subPath[i]);
        }
        if (cur.files.containsKey(subPath[subPath.length - 1])) {
          files.add(subPath[subPath.length - 1]);
          return files;
        } else {
          cur = cur.dirs.get(subPath[subPath.length - 1]);
        }
      }

      files.addAll(new ArrayList<>(cur.dirs.keySet()));
      files.addAll(new ArrayList<>(cur.files.keySet()));
      Collections.sort(files);
      return files;
    }

    public void mkdir(String path) {
      Dir cur = root;
      String[] subPath = path.split("/");
      for (int i = 1; i < subPath.length; i++) {
        if (!cur.dirs.containsKey(subPath[i])) {
          cur.dirs.put(subPath[i], new Dir());
        }
        cur = cur.dirs.get(subPath[i]);
      }
    }

    public void addContentToFile(String filePath, String content) {
      Dir cur = root;
      String[] subPath = filePath.split("/");
      for (int i = 1; i < subPath.length - 1; i++) { // we stop before subPath.length - 1 because the last element is the file name which we use later
        cur = cur.dirs.get(subPath[i]);
      }

      String existingContent = cur.files.getOrDefault(subPath[subPath.length - 1], "");
      cur.files.put(subPath[subPath.length - 1], existingContent + content);
    }

    public String readContentFromFile(String filePath) {
      Dir cur = root;
      String[] subPath = filePath.split("/");
      for (int i = 1; i < subPath.length - 1; i++) { // we stop before subPath.length - 1 because the last element is the file name which we use later
        cur = cur.dirs.get(subPath[i]);
      }

      return cur.files.get(subPath[subPath.length - 1]);
    }
  }

}
