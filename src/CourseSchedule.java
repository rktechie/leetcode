import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem 207: Course Schedule
 * 
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.
Hints:

    This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
    Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
    Topological sort could also be done via BFS.



Solution:
https://leetcode.com/problems/course-schedule/discuss/58524/Java-DFS-and-BFS-solution
https://en.wikipedia.org/wiki/Topological_sorting#Algorithms
 */

public class CourseSchedule {
	
	// LOOK AT BOTH THE SOLUTIONS - VERY GOOD FOR LEARNING PERSPECTIVE

	/*
	 * Solution 1: BFS Topological Sort (Kahn's algorithm)
	 * 
	 * First, find a list of "start nodes" which have no incoming edges and insert them into a set S; 
	 * at least one such node must exist in a non-empty acyclic graph.
	 * 
	 * Poll the queue, get a node and now add the edges of the node to the queue if only 
	 * they dont have any nodes pointing to them i.e. indegree = 0.
	 * 
	 * Time Complexity:
	 * If we use adjacency matrix : O(V2)
	 * If we use adjacency list: O(E+V) 
	 */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	ArrayList<Integer>[] graph = new ArrayList[numCourses]; // we can either create a matrix or an adjacency list. In this solution, we are creating an adjacency list.
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>(); // Set of all nodes with no incoming edge
        int count = 0; // the total number of courses which can be completed without any problem
        
        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();
            
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][1]]++; // if its u->v, then increment indegree for v.
            graph[prerequisites[i][0]].add(prerequisites[i][1]); // if its u->v, then here we are adding outward edges for the node u 
        }
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) { // if the node has no incoming edge then add it to the queue
                queue.add(i);
                count++;
            }
        }
        
        while (!queue.isEmpty()) {
            int course = (int) queue.poll(); // remove a node from the queue (these nodes have no incoming edges)
            for (int i = 0; i < graph[course].size(); i++) { // keep removing edges e from the graph for the node
                int pointer = (int) graph[course].get(i);
                indegree[pointer]--;
                if (indegree[pointer] == 0) { // if it has no other incoming edges, then add it to the queue
                    queue.add(pointer);
                    count++;
                }
            }
        }
        
        if (count == numCourses)
            return true;
        else    
            return false; // graph has at least one cycle
    }
    
    /*
     * Solution 2: DFS
     * 
     * The algorithm loops through each node of the graph, in an arbitrary order, 
     * initiating a depth-first search that terminates when it hits any node that 
     * has already been visited since the beginning of the topological sort or 
     * the node has no outgoing edges (i.e. a leaf node)
     * 
     * dp array is used to prevent revisiting the nodes as well as to deal with cycles.
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses]; // to create adjacency list
        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();
            
        boolean[] visited = new boolean[numCourses];
        boolean[] dp = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]); // note: (unlike above solution) here we create list of u's for v for u->v
        }

        for (int i = 0; i < numCourses; i++) { // must check every node. eg.[1,0],[0,1]
            if (!dfs(graph, visited, i, dp))
                return false;
        }
        
        return true;
    }

    private boolean dfs(ArrayList[] graph, boolean[] visited, int course, boolean[] dp){
        if (visited[course])
            return dp[course];
        else
            visited[course] = true;

        for (int i = 0; i < graph[course].size(); i++) {
            if (!dfs(graph, visited, (int)graph[course].get(i), dp)) {
                dp[course] = false;
                return false;
            }
        }
        
        dp[course] = true;
        
        return true;
    }
}
