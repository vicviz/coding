package training.Leetcode;

import java.util.*;

/**
 *107. 二叉树的层次遍历 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (63)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class Q107levelOrderBottom {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> list1 = new LinkedList<>();
        LinkedList<TreeNode> list2 = new LinkedList<>();
        list1.add(root);
        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (!list1.isEmpty()) {
                List<Integer> nums = new ArrayList<>();
                while (!list1.isEmpty()) {
                    TreeNode treeNode = list1.pollFirst();
                    nums.add(treeNode.val);
                    if (treeNode.left != null) {
                        list2.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        list2.add(treeNode.right);
                    }
                }
                result.add(nums);
            } else {
                List<Integer> nums = new ArrayList<>();
                while (!list2.isEmpty()) {
                    TreeNode treeNode = list2.pollFirst();
                    nums.add(treeNode.val);
                    if (treeNode.left != null) {
                        list1.add(treeNode.left);
                    }
                    if (treeNode.right!= null) {
                        list1.add(treeNode.right);
                    }
                }
                result.add(nums);
            }
        }

        List<List<Integer>> resultReve = new ArrayList<>(result.size());
        for (int i = result.size() - 1; i >= 0; i--) {
            resultReve.add(result.get(i));
        }
        return resultReve;
    }

    public static void main(String[] args) {
        Q107levelOrderBottom test = new Q107levelOrderBottom();
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
    }
}