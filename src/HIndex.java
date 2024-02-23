import java.util.Arrays;

/*
Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the
given researcher has published at least h papers that have each been cited at least h times.


Example 1:
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
Example 2:

Input: citations = [1,3,1]
Output: 1

Constraints:
n == citations.length
1 <= n <= 5000
0 <= citations[i] <= 1000
 */
public class HIndex {

  /**
   * Approach #1: (Sorting) [Accepted]
   *
   * Think geometrically. Imagine plotting a histogram where the y-axis represents the number of citations for each paper.
   * After sorting in descending order, h-index is the length of the largest square in the histogram.
   * why square? because we need to find at least h papers that have each been cited at least h times.
   *
   * Algorithm:
   * To find such a square length, we first sort the citations array in descending order.
   * After sorting, if citations[i]>i, then papers 0 to i all have at least i+1 citations.
   * Thus, to find h-index, we search for the largest i (let's call it i′) such that
   * citations[i]>i and therefore the h-index is i′+1.
   *
   * It is also possible to find i′ through binary search after sorting. However, since comparison sorting has a
   * time complexity of O(nlogn) which dominates the performance of entire algorithm (linear search is O(n)).
   * Using a binary search (O(logn)) instead of linear search won't change the asymptotic time complexity.
   *
   * Time complexity : O(nlogn). Comparison sorting dominates the time complexity.
   * Space complexity : O(1). Most libraries using heap sort which costs O(1) extra space in the worst case
   */
  public int hIndex(int[] citations) {
    // sorting the citations in ascending order
    Arrays.sort(citations);

    // finding h-index by linear search
    int i = 0;
    while (i < citations.length && citations[citations.length - 1 - i] > i) {
      i++;
    }

    return i; // after the while loop, i = i' + 1
  }

  /**
   * Approach #2 (Counting)
   * Comparison sorting algorithm has a lower bound of O(nlogn). To achieve better performance, we need non-comparison based sorting algorithms.
   *
   * Algorithm
   * From Approach #1, we sort the citations to find the h-index. However, it is well known that comparison sorting
   * algorithms such as heapsort, mergesort and quicksort have a lower bound of O(nlogn).
   * The most commonly used non-comparison sorting is counting sort.
   *
   * However, in our problem, the keys are the citations of each paper which can be much larger than the number of papers n.
   * It seems that we cannot use counting sort. The trick here is the following observation:
   * - Any citation larger than nnn can be replaced by nnn and the h-index will not change after the replacement
   * - The reason is that h-index is upper bounded by total number of papers nnn, i.e. h≤n
   *
   * Time complexity : O(n). There are two steps. The counting part is O(n) since we traverse the citations array once and only once.
   * The second part of finding the hhh-index is also O(n) since we traverse the papers array at most once.
   * Thus, the entire algorithm is O(n)
   *
   * Space complexity : O(n). We use O(n) auxiliary space to store the counts.
   */
  public int hIndex2(int[] citations) {
    int n = citations.length;
    int[] papers = new int[n + 1];

    // counting papers for each citation number
    for (int c: citations)
      papers[Math.min(n, c)]++;

    // finding the h-index
    int k = n;
    for (int s = papers[n]; k > s; s += papers[k])
      k--;

    return k;
  }
}
