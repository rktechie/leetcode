import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * Problem : Insert Delete GetRandom O(1)
 * 
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */
public class InsertDeleteGetRandom {

	/*
	Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.
	The idea of GetRandom is to choose a random index and then retrieve an element with that index.
	There are no indexes in the hashmap, and hence to get a true random value, one has first to convert
	hashmap keys in a list, which would take linear time.
	The solution here is to build a list of keys aside and use this list to compute GetRandom in constant time.

	Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.
	Deleting a value at an arbitrary index takes linear time. The solution here is to always delete the last value:
	Swap the element to delete with the last one.
	Pop the last element out.

	For that, one has to compute an index of each element in constant time and hence needs a hashmap
	which stores element -> its index dictionary.

	Both ways converge into the same combination of data structures:
	Hashmap element -> its index.
	Array List of elements.
	 */
	class RandomizedSet {
		ArrayList<Integer> nums;
		HashMap<Integer, Integer> locs;
		Random rand = new Random();

		/** Initialize your data structure here. */
		public RandomizedSet() {
			nums = new ArrayList<Integer>();
			locs = new HashMap<Integer, Integer>();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already
		 * contain the specified element.
		 */
		public boolean insert(int val) {
			boolean contain = locs.containsKey(val);
			if (contain)
				return false;
			locs.put(val, nums.size());
			nums.add(val);
			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the
		 * specified element.
		 * 
		 * If you try to remove an element from the array without shifting the elements and 
		 * updating their indexes in the map, it will cause the removal to cost O(N). 
		 * The idea of swapping the item to be deleted with the last item is simply brilliant - it happens in O(1)
		 */
		public boolean remove(int val) {
			boolean contain = locs.containsKey(val);
			if (!contain)
				return false;
			int loc = locs.get(val);
			if (loc < nums.size() - 1) { // not the last one then swap the last one with this val
				int lastone = nums.get(nums.size() - 1);
				nums.set(loc, lastone);
				locs.put(lastone, loc);
			}
			locs.remove(val);
			nums.remove(nums.size() - 1);
			return true;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			return nums.get(rand.nextInt(nums.size()));
		}
	}
}
