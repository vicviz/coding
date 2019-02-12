package training.Leetcode;

import java.util.Stack;

/**
 * 84.柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class Q84largestRectangleArea {
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


    /**
     * 每次从i到j找最小的高度，然后每次看看是不是最小的面积
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int area = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i ++) {
            if (area < heights[i]) {
                area = heights[i];
            }
        }
        for (int interval = 1; interval < heights.length; interval ++) {
            for (int i = 0; i < heights.length; i++) {
                if (interval + i >= heights.length) {
                    break;
                }
                int minHeight = Integer.MAX_VALUE;
                for (int j = i; j <= i + interval; j++) {
                    if (minHeight > heights[j]) {
                        minHeight = heights[j];
                    }
                }
                int nowArea = minHeight * (interval + 1);
                if (area < nowArea) {
                    area = nowArea;
                }
            }
        }
        return area;
    }

    /**
     * 记录从i到j最小的高度，然后每次看看是不是最小的面积
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int area = Integer.MIN_VALUE;
        int[][] m = new int[heights.length][heights.length];
        for (int i = 0; i < heights.length; i ++) {
            m[i][i] = heights[i];
            if (area < m[i][i]) {
                area = m[i][i];
            }
        }
        for (int interval = 1; interval < heights.length; interval ++) {
            for (int i = 0; i < heights.length; i++) {
                if (interval + i >= heights.length) {
                    break;
                }
                if (m[i][i + interval -1 ] > heights[i + interval]) {
                    m[i][i + interval] = heights[i + interval];
                } else {
                    m[i][i + interval] = m[i][i + interval -1];
                }
                int nowArea = m[i][i + interval] * (interval + 1);
                if (area < nowArea) {
                    area = nowArea;
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        Q84largestRectangleArea test = new Q84largestRectangleArea();
        System.out.print(test.largestRectangleArea(heights));
    }
}