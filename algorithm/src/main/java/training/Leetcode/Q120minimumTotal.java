package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *120. 三角形最小路径和
 *
 *
 *
 *
 * 题目描述
 * 评论 (50)
 * 官方题解
 * 提交记录
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Q120minimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int[] nums = new int[triangle.size()];
        nums[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            int[] temp = new int[nums.length];
            for (int k = 0 ; k < nums.length; k++) {
                temp[k] = nums[k];
            }
            nums[0] = nums[0] + triangle.get(i).get(0);
            for (int j = 1; j <= i; j++) {
                int numLeft = temp[j - 1];
                if (j != i) {
                    int numRight = temp[j];
                    nums[j] = (numLeft > numRight? numRight: numLeft) +  triangle.get(i).get(j);
                } else {
                    nums[j] =  temp[j-1] + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0 ;i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }
    public static void main(String[] args) {
        Q120minimumTotal test = new Q120minimumTotal();
        List<List<Integer>> m = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        m.add(list1);
        list1 = new ArrayList<>();
        list1.add(3);
        list1.add(4);
        m.add(list1);

        list1 = new ArrayList<>();
        list1.add(6);
        list1.add(5);
        list1.add(4);
        m.add(list1);

        list1 = new ArrayList<>();
        list1.add(4);
        list1.add(1);
        list1.add(8);
        list1.add(3);
        m.add(list1);
        System.out.print(test.minimumTotal(m));
    }
}