import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
 * Problem 432: All O`one Data Structure
 * 
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */
public class AllOOneDataStructure {

	/*
	 * Solution : Main idea is to maintain a list of Bucket's, each Bucket contains all keys with the same count.
	 * (the idea is inspired by SkipLists.)
	 *
	 * Bucket here is using doubled-linked list by using LinkedHashSet for storing keys with same counts.
	 *
	 * If we dont use a LinkedHashSet but a HashSet, then the operators are not O(1) because Set is implemented
	 * with a hashtable and thus if M is the number of slots of the hash table then the worst case complexity
	 * of getting first element is O(M).
	 *
	 * Head and tail can ensure both getMaxKey() and getMinKey() be done in O(1).
	 * keyCountMap maintains the count of keys, countBucketMap provides O(1) access to a specific Bucket with given count. 
	 * Deleting and adding a Bucket in the Bucket list cost O(1), so both inc() and dec() take strict O(1) time.
	 */
	static class AllOne {
		// maintain a doubly linked list of Buckets
		private Bucket head;
		private Bucket tail;
		// for accessing a specific Bucket among the Bucket list in O(1) time
		private Map<Integer, Bucket> countBucketMap;
		// keep track of count of keys
		private Map<String, Integer> keyCountMap;

		// each Bucket contains all the keys with the same count
		private class Bucket {
			int count;
			Set<String> keySet;
			Bucket next;
			Bucket prev;

			public Bucket(int cnt) {
				count = cnt;
				keySet = new LinkedHashSet<>(); // so that later in the code when we call iterator.next() method, it will truly be O(1).
			}
		}

		/** Initialize your data structure here. */
		public AllOne() {
			head = new Bucket(Integer.MIN_VALUE); // dummy
			tail = new Bucket(Integer.MAX_VALUE); // dummy
			head.next = tail;
			tail.prev = head;
			countBucketMap = new HashMap<>();
			keyCountMap = new HashMap<>();
		}

		/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
		public void inc(String key) {
			if (keyCountMap.containsKey(key)) {
				changeKey(key, 1);
			} else {
				keyCountMap.put(key, 1);
				if (head.next.count != 1)
					addBucketAfter(new Bucket(1), head);
				head.next.keySet.add(key);
				countBucketMap.put(1, head.next);
			}
		}

		/**
		 * Decrements an existing key by 1. If Key's value is 1, remove it from the data
		 * structure.
		 */
		public void dec(String key) {
			if (keyCountMap.containsKey(key)) {
				int count = keyCountMap.get(key);
				if (count == 1) {
					keyCountMap.remove(key);
					removeKeyFromBucket(countBucketMap.get(count), key);
				} else {
					changeKey(key, -1);
				}
			}
		}

		/** Returns one of the keys with maximal value. */
		public String getMaxKey() {
			return tail.prev == head ? "" : (String) tail.prev.keySet.iterator().next();
		}

		/** Returns one of the keys with Minimal value. */
		public String getMinKey() {
			return head.next == tail ? "" : (String) head.next.keySet.iterator().next();
		}

		// helper function to make change on given key according to offset
		private void changeKey(String key, int offset) {
			int count = keyCountMap.get(key);
			keyCountMap.put(key, count + offset);
			Bucket curBucket = countBucketMap.get(count);
			Bucket newBucket;
			if (countBucketMap.containsKey(count + offset)) {
				// target Bucket already exists
				newBucket = countBucketMap.get(count + offset);
			} else {
				// add new Bucket
				newBucket = new Bucket(count + offset);
				countBucketMap.put(count + offset, newBucket);
				addBucketAfter(newBucket, offset == 1 ? curBucket : curBucket.prev);
			}
			newBucket.keySet.add(key);
			removeKeyFromBucket(curBucket, key);
		}

		private void removeKeyFromBucket(Bucket bucket, String key) {
			bucket.keySet.remove(key);
			if (bucket.keySet.size() == 0) {
				removeBucketFromList(bucket);
				countBucketMap.remove(bucket.count);
			}
		}

		private void removeBucketFromList(Bucket bucket) {
			bucket.prev.next = bucket.next;
			bucket.next.prev = bucket.prev;
			bucket.next = null;
			bucket.prev = null;
		}

		// add newBucket after preBucket
		private void addBucketAfter(Bucket newBucket, Bucket preBucket) {
			newBucket.prev = preBucket;
			newBucket.next = preBucket.next;
			preBucket.next.prev = newBucket;
			preBucket.next = newBucket;
		}
	}
}
