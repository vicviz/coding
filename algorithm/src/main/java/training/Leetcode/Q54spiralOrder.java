package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * ]
 */
public class Q54spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return Collections.emptyList();
        }
        int col = matrix[0].length;
        if (col == 0) {
            return Collections.emptyList();
        }
        int rang = 0;
        List<Integer> result = new ArrayList<>();
        int allNum = row * col;
        int nowNum = 0;
        while (nowNum <= allNum) {
            int i = rang;
            int j = rang;
            for (; j < col - rang; j++) {
                result.add(matrix[i][j]);
                nowNum ++;
                if (nowNum >= allNum) {
                    return result;
                }
            }
            i++;j--;
            for (; i < row - rang; i++) {
                result.add(matrix[i][j]);
                nowNum ++;
                if (nowNum >= allNum) {
                    return result;
                }
            }
            j--;i--;
            for (; j >= rang; j--) {
                result.add(matrix[i][j]);
                nowNum ++;
                if (nowNum >= allNum) {
                    return result;
                }
            }
            j++;i--;
            for (; i > rang; i--) {
                result.add(matrix[i][j]);
                nowNum ++;
                if (nowNum >= allNum) {
                    return result;
                }
            }
            rang ++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Q54spiralOrder test = new Q54spiralOrder();
        System.out.println(test.spiralOrder(matrix));
    }
}
