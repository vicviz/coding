package training.Leetcode;

import java.util.LinkedList;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O'
 * 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 */
public class Q130O2X {
    /**
     * 宽度优先遍历
     */
    public void wdfMark(char[][] board, int m, int n, int i, int j) {
        if (i >=0 && j >= 0 && i < m && j < n) {
            if (board[i][j] == 'O') {
                board[i][j] = '1';
                wdfMark(board, m, n, i - 1, j);
                wdfMark(board, m, n, i + 1, j);
                wdfMark(board, m, n, i, j - 1);
                wdfMark(board, m, n, i, j + 1);
            }
        }
    }

    public void solve(char[][] board) {
        int m = board.length;
        if (m <= 2) {
            return;
        }
        int n = board[0].length;
        if (n <= 2) {
            return;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    //边界上的点
                    if (board[i][j] == 'O') {
                        wdfMark(board, m, n, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
