import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/*
 * arr1 = [1, 2, 3]
 * arr2 = [3, 4, 5]
 * 
 * union = [1, 2, 3, ,4 , 5] //no duplicates
 * intersection = [3]
 */
public class AdobeTest {

	public static void main(String[] args) {
		int[] arr1 = {1, 2, 3};
		int[] arr2 = {3, 4, 5};
		
		List<Integer> unionElements = union(arr1, arr2);
		System.out.println("Union: " + unionElements);
		
		List<Integer> intersectionElements = intersection(arr1, arr2);
		System.out.println("Intersection: " + intersectionElements);
	}
	
	public static List<Integer> union(int[] arr1, int[] arr2) {
		HashSet<Integer> set = new HashSet<>();
		for (int element : arr1)
			set.add(element);
		for (int element : arr2)
			set.add(element);
		
		List<Integer> result = new ArrayList<>();
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		
		return result;
	}
	
	
	
	
	public static List<Integer> union2(int[] arr1, int[] arr2) {
		List<Integer> result = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		
		for (int element : arr1) {
			if (!set.contains(element)) {
				set.add(element);
				result.add(element);
			}
		}
		
		for (int element : arr2) {
			if (!set.contains(element)) {
				result.add(element);
			}
	}
	
			
			
			
	public static List<Integer> intersection(int[] arr1, int[] arr2) {
		// if (arr1 == null || arr2)
		if (arr1.length == 0 || arr2.length == 0)
			return new ArrayList<>();
		
		HashSet<Integer> set = new HashSet<>();
		for (int element : arr1)
			set.add(element);
		
		List<Integer> result = new ArrayList<>();
		for (int element : arr2) {
			if (set.contains(element)) {
				result.add(element);
			}
		}
		
		return result;
	}
	
}
