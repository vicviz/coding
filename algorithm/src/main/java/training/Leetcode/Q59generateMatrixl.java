package training.Leetcode;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class Q59generateMatrixl {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int value = 1;
        int level = 0;
        while (value <= n * n) {
            int row = level;
            int col = level;
            for (; col < n - level; col++) {
                m[row][col] = value++;
            }
            col--;row++;
            for (; row < n  - level; row++) {
                m[row][col] = value++;
            }
            row--;col--;
            for (; col >= level; col--) {
                m[row][col] = value++;
            }
            col++;row--;
            for (; row > level; row--) {
                m[row][col] = value++;
            }
            level++;
        }
        return m;
    }

    private void print(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0 ; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Q59generateMatrixl test = new Q59generateMatrixl();
        int[][] m = test.generateMatrix(6);
        test.print(m);
    }
}
