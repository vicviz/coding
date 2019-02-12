package training.Leetcode;

/**
 *96. 不同的二叉搜索树
 *
 *
 *
 *
 * 题目描述
 * 评论 (33)
 * 官方题解
 * 提交记录
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class Q96numTrees {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        int[] num = new int[n + 1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i <= n; i ++) {
            for (int j = 0; j < i; j++) {
                num[i] += num[j] * num[i - j - 1];
            }
        }
        return num[n];
    }


    public static void main(String[] args) {
        Q96numTrees test = new Q96numTrees();
        System.out.println(test.numTrees(3));
    }
}