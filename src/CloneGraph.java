import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem 133: Clone Graph
 * 
 * 
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:

Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/

 */
public class CloneGraph {

    public static void main(String[] args) {

    }

    /*
     * Didn't understand the problem and the solution.
     */
    
    // Method 1: DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hashMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return cloneGraphHelper(node, hashMap);
    }

    public UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode node,
            HashMap<UndirectedGraphNode, UndirectedGraphNode> hashMap) {
        if (node == null)
            return null;
        if (hashMap.containsKey(node) == true)
            return hashMap.get(node);

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        hashMap.put(node, newNode);
        for (UndirectedGraphNode temp : node.neighbors) {
            newNode.neighbors.add(cloneGraphHelper(temp, hashMap));
        }

        return newNode;
    }

    // Method 2: BFS
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        if (node == null)
            return null;
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));
        while (queue.isEmpty() == false) {
            UndirectedGraphNode cur = queue.poll();
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (map.containsKey(neighbor) == false) {
                    UndirectedGraphNode newnode = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, newnode);
                    queue.offer(neighbor);
                }
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };
}
