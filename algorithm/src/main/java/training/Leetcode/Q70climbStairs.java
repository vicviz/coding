package training.Leetcode;

/**
 *爬楼梯
 *
 *
 *
 *
 * 题目描述
 * 评论 (201)
 * 官方题解
 * 提交记录
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Q70climbStairs {
    public int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n- 2);
    }

    public int climbStairs(int n) {
        int allNum1 = 1;
        int allNum2 = 2;
        int allNum = 0;
        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                allNum = allNum1 + allNum2;
                if (i == n) {
                    return allNum;
                } else {
                    allNum1 = allNum2;
                    allNum2 = allNum;
                }
            }
        }
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        return allNum;
    }


    public static void main(String[] args) {
        Q70climbStairs test = new Q70climbStairs();
        System.out.println(test.climbStairs(4));
    }
}
