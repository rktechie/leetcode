import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/*
 * Problem 635 : Design Log Storage System
 * 
You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.
 */
public class DesignLogStorageSystem {

	/*
	 * Solution 1: Much more optimized than solution 2 because we are using treemap for range queries.
	 * 
	 * Treemap main advantage is that it allows to store the key-value mappings in a sorted order.
	 * 
	 * Given the granularity we can set a lower bound and upper bound of
	 * timestamp so we could do a range query using TreeMap.
	 * 
	 * To get the lower bound we append a suffix of "2000:01:01:00:00:00" to the
	 * prefix of s and to get the upper bound we append a suffix of
	 * "2017:12:31:23:59:59" to the prefix of e.
	 * 
	 * **Performance Analysis** 
	 * The TreeMap is maintained internally as a Red-Black(balanced) tree. 
	 * Thus, the put method takes O(log(n)) time to insert a new entry into the given set
	 * of logs. Here, n refers to the number of entries currently present in
	 * the given set of logs.
	 * 
	 * The retrieve method uses subMap(key, true, key, true) which is O(1)
	 * Time complexity of iterating a subtree is O(log n + k), where n is the number 
	 * of elements in the whole map, and k is the number of elements in the sub-map.
	 */
	class LogSystem {
		private TreeMap<String, List<Integer>> map; // Key: timestamp, Value: id
		private Map<String, Integer> indexes;
		private String min = "2000:01:01:00:00:00", max = "2017:12:31:23:59:59";

		public LogSystem() {
			map = new TreeMap<>();
			indexes = new HashMap<>();
			indexes.put("Year", 4);
			indexes.put("Month", 7);
			indexes.put("Day", 10);
			indexes.put("Hour", 13);
			indexes.put("Minute", 16);
			indexes.put("Second", 19);
		}

		public void put(int id, String timestamp) {
			if (!map.containsKey(timestamp)) {
				map.put(timestamp, new ArrayList<>());
			}
			map.get(timestamp).add(id);
		}

		public List<Integer> retrieve(String s, String e, String gra) {
			List<Integer> result = new ArrayList<>();

			Map<String, List<Integer>> subMap = map.subMap(
					s.substring(0, indexes.get(gra)) + min.substring(indexes.get(gra)), true,
					e.substring(0, indexes.get(gra)) + max.substring(indexes.get(gra)), true);

			return subMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
		}
	}

	/*
	 * Solution 2:
	 * This is similar to a full table db scan and hence expensive
	 */
	public class LogSystem2 {

		List<String[]> timestamps = new LinkedList<>();
		List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
		int[] indices = new int[] { 4, 7, 10, 13, 16, 19 };

		public void put(int id, String timestamp) {
			timestamps.add(new String[] { Integer.toString(id), timestamp });
		}

		public List<Integer> retrieve(String s, String e, String gra) {
			List<Integer> res = new LinkedList<>();
			int idx = indices[units.indexOf(gra)];
			for (String[] timestamp : timestamps) {
				if (timestamp[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0
						&& timestamp[1].substring(0, idx).compareTo(e.substring(0, idx)) <= 0)
					res.add(Integer.parseInt(timestamp[0]));
			}
			return res;
		}
	}
}
