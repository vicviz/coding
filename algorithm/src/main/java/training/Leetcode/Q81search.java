package training.Leetcode;

/**
 * 81. 搜索旋转排序数组 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (21)
 * 官方题解
 * 提交记录
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class Q81search {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return search(nums, target, 0, nums.length - 1);
    }

    public boolean search(int[] nums, int target, int i, int j) {
        if (i > j) {
            return false;
        }
        if (nums[i] == target || nums[j] == target) {
            return true;
        }
        if (nums[i] < nums[j]) {
            //可以直接搜索
            return bSearch(nums, target, i + 1, j - 1);
//        } else if (nums[i] == nums[j]) {
//            //进行
        } else {
            //进行分治
            int index = j - (j- i)/2;
            if (target == nums[index]) {
                return true;
            }
            boolean left = search(nums, target, index + 1, j);
            if (left) {
                return true;
            }
            boolean right = search(nums, target, i, index - 1);
            if (right) {
                return true;
            }
            return false;
        }
    }

    public boolean bSearch(int[] nums, int target, int i, int j) {
        while (i <= j) {
            int index = j - (j- i)/2;
            if (nums[index] == target) {
                return true;
            } else if (target > nums[index]) {
                i = index + 1;
            } else {
                j = index - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q81search test = new Q81search();
//        int[] m = new int[]{2,5,6,0,0,1,2};
//        int[] m = new int[]{5,5,5,5,6,5,5,5};
        int[] m = new int[]{1};
        System.out.println(test.search(m, 0));
    }
}
