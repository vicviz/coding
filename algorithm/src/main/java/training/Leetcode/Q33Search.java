package training.Leetcode;


import java.util.Stack;

/**

 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 你可以假设数组中不存在重复的元素。

 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:

 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4
 示例 2:

 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 *
 */
public class Q33Search {
    private int findMax(int[] nums) {
        int len = nums.length;
        if (nums[len - 1] > nums[0]) {
            return len - 1;
        } else {
            int start = 0;
            int end = len - 1;
            int index = start + (end - start) / 2;
            while (nums[index] < nums[index + 1]) {
                if (nums[index] >= nums[0]) {
                    start = index + 1;
                } else {
                    end = index - 1;
                }
                index = start + (end - start) / 2;
            }
            return index;
        }
    }

    private int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
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
        return -1;
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        int index = findMax(nums);
        if (index == nums.length) {
            return binarySearch(nums, target, 0, nums.length - 1);
        } else {
            if (target == nums[0]) {
                return 0;
            } else {
                if (target < nums[0]) {
                    return binarySearch(nums, target, index + 1, nums.length - 1);
                } else {
                    return binarySearch(nums, target, 0, index);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {4,5,1,2,3};
        Q33Search test = new Q33Search();
        System.out.println(test.search(nums, 1));
    }
}
