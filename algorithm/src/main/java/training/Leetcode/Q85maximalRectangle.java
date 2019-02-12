package training.Leetcode;

import java.util.Stack;

/**
 * 85. 最大矩形
 *
 *
 *
 *
 * 题目描述
 * 评论 (7)
 * 官方题解
 * 提交记录
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Q85maximalRectangle {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack();
        int i = 0;
        while (i < heights.length) {
            if (stack.empty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                int area = heights[index] * (stack.empty()? i: i - stack.peek() - 1);
                if (area > max) {
                    max = area;
                }
            }
        }
        while (!stack.empty()) {
            int index = stack.pop();
            int area = heights[index] * (stack.empty()? i : i - stack.peek() - 1);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i ++) {
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    dp[j] = matrix[i][j] - '0';
                }
            } else {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '0') {
                        dp[j] = 0;
                    } else {
                        dp[j] = dp[j] + 1;
                    }
                }
            }
            int newmax = largestRectangleArea(dp);
            if (max < newmax) {
                max = newmax;
            }
        }
        return max;
    }


    public static void main(String[] args) {
//        char [][] m = new char[][]{
//                {'1','0','1','0','0'},
//                                {'1','0','1','1','1'},
//                                {'1','1','1','1','1'},
//                                {'1','0','0','1','0'}};
        char [][] m = new char[][]{
                {'0','1'},
                {'1','0'}};
        Q85maximalRectangle test = new Q85maximalRectangle();
        System.out.print(test.maximalRectangle(m));
    }
}