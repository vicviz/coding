package training.Leetcode;

/**
 *74. 搜索二维矩阵
 *
 *
 *
 *
 * 题目描述
 * 评论 (39)
 * 官方题解
 * 提交记录
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class Q74searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rowNum = matrix.length;
        if (target < matrix[0][0] || target > matrix[rowNum-1][matrix[0].length - 1]) {
            return false;
        }
        int index;
        int i = 0, j = rowNum - 1;
        int targetLine = -1;
        while (i <= j) {
            index = j - (j - i) / 2;
            if (matrix[index][0] == target) {
                return true;
            } else if (target < matrix[index][0]) {
                j = index - 1;
            } else {
                if (index == rowNum - 1 || target < matrix[index + 1][0]) {
                    targetLine = index;
                    break;
                } else {
                    i = index + 1;
                }
            }
        }
        if (targetLine == -1) {
            return false;
        }
        i = 1;
        j = matrix[targetLine].length - 1;
        while (i <= j) {
            index = j - (j - i) / 2;
            if (target == matrix[targetLine][index]) {
                return true;
            } else if (target > matrix[targetLine][index]) {
                i = index + 1;
            } else {
                j = index - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q74searchMatrix test = new Q74searchMatrix();
        int[][] m = new int[][] {{1,  3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(test.searchMatrix(m, 2));
    }
}
