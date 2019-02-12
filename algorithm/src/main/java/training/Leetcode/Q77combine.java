package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *77. 组合
 *
 *
 *
 *
 * 题目描述
 * 评论 (31)
 * 官方题解
 * 提交记录
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Q77combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= n; i++ ) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            result.add(list);
        }
        int eleNum = 1;
        while (eleNum < k) {
            List<List<Integer>> newResult = new ArrayList<>();
            for (List<Integer> list: result) {
                int last = list.get(list.size() - 1);
                for (int i = last + 1; i <= n; i++) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i);
                    newResult.add(newList);
                }
            }
            result = newResult;
            eleNum++;
        }
        return result;
    }

    public static void main(String[] args) {
        Q77combine test = new Q77combine();
        List<List<Integer>> result = test.combine(4, 3);
        for (List<Integer> l: result) {
            for (Integer i: l) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
