package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *104. 二叉树的最大深度
 *
 *
 *
 *
 * 题目描述
 * 评论 (73)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class Q104maxDepth {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNum = maxDepth(root.left);
        int rightNum = maxDepth(root.right);
        if (leftNum > rightNum) {
            return leftNum + 1;
        } else {
            return rightNum + 1;
        }
    }

    public static void main(String[] args) {
        Q104maxDepth test = new Q104maxDepth();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(5);
        right.left = n1;
        System.out.println(test.maxDepth(root));
    }
}