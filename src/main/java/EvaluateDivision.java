import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Problem : Evaluate Division
 * 
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution: DFS approach
	 * Imagine a/b = k as a link between node a and b, the weight from a to b is k, 
	 * the reverse link is 1/k. Thus, it is a directed weighted graph.
	 * 
	 * Query is to find a path between two nodes.
	 * 
	 * Take a / b = 2, b / c = 3, a / c = ? for example,
	 * a --2--> b --3--> c
	 * We simply find a path using DFS from node a to node c and multiply the weights of edges passed, i.e. 2 * 3 = 6.
	 */
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u,v;
        // we are building the graph
        for (int i = 0; i < values.length; i++) {
        	u = equations.get(i).get(0);
        	v = equations.get(i).get(1);
          graph.putIfAbsent(u, new HashMap<>());
          graph.putIfAbsent(v, new HashMap<>());
          graph.get(u).put(v, values[i]);
          graph.get(v).put(u, 1 / values[i]);
        }
        
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)
          result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, graph, new HashSet<>());
        
        return result;
    }

    double dfs(String num, String denom, double r, Map<String, Map<String, Double>> graph, Set<String> visited) { //num is numerator and denom is denominator
        if (!graph.containsKey(num) || !visited.add(num)) // here we check if the key is not in the graph and if it has been visited already  
        	return -1;
        if (num.equals(denom)) 
        	return r;
        
        Map<String, Double> edges = graph.get(num);
        for (String node : edges.keySet()) {
            double result = dfs(node, denom, r * edges.get(node), graph, visited); // we do r*next.get(c) because as we keep traversing the nodes in the graph, we have to include the weight in our calculation
            if (result != -1) 
            	return result;
        }
        
        return -1;
    }
}
