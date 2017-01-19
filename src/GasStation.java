/*
 * Problem 134: Gas Station
 *
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique. 
 */

////////////////////////////////////////////////////////////////////////////////////////

/*
 * Solution:
 * 
To solve this problem, we need to understand and use the following 2 facts:
1) if the sum of gas >= the sum of cost, then the circle can be completed.
2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.

Proof of fact 2:

If gas[A] < cost[A], then A can not even reach B. 
So to reach C from A, gas[A] must >= cost[A]. 
Given that A can not reach C, we have gas[A] + gas[B] < cost[A] + cost[B],
and gas[A] >= cost[A],
Therefore, gas[B] < cost[B], i.e., B can not reach C. 

In the following solution, sumRemaining tracks the sum of remaining to the current index. 
If sumRemaining < 0, then every index between old start and current index is bad, and we need to update start to be the current index.
 */
public class GasStation {

    public static void main(String[] args) {

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumRemainingFromStart = 0; // track current remaining
        int total = 0; // track total remaining
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];

            // if sum remaining of (i-1) >= 0, continue
            if (sumRemainingFromStart >= 0) {
                sumRemainingFromStart += remaining;
                // otherwise, reset start index to be current
            } else {
                sumRemainingFromStart = remaining;
                start = i;
            }
            total += remaining;
        }

        if (total >= 0) {
            return start;
        } else {
            return -1;
        }
    }
}
