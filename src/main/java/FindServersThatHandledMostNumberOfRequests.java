import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.util.Pair;

/*
1606. Find Servers That Handled Most Number of Requests
Hard
Topics
Companies
Hint
You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously. Each server has infinite computational capacity but cannot handle more than one request at a time. The requests are assigned to servers according to a specific algorithm:

The ith (0-indexed) request arrives.
If all servers are busy, the request is dropped (not handled at all).
If the (i % k)th server is available, assign the request to that server.
Otherwise, assign the request to the next available server (wrapping around the list of servers and starting from 0 if necessary). For example, if the ith server is busy, try to assign the request to the (i+1)th server, then the (i+2)th server, and so on.
You are given a strictly increasing array arrival of positive integers, where arrival[i] represents the arrival time of the ith request, and another array load, where load[i] represents the load of the ith request (the time it takes to complete). Your goal is to find the busiest server(s). A server is considered busiest if it handled the most number of requests successfully among all the servers.

Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.



Example 1:


Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
Output: [1]
Explanation:
All of the servers start out available.
The first 3 requests are handled by the first 3 servers in order.
Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.
Example 2:

Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
Output: [0]
Explanation:
The first 3 requests are handled by first 3 servers.
Request 3 comes in. It is handled by server 0 since the server is available.
Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.
Example 3:

Input: k = 3, arrival = [1,2,3], load = [10,12,11]
Output: [0,1,2]
Explanation: Each server handles a single request, so they are all considered the busiest.


Constraints:

1 <= k <= 105
1 <= arrival.length, load.length <= 105
arrival.length == load.length
1 <= arrival[i], load[i] <= 109
arrival is strictly increasing.
 */
public class FindServersThatHandledMostNumberOfRequests {

  /*
   * Approach 2: Two Priority Queues
   *
   * Algorithm:
   * 1. Initialize an empty priority queue free and a sorted container busy to store free servers and busy servers separately.
   * 2. Use an array count of size k to record the workload of each server.
   * 3. Put all k servers to free.
   * 4. Iterate over requests, for each request [start[i], load[i]], do the following steps by order:
   * a. If there are servers that become free before start[i], we remove them from busy and add them to free
   * b. If there is no server in free, abandon this request by repeating step 4.
   * c. Otherwise, perform a binary search on free to find the first server larger than or equal to i. If
   * all servers are smaller than i, choose the smallest one to handle this request. Increment the
   * workload of this server by 1 and repeat step 4.
   * 5. After the iteration stops, collect all servers having the maximum workload.
   *
   * Note: In Java, TreeSet is backed by an implementation of NavigableMap called TreeMap. It is a red-black
   * (balanced) binary search tree(BST). And when you are searching (methods: lower() and higher() are actually searching)
   * over the red-black BST time-complexity is going to be 2 log N. So the time complexity of both methods is O (log N).
   *
   * Time Complexity: O(n⋅logk)
   * - Operations like adding and removing in the priority queue take logarithmic time. Since there
   * may be at most k servers stored in the priority queue, thus each operation takes O(logk) time.
   * - Sorted containers (TreeSet) are implemented using a red-black tree, and operations like inserting, deleting,
   * and performing a binary search on the red-black tree take O(logk) time.
   * - In each step, we perform multiple operations on busy and free.
   *
   * Therefore, the overall time complexity is O(n*logk).
   *
   * Space complexity: O(k)
   * The total number of servers stored in busy and free is nnn, so they take O(k) space.
   */
  public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
    int[] count = new int[k];
    TreeSet<Integer> free = new TreeSet<Integer>();
    PriorityQueue<Pair<Integer, Integer>> busy = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey()); // <time, serverId>

    // All servers are free at the beginning.
    for (int i = 0; i < k; ++i) {
      free.add(i);
    }

    for (int i = 0; i < arrival.length; ++i) {
      int start = arrival[i];

      // Move free servers from 'busy' to 'free'.
      while (!busy.isEmpty() && busy.peek().getKey() <= start) {
        Pair<Integer, Integer> curr = busy.remove();
        int serverId = curr.getValue();
        free.add(serverId);
      }

      // If we have free servers, use ceiling function (which indirectly is modified binary search) to find the target server.
      if (!free.isEmpty()) {
        Integer busyId = free.ceiling(i % k);
        if (busyId == null) {
          busyId = free.first();
        }

        free.remove(busyId);
        busy.add(new Pair<>(start + load[i], busyId));
        count[busyId]++;
      }
    }

    // Find the servers that have the maximum workload.
    int maxJob = Arrays.stream(count).max().getAsInt();
    List<Integer> answer = new ArrayList<>();
    for (int i = 0; i < k; ++i) {
      if (count[i] == maxJob) {
        answer.add(i);
      }
    }

    return answer;
  }

}
