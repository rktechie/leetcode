import java.util.Arrays;
import java.util.List;

/*
 * Problem 119: Pascal's Triangle II
 * 
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
 */

public class PascalsTriangle2 {
    
    /*
     * Same as Pascals Triangle solution. But here we return the prev element.
     */
    public List<Integer> getRow(int rowIndex) {
        Integer[] prev = new Integer[0];

        for (int i = 1; i <= rowIndex + 1; i++) {
            Integer[] row = new Integer[i];
            row[0] = 1;
            row[i - 1] = 1;
            for (int j = 1; j < i - 1; j++) {
                row[j] = prev[j] + prev[j - 1];
            }
            prev = row;
        }

        return Arrays.asList(prev);
    }
    
    // 2nd method : More sensible way. The runtime of this and the above method on LC is the same. But this method uses lesser space.
    public List<Integer> getRow2(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        
        Arrays.fill(row, 1);
        
        for(int i = 0 ; i < rowIndex - 1; i++){
            for(int j = i + 1; j >= 1; j--){
                row[j] = row[j] + row[j - 1];
            }
        }
        
        return Arrays.asList(row);
    }
}
