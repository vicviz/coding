package training.Leetcode;

import java.util.*;

/**
 给定一个 n × n 的二维矩阵表示一个图像。

 将图像顺时针旋转 90 度。

 说明：

 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

 示例 1:

 给定 matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 示例 2:

 给定 matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]
 */
public class Q48rotate {

    public void rotate(int[][] matrix) {
        if (matrix.length <=1) {
            return ;
        }
        int size = matrix[0].length;
        int level = 0;
        while ( (level * 2) <= (size + 1)) {
            for (int i = 0; (level + i) < size - level - 1; i++ ) {
                //置换4次
                int startRow = level;
                int startCol = level + i;
                int nextRow = startCol;
                int nextCol = size - 1 - startRow;
                int temp = matrix[nextRow][nextCol];
                matrix[nextRow][nextCol] = matrix[startRow][startCol];

                startRow = nextRow;
                startCol = nextCol;
                nextRow = startCol;
                nextCol = size - 1 - startRow;
                int temp2 = matrix[nextRow][nextCol];
                matrix[nextRow][nextCol] = temp;

                startRow = nextRow;
                startCol = nextCol;
                nextRow = startCol;
                nextCol = size - 1 - startRow;
                int temp3 = matrix[nextRow][nextCol];
                matrix[nextRow][nextCol] = temp2;

                startRow = nextRow;
                startCol = nextCol;
                nextRow = startCol;
                nextCol = size - 1 - startRow;
                int temp4 = matrix[nextRow][nextCol];
                matrix[nextRow][nextCol] = temp3;

                startRow = nextRow;
                startCol = nextCol;
                nextRow = startCol;
                nextCol = size - 1 - startRow;
                matrix[nextRow][nextCol] = temp4;
            }
            level++;
        }
    }

    public static void print(int[][] m) {
        for (int i = 0 ; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
    public static void main(String[] args) {
        Q48rotate test = new Q48rotate();
        int[][] m = new int[][]{{2,29,20,26,16,28},
                                {12,27,9,25,13,21},
                                {32,33,32,2,28,14},
                                {13,14,32,27,22,26},
                                {33,1,20,7,21,7},
                                {4,24,1,6,32,34}};
        print(m);
        test.rotate(m);
        print(m);
    }
}
