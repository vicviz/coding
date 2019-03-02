package training.Leetcode;

/**
 *124. 二叉树中的最大路径和
 *
 *
 *
 *
 * 题目描述
 * 评论 (30)
 * 官方题解
 * 提交记录
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 */
public class Q124maxPathSum {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public int maxPathSum(TreeNode root) {
        TreeNode maxNode = new TreeNode(Integer.MIN_VALUE);
        max(root, maxNode);
        return maxNode.val;
    }

    private int max(TreeNode node, TreeNode maxNode) {
        if (node == null) {
            return 0;
        }
        int maxLeft = 0, maxRight = 0;
        if (node.left != null) {
            maxLeft = max(node.left, maxNode);
        }
        if (node.right != null) {
            maxRight = max(node.right, maxNode);
        }

        int max1 = max(node.val, maxLeft + node.val, maxRight + node.val);
        int max2 = max(max1, node.val + maxLeft + maxRight);
        if (max2 > maxNode.val) {
            maxNode.val = max2;
        }
        return max(node.val,  node.val + maxLeft, node.val + maxRight);
    }

    private int max(int a, int b, int c) {
        if (a < b) {
            a = b;
        }
        if (a < c) {
            a = c;
        }
        return a;
    }
    private int max(int a, int b) {
        if (a < b) {
            a = b;
        }
        return a;
    }
}