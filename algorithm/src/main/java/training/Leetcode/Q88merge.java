package training.Leetcode;

/**
 * 88. 合并两个有序数组
 *
 *
 *
 *
 * 题目描述
 * 评论 (163)
 * 官方题解
 * 提交记录
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Q88merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        } else if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }

        int end1 = m - 1;
        int end2 = n - 1;
        int index = m + n - 1;
        while (end1 >= 0 && end2 >= 0) {
            if (nums1[end1] > nums2[end2]) {
                nums1[index--] = nums1[end1--];
            } else {
                nums1[index--] = nums2[end2--];
            }
        }
        while (end1 >= 0) {
            nums1[index--] = nums1[end1--];
        }
        while (end2 >= 0) {
            nums1[index--] = nums2[end2--];
        }
        return;
    }

    public static void main(String[] args) {
        Q88merge test = new Q88merge();
        int[]nums1 = new int[]{2,0};
        int[]nums2 = new int[]{1};
        test.merge(nums1, 1, nums2, 1);
        for(int i = 0 ; i < 2;i++) {
            System.out.print(nums1[i] + ",");
        }
    }
}