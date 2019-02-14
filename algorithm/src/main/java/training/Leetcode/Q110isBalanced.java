package training.Leetcode;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *110. 平衡二叉树
 *
 *
 *
 *
 * 题目描述
 * 评论 (62)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class Q110isBalanced {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isBalanced(TreeNode root) {
        return findDepth(root) != -1;
    }

    private int findDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthLeft = 0;
        int depthRight = 0;
        if (root.left != null) {
            int depth = findDepth(root.left);
            if (depth == -1) {
                return -1;
            } else {
                depthLeft = depth + 1;
            }
        }
        if (root.right != null) {
            int depth = findDepth(root.right);
            if (depth == -1) {
                return -1;
            } else {
                depthRight = depth + 1;
            }
        }
        if (Math.abs(depthLeft - depthRight) <= 1) {
            return depthLeft > depthRight ? depthLeft:depthRight;
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        Q110isBalanced test = new Q110isBalanced();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);

        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.print(test.isBalanced(n1));
    }
}