package training.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 80. 删除排序数组中的重复项 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (30)
 * 官方题解
 * 提交记录
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Q80removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int now = 1;
        int index = 1;
        boolean isMoreThan2 = false;
        while (now < nums.length) {
            if (!isMoreThan2) {
                if (nums[now] == nums[now - 1]) {
                    isMoreThan2 = true;
                }
                nums[index] = nums[now];
                index++;
                now++;
            } else {
                if (nums[now] == nums[now - 1]) {
                    now++;
                } else {
                    nums[index] = nums[now];
                    index ++;
                    now++;
                    isMoreThan2 = false;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Q80removeDuplicates test = new Q80removeDuplicates();
        int[] m = new int[]{1,1,1,2,2,2,3};
        int len = test.removeDuplicates(m);
        for (int i = 0 ; i < len; i++) {
            System.out.print(m[i] + "\t");
        }
    }
}
