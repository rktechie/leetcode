import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem 403 : Frog Jump
 * 
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
 */
public class FrogJump {
	
	/*
	 * Solution: DP
	 * 
	 * we make use of a hashmap map which contains key:value pairs 
	 * such that key refers to the position at which a stone is present and 
	 * value is a set containing the jumpsize which can lead to the current stone position. 
	 * 
	 * We start by making a hashmap whose keys are all the positions at which a 
	 * stone is present and the values are all empty except position 0 whose value contains 0. 
	 * 
	 * Then, we start traversing the elements(positions) of the given stone 
	 * array in sequential order. 
	 * 
	 * For the currentPosition, for every possible jumpsize in the 
	 * value set, we check if currentPosition + newjumpsize exists in the map, 
	 * where newjumpsize can be either jumpsize−1, jumpsize, jumpsize+1. 
	 * 
	 * If so, we append the corresponding value set with newjumpsize. 
	 * 
	 * We continue in the same manner. If at the end, the value set 
	 * corresponding to the last position is non-empty, we conclude that 
	 * reaching the end is possible, otherwise, it isn't.
	 */
	public boolean canCross(int[] stones) {
        HashMap<Integer, Set <Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}
