/**
 * @(#)Solution.java, 2016/03/09 Copyright 2016 Yodao, Inc. All rights
 */
package training.Leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of s ize m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * 2016/03/09
 *
 * @author zhaowei
 */
public class MedianOfTwoSortedArrays_4 {
    private double findKth(int[] nums1,int start1, int end1, int[] nums2, int start2, int end2, int kth) {
        int length1 = end1 - start1 + 1;
        int length2 = end2 - start2 + 1;
        if (length1 < length2) {
            return findKth(nums2, start2, end2, nums1, start1, end1, kth);
        }
        if (length2 <= 0) {
            return nums1[start1 + kth - 1];
        }
        if (kth == 1) {
            return nums1[start1] > nums2[start2] ? nums2[start2] : nums1[start1];
        }
        int half2 = kth / 2;
        if (half2 > length2) {
            half2 = length2;
        }
        int half1 = kth - half2;
        if (nums1[start1 + half1 - 1] > nums2[start2 + half2 - 1]) {
            return findKth(nums1, start1, end1, nums2, start2 + half2, end2, kth - half2);
        } else if (nums1[start1 + half1 - 1] < nums2[start2 + half2 - 1]){
            return findKth(nums1, start1 + half1, end1, nums2, start2, end2, kth - half1);
        } else {
            return nums2[start2 + half2 - 1];
        }
    }

    private double findMedianSortedArrays(int[] nums1, int length) {
        if (length % 2 == 0) {
            return (double)(nums1[length/2 - 1] + nums1[length/2 ]) / 2;
        } else {
            return nums1[length / 2];
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 1 && length2 == 1) {
            return (double)(nums1[0] + nums2[0])/2;
        }
        if (length1 == 0 && length2 != 0) {
            return findMedianSortedArrays(nums2, length2);
        } else if (length2 == 0 && length1 != 0) {
            return findMedianSortedArrays(nums1, length1);
        }

        if ( (length1 + length2) % 2 == 0) {
            return (findKth(nums1, 0, length1-1, nums2, 0, length2-1, (length1 + length2) / 2)
                    + findKth(nums1, 0, length1-1, nums2, 0, length2-1, (length1 + length2) / 2 + 1)) / 2;
        } else {
            return findKth(nums1,0, length1-1, nums2, 0, length2-1, (length1 + length2) / 2 + 1);
        }
    }

    public static void main(String[] args) {
        int[] num1 = {1};
        int[] num2 = {2,3};
        MedianOfTwoSortedArrays_4 solution = new MedianOfTwoSortedArrays_4();
        System.out.println(solution.findMedianSortedArrays(num1, num2));
    }
}
