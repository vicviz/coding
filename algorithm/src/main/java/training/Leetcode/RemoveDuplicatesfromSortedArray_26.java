/**
 * RemoveDuplicatesfromSortedArray.java, 2016—10-05.
 */
package training.Leetcode;

/**
 https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 For example,
 Given input array nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 It doesn't matter what you leave beyond the new length.

 Subscribe to see which companies asked this question
 * @author zhaowei
 */
public class RemoveDuplicatesfromSortedArray_26 {
    public int removeDuplicates(int[] nums) {
        int p1 = 0;
        int p2 = 1;
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        while (p2 < length) {
            if (nums[p2] != nums[p2-1]) {
                p1++;
                nums[p1] = nums[p2];
            }
            p2++;
        }
        return p1 + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray_26 solution = new RemoveDuplicatesfromSortedArray_26();
        int[] a = new int[]{1,1};
        int result = solution.removeDuplicates(a);
        System.out.println(result);
        for (int i = 0; i < result;i++) {
            System.out.print(a[i]+ ",");
        }
    }
}
