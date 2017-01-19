import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int median1 = 0;
		int median2 = 0;
		
		if(nums1.length != 0 ){
			if(nums1.length % 2 == 0){
				median1 = nums1[nums1.length/2] + nums1[1+ nums1.length/2];
			} else{
				median1 = nums1[nums1.length];
			}	
		}
		
		if(nums2.length != 0){
			if(nums2.length % 2 == 0){
				median2 = nums2[nums2.length/2] + nums2[1+ nums2.length/2];
			} else{
				median2 = nums2[nums2.length];
			}
		}
		
		return (median1 + median2) / 2;
	}
	
//	public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
//		System.arraycopy(nums1, 0, dest, destPos, length);
//	}

}
