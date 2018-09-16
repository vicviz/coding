package training.Leetcode;


/**

 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

 你的算法时间复杂度必须是 O(log n) 级别。

 如果数组中不存在目标值，返回 [-1, -1]。

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]
 *
 */
public class Q34SearchBeginEnd {
    private int binarySearch(int[] nums, int target, int start, int end) {
        if (nums.length == 0) {
            return -1;
        }
        if (start < 0 || end >= nums.length) {
            return -1;
        }
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

    private int searchEnd(int[] nums, int target, int start, int end) {
        int begin = start;
        int newIndex = binarySearch(nums, target, start + 1, end);
        while (newIndex != -1) {
            begin = newIndex;
            newIndex = binarySearch(nums, target, begin + 1, end);
        }
        return begin;
    }
    private int searchBegin(int[] nums, int target, int start, int end) {
        int begin = end;
        int newIndex = binarySearch(nums, target, start, end - 1);
        while (newIndex != -1) {
            begin = newIndex;
            newIndex = binarySearch(nums, target, start, begin - 1);
        }
        return begin;
    }

    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target, 0, nums.length - 1);
        if (index == -1) {
            return new int[]{-1, -1};
        } else {
            int begin = searchBegin(nums, target, 0, index);
            int end = searchEnd(nums, target, index, nums.length - 1);
            return new int[]{begin, end};
        }
    }

    public static void main(String[] args) {
        int[] nums = {8,8};
        Q34SearchBeginEnd test = new Q34SearchBeginEnd();
        int[] result = test.searchRange(nums, 8);
        System.out.println(result[0] + ", " + result[1]);
    }
}
