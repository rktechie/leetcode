import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
1235. Maximum Profit in Job Scheduling
Hard
Topics
Companies
Hint
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.



Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
 */
public class MaximumProfitInJobScheduling {
  int[] memo = new int[50001]; // maximum number of jobs are 50000

  /*
   * SEE NEETCODE EXPLANATION. ITS PERFECT TO UNDERSTAND THIS SOLN - https://www.youtube.com/watch?v=JLoWc3v0SiE&ab_channel=NeetCodeIO
   * Very easy to understand, it just looks long.
   *
   * Approach: Top-Down Dynamic Programming + Binary Search
   * 1. Store the startTime, endTime and profit of each job in jobs
   * 2. Sort the jobs according to their starting time.
   * 3. Iterate over jobs from left to right, where position is the index of the current job. For each job,
   * we must compare two options:
   * a. Skip the current job (earn 0 profit) and move on to consider the job at the index position + 1.
   * b. Schedule the current job (earn profit for the current job) and move on to the next non-conflicting job
   * whose index is nextIndex. nextIndex is determined by using binary search in the startTime array.
   * 4. Return the maximum profit of the two choices and record this profit in the array memo (memoization).
   *
   *
   * If we didnt do binary search to find the next element, with linear search the time complexity will become O(n^2)
   *
   * Time complexity: O(NlogN)
   * Sorting jobs according to their starting time will take O(NlogN)
   * The time complexity for the recursion (with memoization) is equal to the number of times findMaxProfit is
   * called times the average time of findMaxProfit. The number of calls to findMaxProfit is 2*N because each
   * non-memoized call will call findMaxProfit twice.
   * Each memoized call will take O(1) time while for the non-memoized call, we will perform a binary search
   * that takes O(logN) time, hence the time complexity will be O(NlogN+N).
   */
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    List<List<Integer>> jobs = new ArrayList<>();

    // marking all values to -1 so that we can differentiate
    // if we have already calculated the answer or not
    Arrays.fill(memo, -1);

    // storing job's details into one list
    // this will help in sorting the jobs while maintaining the other parameters
    int length = profit.length;
    for (int i = 0; i < length; i++) {
      ArrayList<Integer> currJob = new ArrayList<>();
      currJob.add(startTime[i]);
      currJob.add(endTime[i]);
      currJob.add(profit[i]);
      jobs.add(currJob);
    }

    jobs.sort(Comparator.comparingInt(a -> a.get(0))); // sort by start time

    // binary search will be used on startTime so store it as separate list
    for (int i = 0; i < length; i++) {
      startTime[i] = jobs.get(i).get(0);
    }

    return findMaxProfit(jobs, startTime, length, 0);
  }

  private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
    // Base case: 0 profit if we have already iterated over all the jobs
    if (position == n) {
      return 0;
    }

    // Caching/memoization: return result directly if it's calculated
    if (memo[position] != -1) {
      return memo[position];
    }

    // nextIndex is the index of next non-conflicting job i.e. the job which starts after the end of the current job
    int nextIndex = findNextJob(startTime, jobs.get(position).get(1));

    // find the maximum profit of our two options i.e. max = Max(next element, current element + next element which starts after the end of current element):
    // 1. do no include current job (skipping the current)
    // 2. include the current job
    int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1),
        jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));

    // return maximum profit and also store it for future reference (memoization)
    return memo[position] = maxProfit;
  }

  private int findNextJob(int[] startTime, int lastEndingTime) {
    int start = 0, end = startTime.length - 1, nextIndex = startTime.length;

    while (start <= end) {
      int mid = (start + end) / 2;
      if (startTime[mid] >= lastEndingTime) {
        nextIndex = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return nextIndex;
  }
}
