package training.Leetcode;

import java.util.*;

/**
 *128. 最长连续序列
 *
 *
 *
 *
 * 题目描述
 * 评论 (28)
 * 题解
 * 提交记录
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Q128longestConsecutive {

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> adjMap = new HashMap<>();
        for (int num: nums) {
            adjMap.put(num, null);
            if (adjMap.containsKey(num - 1)) {
                adjMap.put(num - 1, num);
            }
        }
        for (int num: nums) {
            if (adjMap.containsKey(num - 1)) {
                adjMap.put(num - 1, num);
            }
        }
        int maxLen = 0;
        for (Integer key: adjMap.keySet()) {
            int lenNow = lenth(adjMap, key);
            if (lenNow > maxLen) {
                maxLen = lenNow;
            }
        }
        return maxLen;
    }

    private int lenth(Map<Integer, Integer> map, int num) {
        Integer next = map.get(num);
        int len = 1;
        while (next != null) {
            next = map.get(next);
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        Q128longestConsecutive test = new Q128longestConsecutive();
        System.out.println(test.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

}