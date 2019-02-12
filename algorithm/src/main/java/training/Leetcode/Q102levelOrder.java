package training.Leetcode;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 *101. 对称二叉树
 *
 *
 *
 *
 * 题目描述
 * 评论 (69)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。？
 */
public class Q102levelOrder {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            List<Integer> newList = new ArrayList<>();
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    TreeNode node = queue1.pollFirst();
                    newList.add(node.val);
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                }
            } else if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    TreeNode node = queue2.pollFirst();
                    newList.add(node.val);
                    if (node.left != null) {
                        queue1.add(node.left);
                    }
                    if (node.right != null) {
                        queue1.add(node.right);
                    }
                }
            }
            result.add(newList);
        }
        return result;
    }


    public static void main(String[] args) {
        Q102levelOrder test = new Q102levelOrder();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(5);
        right.left = n1;
        List<List<Integer>> result = test.levelOrder(root);
        for (List<Integer> list: result) {
            System.out.println(list);
        }
    }
}