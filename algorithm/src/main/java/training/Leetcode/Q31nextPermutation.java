package training.Leetcode;


import java.util.*;

/**
 *实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 *
 * 思路:
 * 从最后一位起，向前找到第一个逆序产生的地方index-1和index，把index-1的数与后面这一坨中，比index-1大的最小的数找出来，然后交换
 * 如果最后一位没有找到，再看前一个去交换
 *
 */
public class Q31nextPermutation {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void sort(int[] nums, int index, int allNums) {
        for (int i = 0; i < allNums - index; i++) {
            for (int j = index; j < (allNums - 1 - i);j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j + 1, j);
                }
            }
        }
    }

    public void nextPermutation(int[] nums) {
        int allNums = nums.length;
        for (int index = allNums - 1; index > 0; index--) {
            if (nums[index - 1] < nums[index]) {
                sort(nums, index, allNums);
                for (int j = index;j < allNums;j++) {
                    if (nums[index - 1] < nums[j]) {
                        swap(nums, index - 1, j);
                        sort(nums, index, allNums);
                        return;
                    }
                }
                return;
            }
        }
        sort(nums, 0, allNums);
    }

    private void print(int[] nums) {
        for (int i = 0 ; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }
    public static void main(String[] args) {

        Q31nextPermutation q31nextPermutation = new Q31nextPermutation();
        int[] nums0 = {4,2,0,2,3,2,0};
        q31nextPermutation.nextPermutation(nums0);
        q31nextPermutation.print(nums0);
        System.out.println();

        int[] nums = {2, 4, 5, 2, 1};
        q31nextPermutation.nextPermutation(nums);
        q31nextPermutation.print(nums);
        System.out.println();

        int[] nums2 = {5,4,3,2,1};
        q31nextPermutation.nextPermutation(nums2);
        q31nextPermutation.print(nums2);
        System.out.println();

        int[] nums3 = {1,3,2};
        q31nextPermutation.nextPermutation(nums3);
        q31nextPermutation.print(nums3);
        System.out.println();
    }
}
