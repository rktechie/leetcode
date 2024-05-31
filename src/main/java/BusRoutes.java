import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
815. Bus Routes
Hard
Topics
Companies
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.



Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1




Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 105
All the values of routes[i] are unique.
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
 */
public class BusRoutes {

  /*
   * Approach 1: Breadth-First Search (BFS) with Bus Stops as Nodes
   *
   * This approach will build up the graph using the bus stops as the nodes.
   * To connect the edges in this graph, we need to find the stop we can go from a particular stop.
   * We need to find the shortest distance between two given nodes "source" and "target".
   * Since the edges are unweighted, BFS is more appropriate than Dijkstra's algorithm.
   *
   * In the problem statement, it's given that we are not on any bus initially. Hence, to start from the
   * bus stop source we can board any of the bus that has the source as one of the stops in its route
   *
   * 1. Return 0 if the source and target are the same.
   * 2. Initialize an empty map from an integer to a list of integers adjList to store the edges.
   *    The key is the bus stop and the value is the list of integers denoting the indices of routes that have this stop.
   * 3. Initialize an empty queue q and an unordered set vis to keep track of visited routes.
   * 4. Insert the initial routes into the queue q and mark them visited in vis.
   * 5. Iterate over the queue while it's not empty and do the following:
   * a. Pop the route from the queue.
   * b. Iterate over the stops in the route.
   * c. If the stop is equal to target, return busCount.
   * d. Otherwise, iterate over the routes for this stop in the map adjList.
   * e. Add the unvisited routes to the queue and mark them visited.
   * 6. Return -1 after completing the BFS.
   *
   * Time complexity: O(M^2 * K)
   * To store the routes for each stop we iterate over each route and for each route, we iterate over each stop,
   * hence this step will take O(M*K) time.
   *
   */
  public int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) {
      return 0;
    }

    HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
    // Create a map from the bus stop to all the routes that include this stop.
    for (int r = 0; r < routes.length; r++) {
      for (int stop : routes[r]) {
        // Add all the routes that have this stop.
        ArrayList<Integer> route = adjList.getOrDefault(stop, new ArrayList<>());
        route.add(r);
        adjList.put(stop, route);
      }
    }

    if (!adjList.containsKey(source)) {
      return -1;
    }

    Queue<Integer> q = new LinkedList<>();
    Set<Integer> vis = new HashSet<Integer>(routes.length);
    // Insert all the routes in the queue that have the source stop.
    for (int route : adjList.get(source)) {
      q.add(route);
      vis.add(route);
    }

    int busCount = 1;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int route = q.remove();
        // Iterate over the stops in the current route.
        for (int stop : routes[route]) {
          // Return the current count if the target is found.
          if (stop == target) {
            return busCount;
          }
          // Iterate over the next possible routes from the current stop.
          for (int nextRoute : adjList.get(stop)) {
            if (!vis.contains(nextRoute)) {
              vis.add(nextRoute);
              q.add(nextRoute);
            }
          }
        }
      }
      busCount++;
    }

    return -1;
  }

}
