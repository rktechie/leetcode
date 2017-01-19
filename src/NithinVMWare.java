/*
 * 
 */
public class NithinVMWare {

    public static void main(String[] args) {
         int x = maxDiff(new int[] { 4,3,2});
         System.out.println("Answer: " + x);

    }

    public static int maxDiff(int a[]) {
        if (a.length <= 1)
            return -1;
        boolean isDescending = true;
        int maxDiff = a[1] - a[0];
        int minElement = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] - minElement > maxDiff)
                maxDiff = a[i] - minElement;
            if (a[i] < minElement)
                minElement = a[i];
        }
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                isDescending = false;
                break;
            }
        }
        if (isDescending)
            return -1;
        return maxDiff;
    }

    static long kSub(int k, int[] nums) {
        // Initialize count array.
        int[] countArray = new int[k];
        countArray[0] = 1;
        int sum = 0;
        // Iterate over the input sequence.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;
            countArray[sum]++;
        }
        // Compute the answer.
        long output = 0;
        for (int i = 0; i < k; i++)
            output += (long) countArray[i] * (countArray[i] - 1) / 2;
        return output;
    }
}
