package training.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *114. 二叉树展开为链表
 *
 *
 *
 *
 * 题目描述
 * 评论 (25)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class Q114flatten {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        root = build(root);
    }

    private TreeNode build(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode rightRoot = null;
        TreeNode leftRoot = null;

        if (root.right != null) {
            rightRoot = build(root.right);
            root.right = null;
        }
        if (root.left != null) {
            leftRoot = build(root.left);
            root.left = null;
        }
        if (leftRoot != null) {
            TreeNode iter = leftRoot;
            while (iter.right != null) {
                iter = iter.right;
            }
            iter.right = rightRoot;
            root.right = leftRoot;
        } else {
            root.right = rightRoot;
        }
        return root;
    }


    public static void main(String[] args) {
        Q114flatten test = new Q114flatten();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(27);

        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.print(test.build(n1));
        System.out.print(n1);
    }
}