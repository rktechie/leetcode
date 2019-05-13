import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * Problem : Reconstruct Itinerary
 * 
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
             
             
Algorithm/Solution: Hierholzer’s Algorithm

First keep going forward until you get stuck. That's a good main path already. 
Remaining tickets form cycles which are found on the way back and get merged into that main path. 
By writing down the path backwards when retreating from recursion, 
merging the cycles into the main path is easy - the end part of the path has already been written, 
the start part of the path hasn't been written yet, so just write down the cycle now and 
then keep backwards-writing the path.

Example:

enter image description here

From JFK we first visit JFK -> A -> C -> D -> A. 
There we're stuck, so we write down A as the end of the route and retreat back to D. 
There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. 
Then we're stuck again, retreat and write down the airports while doing so: 
Write down D before the already written A, then JFK before the D, etc. 
When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A. 
Then we retreat further along the original path, prepending C, A and 
finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.


 */
public class ReconstructItinerary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	Map<String, PriorityQueue<String>> flights = new HashMap<>();
	List<String> route = new LinkedList<>();

	/*
	 * Solution 1: (Hierholzer’s Algorithm) Just Eulerian path. Greedy DFS, building the route backwards when retreating.
	 * 
	 * So, the algorithm is to find the end node first and delete the path to this node(backtrack), 
	 * meanwhile using PriorityQueue to guarantee lexical order.
	 * 
	 * Lets see how this algo ensures we dont get a deadend node in middle of the route:
	 * Starting at the first node, we can only get stuck at the ending point,
	 * since every node except for the first and the last node has even number of edges, 
	 * when we enter a node we can always get out. 
	 * 
	 * Now we are at the destination and if all edges are visited, we are done, 
	 * and the dfs returns to the very first state. 
	 * 
	 * Otherwise we need to "insert" the unvisited loop into corresponding position, 
	 * and in the dfs method, it returns to the node with extra edges, 
	 * starts another recursion and adds the result before the next path. 
	 * 
	 * This process continues until all edges are visited.
	 * 
	 * Basically, a dead end node will be having odd number of edges and it wont be entered into the map in the first place.
	 * 
	 */
	public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
        	flights.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
	    visit("JFK"); // we first add as it is the fixed starting point
	    return route;
    }

	void visit(String departure) {
		PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
        	visit(arrivals.poll());
	    route.add(0, departure); // add to the beginning (0th index) to the route list.
	}

	/*
	 * Solution 2: Iterative version
	 */
	public List<String> findItinerary(String[][] tickets) {
	    Map<String, PriorityQueue<String>> flights = new HashMap<>();
	    for (String[] ticket : tickets)
	        flights.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
	    List<String> route = new LinkedList();
	    Stack<String> stack = new Stack<>();
	    stack.push("JFK");
	    while (!stack.empty()) {
	        while (flights.containsKey(stack.peek()) && !flights.get(stack.peek()).isEmpty())
	            stack.push(flights.get(stack.peek()).poll());
	        route.add(0, stack.pop());
	    }
	    return route;
	}
	
}
