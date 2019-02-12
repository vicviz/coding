package training.Leetcode;

/**
 * 不同路径
 *
 *
 *
 *
 * 题目描述
 * 评论 (65)
 * 官方题解
 * 提交记录
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class Q62uniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int small = m;
        if (m > n) {
            small = n;
        }
        int sum = m + n - 2;
        long a = 1, c = 1;
        for (int i = 1; i <= small - 1; i++) {
            c *= sum + 1 - i;
            a *= i;
            if (c % a == 0) {
                c /= a;
                a /= a;
            }
        }
        return (int)(c/a);
    }

    public static void main(String[] args) {
        Q62uniquePaths test = new Q62uniquePaths();
        System.out.println(test.uniquePaths(10,10));
    }
}
