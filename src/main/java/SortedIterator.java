import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
Facebook Interview: Implement a sorted java iterator that returns a sorted list over k sorted lists.

class SortedIterator implements Iterator<Integer> {
	public SortedIterator(List<List<Integer>> lists) {}
	public boolean hasNext() {}
	public Integer next() {}
}
Example:

Input:
[[1, 4, 5, 8, 9],
 [3, 4, 4, 6],
 [0, 2, 8]]
Output:  0, 1, 2, 3, 4, 4, 4, 5, 6, 8, 8, 9
 */
public class SortedIterator {

  private PriorityQueue<Pair> heap;

  SortedIterator(List<List<Integer>> lists) {
    this.heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
    populateHeap(lists);
  }

  private void populateHeap(List<List<Integer>> lists) {
    lists.forEach(list-> heap.add(new Pair(list.get(0), list.iterator())));
  }

  public Integer next() {
    if(!hasNext())
      throw new RuntimeException("No more elements");
    Pair min = heap.poll();
    if(min.iterator.hasNext())
      heap.add(new Pair(min.iterator.next(), min.iterator));
    return min.value;
  }

  public boolean hasNext() {
    return !heap.isEmpty();
  }

  class Pair {
    int value;
    Iterator<Integer> iterator;

    Pair(int value, Iterator<Integer> iterator) {
      this.value = value;
      this.iterator = iterator;
    }
  }
}
