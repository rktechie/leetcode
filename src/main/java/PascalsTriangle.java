import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem 118: Pascal's Triangle
 * 
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 */




/*
 * Explanation of the Solution: Recursion

from the definition, it is easy to know


 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]

  0 1 2 3 4 


//i start from 0
row[0] = 1
row[last] = 1

row[i] = prev_row[i] + prev_row[i - 1]

 */

public class PascalsTriangle {
    
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Integer[] prev = null;

        for (int i = 1; i <= numRows; i++) {
            Integer[] row = new Integer[i];
            row[0] = 1;
            row[i - 1] = 1;
            for (int j = 1; j < i - 1; j++) {
                row[j] = prev[j] + prev[j - 1];
            }
            result.add(new ArrayList<Integer>(Arrays.asList(row)));
            prev = row;
        }

        return result;
    }
}
