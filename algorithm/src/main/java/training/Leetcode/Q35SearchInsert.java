package training.Leetcode;


/**
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

 你可以假设数组中无重复元素。

 示例 1:

 输入: [1,3,5,6], 5
 输出: 2
 示例 2:

 输入: [1,3,5,6], 2
 输出: 1
 示例 3:

 输入: [1,3,5,6], 7
 输出: 4
 示例 4:

 输入: [1,3,5,6], 0
 输出: 0
 *
 */
public class Q35SearchInsert {
    private int binarySearch(int[] nums, int target, int start, int end) {
        if (nums.length == 0) {
            return 0;
        }
        if (start < 0) {
            return 0;
        }
        if (end >= nums.length) {
            return nums.length;
        }
        while (start <= end) {
            if (start == end) {
                if (nums[start] < target) {
                    return start + 1;
                } else {
                    return start;
                }
            }
            int index = start + (end - start) / 2;
            if (nums[index] == target) {
                return index;
            } else {
                if (nums[index] < target) {
                    start = index + 1;
                } else {
                    end = index - 1;
                }
            }
        }
        if (start < 0) {
            return 0;
        }
        if (end >= nums.length) {
            return nums.length;
        }
        return end + 1;
    }

    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        Q35SearchInsert test = new Q35SearchInsert();
        System.out.println(test.searchInsert(nums, 0));
    }
}
