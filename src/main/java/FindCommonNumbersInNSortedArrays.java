import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Problem : Find common numbers in n sorted arrays
 * 
array1 = {1, 3, 4, 5, 7, 9, 10, 15}
array2 = {3, 4, 5, 6, 8 ,9, 10, 15, 18}
array3 = {2, 3, 5, 6, 7, 8, 9, 10, 12}
...

Find all comment elements from all arrays

result = {3, 5, 9, 10}
 */
public class FindCommonNumbersInNSortedArrays {

	public static class Element {
        int[] array;
        int index;
        
        public Element(int[] arr, int i) {
            array = arr;
            index = i;
        }
    }
    
    public static List<Integer> commonNumber(int[][] input) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Element> queue = new PriorityQueue<>(
            (a,b) -> a.array[a.index] - b.array[b.index]
        );
        
        for (int[] row : input) {
            Element e = new Element(row, 0);
            queue.add(e);
        }
        
        int value = -1;
        int count = 0;
        while(!queue.isEmpty()) {
            Element e = queue.poll();
                
            if (e.array[e.index] == value) {
                count++;
                if (count == input.length) {
                    result.add(e.array[e.index]);
                }
            } else {
                value = e.array[e.index];
                count = 1;
            }
            
            e.index = e.index + 1;
            if (e.index >= e.array.length)
                continue;
            queue.add(e);
        }
        
        return result;
    }
}
