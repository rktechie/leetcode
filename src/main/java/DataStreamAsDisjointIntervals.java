import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
352. Data Stream as Disjoint Intervals
Hard
Topics
Companies
Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.

Implement the SummaryRanges class:

SummaryRanges() Initializes the object with an empty stream.
void addNum(int value) Adds the integer value to the stream.
int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.


Example 1:

Input
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
Output
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

Explanation
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // return [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]


Constraints:

0 <= value <= 104
At most 3 * 104 calls will be made to addNum and getIntervals.
At most 102 calls will be made to getIntervals.


Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
 */
public class DataStreamAsDisjointIntervals {

  /*
   * Approach 1: Maintain all the intervals in ordered map
   *
   * Instead of storing the values and then building the intervals every time we call getIntervals, we
   * can just store the intervals themselves and update them every time we add a number.
   *
   * In Java, we can maintain a TreeMap in which each entry represents an interval. The key and value are
   * the left and right bounds of an interval. We still want to maintain the intervals in sorted order so
   * that when we add a number, we can easily find the interval a number is close to and perform merges
   * if necessary. getIntervals then returns all the entries in the TreeMap.
   *
   * When we insert a value, there are 3 non-trivial cases:
   * 1. There is an interval with a right bound of value - 1.
   * In this case, we need to merge the this interval and the value, namely change the the interval's right bound into value.
   *
   * 2. There is an interval with a left bound of value + 1.
   * In this case, we need to merge this interval and the value, namely change the interval's left bound into value
   *
   * 3. Both condition 1 and 2 are satisfied.
   * This is the combination of the previous 2 cases. We should make a new interval which "connects" the
   * two intervals and replace them with the new one.
   *
   * There are 2 trivial cases as well:
   * 1. The value is already in the existing intervals.
   * We do nothing.
   *
   * 2. All other cases.
   * We need to insert a new interval [value, value].
   *
   *
   * Here, N is the total number of calls of addNum.
   * Time complexity: O(log(N)) for addNum, O(N) for getIntervals
   * For getIntervals, we iterate all the entries in the TreeMap which is the same as traversing the
   * whole tree, so the time complexity is O(N).
   *
   * Space complexity: O(N). This is just the space to save all the intervals in the TreeMap.
   */
  class SummaryRanges {
    private TreeMap<Integer, Integer> intervals; // <left bound of interval, right bound of interval>

    public SummaryRanges() {
      intervals = new TreeMap<>();
    }

    public void addNum(int value) {
      // smallEntry is the entry with the greatest key (left bound) no larger than value in intervals.
      final Map.Entry<Integer, Integer> smallEntry = intervals.floorEntry(value); // returns the entry this key else for the greatest key less than the specified key
      int left = value, right = value; // These variables will represent the bounds of a new interval to be created.

      if (smallEntry != null) {
        final int previous = smallEntry.getValue();
        if (previous >= value) {
          return;
        }
        if (previous == value - 1) {
          left = smallEntry.getKey();
        }
      }

      // maxEntry is the entry with the smallest key (left bound) larger than value in intervals.
      final Map.Entry<Integer, Integer> maxEntry = intervals.higherEntry(value);
      if (maxEntry != null && maxEntry.getKey() == value + 1) {
        right = maxEntry.getValue();
        intervals.remove(value + 1);
      }
      intervals.put(left, right);
    }

    public int[][] getIntervals() {
      final int[][] answer = new int[intervals.size()][2];
      int ind = 0;
      for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
        answer[ind][0] = entry.getKey();
        answer[ind++][1] = entry.getValue();
      }
      return answer;
    }
  }
}


class DataStreamAsDisjointIntervals2 {

  /*
   * Approach 2: Save all values in an ordered set
   *
   * The reason to use a TreeSet is that we can iterate on the values in it in the increasing order and
   * elements can be added in O(logn)
   *
   *
   * Here, N is the total number of calls of addNum.
   * Time complexity: O(log(N)) for addNum, O(N) for getIntervals.
   * For addNum, we insert a value into the TreeSet which takes O(log(N)) time.
   * For getIntervals, we iterate all the values in the TreeSet which is the same as traversing the whole tree, so the time complexity is O(N).
   *
   * Space complexity: O(N).
   * This is just the space to save all the values in the TreeSet.
   */
  class SummaryRanges {
    private Set<Integer> values;

    public SummaryRanges() {
      values = new TreeSet<>();
    }

    public void addNum(int value) {
      values.add(value);
    }

    public int[][] getIntervals() {
      if (values.isEmpty()) {
        return new int[0][2];
      }
      List<int[]> intervals = new ArrayList<>();
      int left = -1, right = -1;
      for (Integer value : values) {
        if (left < 0) {
          left = right = value;
        } else if (value == right + 1) {
          right = value;
        } else {
          intervals.add(new int[]{left, right});
          left = right = value;
        }
      }
      intervals.add(new int[]{left, right});
      return intervals.toArray(new int[0][]);
    }
  }
}