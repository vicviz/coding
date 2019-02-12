package training.Leetcode;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *95. 不同的二叉搜索树 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (11)
 * 官方题解
 * 提交记录
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class Q95generateTrees {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<TreeNode> result = buildTree(1, n);
        return result;
    }

    private List<TreeNode> buildTree(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = buildTree(start, i - 1);
            List<TreeNode> right = buildTree(i + 1, end);
            for (TreeNode leftNode: left) {
                for (TreeNode rightNode: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Q95generateTrees test = new Q95generateTrees();
        test.generateTrees(3);
    }
}