import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
261. Graph Valid Tree
Medium
Topics
Companies
Hint
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.



Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false


Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
 */
public class GraphValidTree {

  // MUST learn both approaches. Good application of Union-Find algo.
  // You should tell Interviewer about both options and then ask which one to code

  /*
   * Approach: Union-Find
   * https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
   *
   * Important Properties learned
   * 1. Tree has n-1 edges
   * 2. Disjoint Set can detect if there is cycle in graph
   * 3. Graph with less than n-1 edges is definitely not connected
   * 4. Graph with more than n-1 edges definitely has cycle
   *
   * If all the conditions are satisfied, then the graph is fully connected, we return true.
   * If any condition is not satisfied or if the graph has cycles, then we return false.
   *
   * We could approach the problem is by considering each connected component to be a set of nodes.
   * When an edge is put between two separate connected components, they are merged into a single connected component.
   *
   * Every edge resulted in a merge operation, if not, then there are cycles in the graph.
   * Each time there was no merge, it was because we were adding an edge between two nodes that were already connected via a path.
   * This means there is now an additional path between them—which is the definition of a cycle.
   * Therefore, as soon as this happens, we can terminate the algorithm and return false.
   *
   * Union find represents each of the sets as a directed tree, with the edges pointing towards the root node.
   * Union find is a data structure with 3 methods; makeset(A), find(A) and union(A, B)
   * 1. The makeset(A) method is the simplest. It creates a new size-1 set containing just element A.
   * 2. The find(A) method is used to check whether or not 2 elements are in the same connected component.
   *  - It starts at A, and traces parent links up until it gets to A's tree root.
   *  - It then returns the tree root ID. Two nodes that are in the same connected component have the same root.
   *  - The find(...) operation works its way up parent links until it finds a node that points back to itself.
   * 3. The union(A, B) method works by finding the root for A and the root for B, using the find(...) operation.
   * It then sets Bs parent to be A, thus combining the two trees into one.
   *
   * This algorithm might not seem very efficient, after all, the find(...) operation could be O(n) in the worst case.
   * However, there are two straightforward optimizations we can apply that bring the amortized time close to O(1)
   * for both union(...) and find(...).
   * 1. Tracking the sizes of each set; this helps to ensure the tree depth is minimised, as we can ensure
   * the smaller set is attached onto the larger set, and not the other way around. The modifications for this
   * are in the union(...) method.
   * 2. When doing a find(...), keeping track of all the nodes along the path so that afterwards we can
   * make each point directly at the root, so that next time any of those nodes are searched for, it is O(1).
   * The modifications for this are all in the find(...) method.
   *
   * Time complexity: O(N).
   * find(...) amortizes to O(1) as we make optimizations in the pointer
   * The union(...) method itself has no loops or recursion, so the entire cost of calling it is dependent
   * on the cost of the find(...) method that it calls.
   *
   * Space Complexity : O(N).
   * The UnionFind data structure requires O(N) space to store the arrays it uses.
   *
   * Approach 2 had a lot of overhead in needing to create an adjacency list with the edges before it could
   * even begin the depth-first search. This is all treated as a constant, as it ultimately had the same time
   * complexity as the depth-first search itself.
   * Approach 1 doesn't need to change the input format, it can just get straight to determining whether or not there is a cycle.
   */

  class UnionFind {

    private int[] parent;
    private int[] size; // We use this to keep track of the size of each set.

    // For efficiency, we aren't using makeset, but instead initialising
    // all the sets at the same time in the constructor.
    public UnionFind(int n) {
      parent = new int[n];
      size = new int[n];
      for (int node = 0; node < n; node++) {
        parent[node] = node;
        size[node] = 1;
      }
    }

    // The find method, with path compression. There are ways of implementing
    // this elegantly with recursion, but the iterative version is easier for
    // most people to understand!
    public int find(int A) {
      // Step 1: Find the root.
      int root = A;
      while (parent[root] != root) {
        root = parent[root];
      }
      // Step 2: Do a second traversal, this time setting each node to point
      // directly at A as we go.
      while (A != root) {
        int oldRoot = parent[A];
        parent[A] = root;
        A = oldRoot;
      }
      return root;
    }

    // The union method, with optimization union by size. It returns True if a
    // merge happened, False if otherwise.
    public boolean union(int A, int B) {
      // Find the roots for A and B.
      int rootA = find(A);
      int rootB = find(B);
      // Check if A and B are already in the same set.
      if (rootA == rootB) {
        return false;
      }
      // We want to ensure the larger set remains the root.
      if (size[rootA] < size[rootB]) {
        // Make rootB the overall root.
        parent[rootA] = rootB;
        // The size of the set rooted at B is the sum of the 2.
        size[rootB] += size[rootA];
      } else {
        // Make rootA the overall root.
        parent[rootB] = rootA;
        // The size of the set rooted at A is the sum of the 2.
        size[rootA] += size[rootB];
      }
      return true;
    }
  }

  public boolean validTree(int n, int[][] edges) {
    // Condition 1: The graph must contain n - 1 edges.
    if (edges.length != n - 1) {
      return false;
    }

    // Condition 2: The graph must contain a single connected component.
    // Create a new UnionFind object with n nodes.
    UnionFind unionFind = new UnionFind(n);
    // Add each edge. Check if a merge happened, because if it
    // didn't, there must be a cycle.
    for (int[] edge : edges) {
      int A = edge[0];
      int B = edge[1];
      if (!unionFind.union(A, B)) {
        return false;
      }
    }

    // If we got this far, there's no cycles!
    return true;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  private List<List<Integer>> adjacencyList = new ArrayList<>();
  private Set<Integer> seen = new HashSet<>();

  /*
   * Approach 2: DFS
   *
   * Facts: For the graph to be a valid tree, it must have exactly n - 1 edges. Any less, and it can't possibly
   * be fully connected. Any more, and it has to contain cycles.
   * Additionally, if the graph is fully connected and contains exactly n - 1 edges, it can't possibly contain
   * a cycle, and therefore must be a tree!
   *
   * We still need to use a seen set to prevent the algorithm from getting caught in an infinite loop if
   * there are indeed cycles (and to prevent looping on the trivial cycles).
   *
   * Let E be the number of edges, and N be the number of nodes.
   * Time Complexity : O(N).
   * creating an adjacency list has a time complexity of O(N+E) but E is now bounded by N so O(N)
   * "neighbour" loop runs only once for each node. Therefore, in total, the function is called once for each edge.
   *
   * Space Complexity : O(N).
   * Previously, we determined that the adjacency list took O(E+N) space. We now know this is simply O(N)
   */
  public boolean validTree2(int n, int[][] edges) {
    if (edges.length != n - 1) {
      return false;
    }

    // Make the adjacency list.
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) { // this is a undirected graph, so each edge (a,b) means a->b and b->a
      adjacencyList.get(edge[0]).add(edge[1]);
      adjacencyList.get(edge[1]).add(edge[0]);
    }

    // Carry out depth first search.
    dfs(0);

    return seen.size() == n; // Checking whether or not a graph is fully connected is straightforward - we simply checked if all nodes were reachable from a search starting at a single node.
  }

  public void dfs(int node) {
    if (seen.contains(node)) {
      return;
    }
    seen.add(node);
    for (int neighbour : adjacencyList.get(node)) {
      dfs(neighbour);
    }
  }
}
