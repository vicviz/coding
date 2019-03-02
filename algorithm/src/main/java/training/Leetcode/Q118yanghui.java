package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *118. 杨辉三角
 *
 *
 *
 *
 * 题目描述
 * 评论 (94)
 * 官方题解
 * 提交记录
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class Q118yanghui {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i + 1);
            if (i == 0) {
                list.add(1);
            } else {
                list.add(1);
                for (int j = 1; j < i; j++) {
                    list.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
                list.add(1);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Q118yanghui test = new Q118yanghui();
        System.out.println(test.generate(5));
    }
}