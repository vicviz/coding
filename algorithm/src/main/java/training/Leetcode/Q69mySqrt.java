package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *69. x 的平方根
 *
 *
 *
 *
 * 题目描述
 * 评论 (77)
 * 官方题解
 * 提交记录
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class Q69mySqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        return (int)Math.sqrt((double)x);
    }

    public static void main(String[] args) {
        Q69mySqrt test = new Q69mySqrt();
        System.out.println(test.mySqrt(8));
    }
}
