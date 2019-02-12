package training.Leetcode;

/**
 *73. 矩阵置零
 *
 *
 *
 *
 * 题目描述
 * 评论 (37)
 * 官方题解
 * 提交记录
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */
public class Q73setZeroes {
    public void setZeroes(int[][] matrix) {
        //先找出一行为0以及一列为0的，用来作记录，如果没有这样的，则不用处理了
        int rowNum = matrix.length;
        if (rowNum == 0) {
            return;
        }
        int colNum = matrix[0].length;
        if (colNum == 0) {
            return;
        }
        int row0 = -1;
        int col0 = -1;
        boolean find = false;
        for (int i = 0; i< rowNum; i++) {
            for (int j = 0 ; j < colNum; j++) {
                if (matrix[i][j] == 0) {
                    row0 = i;
                    col0 =j;
                    find = true;
                    break;
                }
            }
        }
        if (!find) {
            return;
        }
        for (int i = 0 ; i < colNum; i++) {
            if (matrix[row0][i] != 0) {
                matrix[row0][i] = 0;
            } else {
                matrix[row0][i] = 1;
            }
        }
        for (int i = 0; i < rowNum; i++) {
            if (matrix[i][col0] != 0) {
                matrix[i][col0] = 0;
            } else {
                matrix[i][col0] = 1;
            }
        }
//        print(matrix);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0 ; j < colNum; j++) {
                if (i == row0 || j == col0) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    matrix[i][col0] = 1;
                    matrix[row0][j] = 1;
                }
            }
        }
//        print(matrix);
        for (int i = 0; i < colNum; i++) {
            if (matrix[row0][i] == 1) {
                for (int j = 0; j < rowNum; j++) {
                    if (j != row0) {
                        matrix[j][i] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < rowNum; i++) {
            if (matrix[i][col0] == 1) {
                for (int j = 0; j < colNum; j++) {
                    if (j != col0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 0 ; i < colNum; i++) {
            matrix[row0][i] = 0;
        }
        for (int i = 0 ; i < rowNum; i++) {
            matrix[i][col0] = 0;
        }
    }

    private void print(int[][] matrix) {
        for (int i = 0; i< matrix.length; i++) {
            for (int j = 0 ; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("___________________");
    }

    public static void main(String[] args) {
        Q73setZeroes test = new Q73setZeroes();
//        int[][] m = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        int[][] m = {{1,1,1}, {1,0,1}, {1,1,1}};
        test.print(m);
        test.setZeroes(m);
        test.print(m);
    }
}
