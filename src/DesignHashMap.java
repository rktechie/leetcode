/*
 * Problem 706 : Design HashMap
 * 
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 */
public class DesignHashMap {

	/*
	 * Solution: Traditional Hash-Table Implementation - Using Array of LinkedList
	 * 
	 * Time complexity: O(1) average and O(n) worst case - for all get(),put() and remove() methods.
	 * Space complexity: O(n) - where n is the number of entries in HashMap
	 */
	class MyHashMap {
		ListNode[] nodes = new ListNode[10000];

		public int get(int key) {
			int index = getIndex(key);
			ListNode prev = findElement(index, key);
			return prev.next == null ? -1 : prev.next.val;
		}

		public void put(int key, int value) {
			int index = getIndex(key);
			ListNode prev = findElement(index, key);

			if (prev.next == null)
				prev.next = new ListNode(key, value);
			else
				prev.next.val = value;
		}

		public void remove(int key) {
			int index = getIndex(key);
			ListNode prev = findElement(index, key);

			if (prev.next != null)
				prev.next = prev.next.next;
		}

		private int getIndex(int key) {
			return Integer.hashCode(key) % nodes.length;
		}

		private ListNode findElement(int index, int key) {
			if (nodes[index] == null) // there is no element at the index
				return nodes[index] = new ListNode(-1, -1);

			// If an element is found, then multiples keys can have the same hash code. \
			// So, we while loop through all the elements to find the element with the key we are looking for.
			ListNode prev = nodes[index];

			while (prev.next != null && prev.next.key != key) {
				prev = prev.next;
			}
			return prev;
		}

		private class ListNode {
			int key, val;
			ListNode next;

			ListNode(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}
	}
}
