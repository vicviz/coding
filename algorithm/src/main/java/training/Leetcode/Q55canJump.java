package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Q55canJump {
    public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return false;
        } else if (nums.length == 1) {
            return true;
        }
        int[] alreadyGet = new int[nums.length];
        return canJump(nums, alreadyGet, 0);
    }

    public boolean canJump(int[] nums, int[] alreadyGet, int nowIndex) {
        int nowValue = nums[nowIndex];
        if ((nowIndex == nums.length - 1) || (nowIndex + nowValue) >= nums.length - 1) {
            return true;
        } else {
            for (int i = 1; i <= nowValue && (nowIndex + i) < nums.length; i++) {
                if (alreadyGet[nowIndex + i] != -1) {
                    boolean canJump = canJump(nums, alreadyGet, nowIndex + i);
                    if (canJump == true) {
                        return true;
                    } else {
                        alreadyGet[nowIndex + i] = -1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q55canJump test = new Q55canJump();
//        int[] nums = {2,3,1,1};
//        int[] nums = {3,2,1,0,4};
        int[] nums = {3,0,0,0,0};
        System.out.println(test.canJump(nums));
    }
}
