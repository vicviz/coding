package training.Leetcode;

/**
 *115. 不同的子序列
 *
 *
 *
 *
 * 题目描述
 * 评论 (14)
 * 官方题解
 * 提交记录
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 示例 1:
 *
 * 输入: S = "rabbbit", T = "rabbit"
 * 输出: 3
 * 解释:
 *
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2:
 *
 * 输入: S = "babgbag", T = "bag"
 * 输出: 5
 * 解释:
 *
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class Q115numDistinct {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (t == null || t.length() == 0) {
            return 0;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][1] = dp[i-1][1] + (t.charAt(0) == s.charAt(i-1)? 1 : 0);
        }
        for (int i = 2 ; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[j][i] = dp[j - 1][i - 1] + dp[j-1][i];
                } else {
                    dp[j][i] = dp[j - 1][i];
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        Q115numDistinct test = new Q115numDistinct();
        System.out.println(test.numDistinct("rabbbit", "rabbit"));
    }
}