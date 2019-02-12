package training.Leetcode;

/**
 64. 最小路径和




 题目描述
 评论 (45)
 官方题解
 提交记录
 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

 说明：每次只能向下或者向右移动一步。

 示例:

 输入:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 输出: 7
 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class Q64minPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (grid.length == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 1) {
            return grid[0][0];
        }
        int[][] dp = new int[m][n];

        int i = 0,j = 0;
        for (i = 0 ; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    int pre1 = Integer.MAX_VALUE, pre2 = Integer.MAX_VALUE;
                    if (i - 1 >= 0) {
                        pre1 = dp[i - 1][j];
                    }
                    if (j - 1 >= 0) {
                        pre2 = dp[i][j - 1];
                    }
                    dp[i][j] = (pre1 > pre2 ? pre2 : pre1) + grid[i][j];
                }
            }
        }
        return dp[i-1][j-1];
    }

    public static void main(String[] args) {
        Q64minPathSum test = new Q64minPathSum();
//        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
//        System.out.println(test.uniquePathsWithObstacles(obstacleGrid));

        int[][] grid = {{1,3,1},
                        {1,5,1},
                        {4,2,1}};
        System.out.println(test.minPathSum(grid));
    }
}
