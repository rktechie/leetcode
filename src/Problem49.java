import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Problem49 {

	public static void main(String[] args) {
		String[] strs = { "", "" };
		List<List<String>> t = groupAnagrams(strs);
	}

	/*
	 * Algorithm: Sort each word. So if two words are anagrams then after sorting each word will have the same sequence. By this we will get all anagrams and add them to a list.
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		ArrayList<String> tempList = new ArrayList<String>();
		HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
		List<List<String>> result = new ArrayList<List<String>>();

		for (int i = 0; i < strs.length; i++) {
			char[] stringChar = strs[i].toCharArray();
			Arrays.sort(stringChar);
			String str = new String(stringChar);
			if (hashMap.containsKey(str)) {
				tempList = hashMap.get(str);
				tempList.add(strs[i]);
				// Because the output should give the inner list sorted in lexicographical order.
				tempList.sort(new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});
			} else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(strs[i]);
				hashMap.put(str, list);
			}
		}

		Iterator<Map.Entry<String, ArrayList<String>>> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = iterator.next();
			result.add(entry.getValue());
		}

		return result;
	}
}