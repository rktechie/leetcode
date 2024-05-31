/*
 * Problem : Friend Circles
 * 
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1. 
 */
public class FriendCircles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution 1: DFS
	 * Time complexity - O(n^2) The complete matrix of size n^2 is traversed
	 * Space complexity - O(n) visited array of size n is used.
	 */
	public int findCircleNum(int[][] M) {
		boolean[] visited = new boolean[M.length]; // visited[i] means if ith person is visited in this algorithm
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (!visited[i]) {
				dfs(M, visited, i);
				count++;
			}
		}
		return count;
	}

	public void dfs(int[][] M, boolean[] visited, int person) {
		for (int other = 0; other < M.length; other++) {
			if (M[person][other] == 1 && !visited[other]) {
				visited[other] = true; // We found an unvisited person in the current friend cycle
				dfs(M, visited, other); // Start DFS on this new found person
			}
		}
	}

	/*
	 * Solution 2: This is a typical Union Find problem
	 * https://leetcode.com/problems/friend-circles/discuss/101336/Java-solution-Union-Find
	 */
	public int findCircleNum2(int[][] M) {
		int m = M.length, cnt = 0;
		int[] root = new int[m];
		for (int i = 0; i < m; i++)
			root[i] = i;
		for (int i = 0; i < m; i++)
			for (int j = i + 1; j < m; j++)
				if (M[i][j] == 1)
					unionFind(root, i, j);

		for (int i = 0; i < m; i++)
			if (i == root[i])
				cnt++;
		return cnt;
	}

	void unionFind(int[] root, int v1, int v2) {
		while (root[v1] != v1)
			v1 = root[v1]; // find v1's root
		while (root[v2] != v2)
			v2 = root[v2]; // find v2's root
		if (root[v1] != root[v2])
			root[v2] = v1; // unite the 2 subtrees
	}
}
