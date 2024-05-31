import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/*
826. Most Profit Assigning Work
Medium
Topics
Companies
You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
Return the maximum profit we can achieve after assigning the workers to the jobs.



Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
Example 2:

Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
Output: 0


Constraints:

n == difficulty.length
n == profit.length
m == worker.length
1 <= n, m <= 104
1 <= difficulty[i], profit[i], worker[i] <= 105
 */
public class MostProfitAssigningWork {

  /*
   * Approach: Sort and Two pointer
   * 1. zip difficulty and profit as jobs.
   * 2. sort jobs and sort 'worker'.
   * 3. Use 2 pointers. For each worker, find his maximum profit best he can make under his ability.
   *
   * What is the need to sort worker[ ] ???
   * REASON:
   * - If current worker has more capacity than his previous ones, then his minimum bestPay should be equal to,
   * maximum bestPaySoFar of workers with less capacity.
   * - If he's able to do more-tougher-job, but less-tougher-job pays more, then he's automatically entitled to
   * benefits of that less-tougher-job.
   * - Now because he has more ability as compared to previous workers, he can explore more tougher-high-paying-jobs.
   * - Due to this, we need to traverse jobs/pair[] only once.
   *
   * Because we have sorted jobs and worker, we will go through two lists only once.
   * this will be only O(D + W). O(DlogD + WlogW), as we sort jobs.
   */
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    List<int[]> jobs = new ArrayList<>();
    int N = profit.length, res = 0, i = 0, best = 0;

    for (int j = 0; j < N; ++j)
      jobs.add(new int[]{difficulty[j], profit[j]});

    Collections.sort(jobs, (a,b) -> a[0] - b[0]);
    Arrays.sort(worker);

    for (int ability : worker) {
      while (i < N && ability >= jobs.get(i)[0])
        best = Math.max(jobs.get(i++)[1], best);
      res += best;
    }

    return res;
  }

  /*
   * Approach 2: Use a treemap<difficulty, profit>
   * Go through the treemap once, find the max profit best for each difficulty.
   *
   * Time O(DlogD + WlogD)
   *
   */
  public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    map.put(0, 0);
    for (int i = 0; i < difficulty.length; i++) {
      map.put(difficulty[i], Math.max(profit[i], map.getOrDefault(difficulty[i], 0)));
    }
    int best = 0, res = 0;
    for (Integer key : map.keySet()) {
      best = Math.max(map.get(key), best);
      map.put(key, best);
    }
    for (int i = 0; i < worker.length; i++) {
      res += map.floorEntry(worker[i]).getValue();
    }
    return res;
  }
}
